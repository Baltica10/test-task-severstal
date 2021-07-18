package com.test.severstal.service;

import static java.util.Objects.isNull;

import com.test.severstal.dto.SupplyCreateDto;
import com.test.severstal.dto.SupplyElementCreateDto;
import com.test.severstal.exception.EntityNotFoundException;
import com.test.severstal.exception.ValidationException;
import com.test.severstal.model.Supplier;
import com.test.severstal.model.supply.Supply;
import com.test.severstal.model.supply.SupplyElement;
import com.test.severstal.model.supply.SupplyReport;
import com.test.severstal.model.supply.SupplyWithSum;
import com.test.severstal.repository.ProductRepository;
import com.test.severstal.repository.SupplierRepository;
import com.test.severstal.repository.SupplyElementRepository;
import com.test.severstal.repository.SupplyRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplyServiceImpl implements SupplyService {

  private final SupplierRepository supplierRepository;
  private final ProductRepository productRepository;
  private final SupplyRepository supplyRepository;
  private final SupplyElementRepository supplyElementRepository;

  public SupplyServiceImpl(SupplierRepository supplierRepository, ProductRepository productRepository,
      SupplyRepository supplyRepository, SupplyElementRepository supplyElementRepository) {
    this.supplierRepository = supplierRepository;
    this.productRepository = productRepository;
    this.supplyRepository = supplyRepository;
    this.supplyElementRepository = supplyElementRepository;
  }

  @Override
  @Transactional
  public Supply createSupply(SupplyCreateDto request) {

    if (isNull(request.getElements()) || request.getElements().isEmpty()) {
      throw new ValidationException("Elements must not empty");
    }

    if (isNull(request.getSupplierId())) {
      throw new ValidationException("SupplierId must not null");
    }

    var supplier = supplierRepository.findById(request.getSupplierId())
        .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

    var prices = productRepository.getProductPrices(
        request.getSupplierId(),
        request.getElements().stream()
            .map(SupplyElementCreateDto::getProductId)
            .collect(Collectors.toList()),
        LocalDate.now()
    );

    var result = new Supply(supplier);
    supplyRepository.saveAndFlush(result);

    var supplyElements = unionEqualsProducts(request.getElements()).entrySet().stream()
        .map(it -> {
          var productPrice = prices.stream()
              .filter(price -> Objects.equals(it.getKey(), price.getProduct().getId()))
              .findFirst()
              .orElseThrow(() -> new EntityNotFoundException("Price for productId " + it.getKey() + " not found"));

          return new SupplyElement(productPrice, it.getValue(), result);
        })
        .collect(Collectors.toSet());
    supplyElementRepository.saveAllAndFlush(supplyElements);

    result.setElements(supplyElements);

    return result;
  }

  private Map<Long, Integer> unionEqualsProducts(List<SupplyElementCreateDto> elements) {
    Map<Long, Integer> result = new HashMap<>();

    elements.forEach(it -> {

      if (it.getQuantity() <= 0) {
        throw new ValidationException("Quantity < 0");
      }

      if (result.containsKey(it.getProductId())) {
        var oldQuantity = result.get(it.getProductId());
        result.put(it.getProductId(), oldQuantity + it.getQuantity());
      } else {
        result.put(it.getProductId(), it.getQuantity());
      }
    });

    return result;
  }

  @Override
  public List<SupplyReport> getReportByPeriod(LocalDate from, LocalDate to) {
    return supplyRepository.getReportByPeriod(from.atStartOfDay(), to.atStartOfDay().plusHours(24));
  }

  @Override
  public List<Supplier> getAllSuppliers() {
    return supplierRepository.findAll();
  }

  @Override
  public List<SupplyWithSum> getLastSupplyWithSum(Integer amount) {
    return supplyRepository.getLastSupplyWithSum(amount);
  }

}