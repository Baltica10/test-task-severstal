package com.test.severstal.dto;

import com.test.severstal.model.supply.Supply;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SupplyDto {

  private Long id;
  private SupplierDto supplier;
  private LocalDateTime created;
  private LocalDateTime updated;
  private List<SupplyElementDto> elements;

  public SupplyDto() {
  }

  public SupplyDto(Supply model) {
    this.id = model.getId();
    this.supplier = new SupplierDto(model.getSupplier());
    this.created = model.getCreated();
    this.updated = model.getUpdated();
    this.elements = model.getElements().stream()
        .map(SupplyElementDto::new)
        .collect(Collectors.toList());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SupplierDto getSupplier() {
    return supplier;
  }

  public void setSupplier(SupplierDto supplier) {
    this.supplier = supplier;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }

  public List<SupplyElementDto> getElements() {
    return elements;
  }

  public void setElements(List<SupplyElementDto> elements) {
    this.elements = elements;
  }

}