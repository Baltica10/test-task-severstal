package com.test.severstal.service;

import com.test.severstal.dto.SupplyCreateDto;
import com.test.severstal.model.Supplier;
import com.test.severstal.model.supply.Supply;
import com.test.severstal.model.supply.SupplyReport;
import com.test.severstal.model.supply.SupplyWithSum;
import java.time.LocalDate;
import java.util.List;

public interface SupplyService {

  Supply createSupply(SupplyCreateDto request);

  List<SupplyReport> getReportByPeriod(LocalDate from, LocalDate to);

  List<Supplier> getAllSuppliers();

  List<SupplyWithSum> getLastSupplyWithSum(Integer amount);
}