package com.test.severstal.dto;

import com.test.severstal.model.supply.SupplyReport;

public class ReportDto {

  private ProductDto product;
  private SupplierDto supplier;
  private Long totalQuantity;
  private Long totalSum;

  public ReportDto() {
  }

  public ReportDto(SupplyReport sqlResult) {
    this.product = new ProductDto(sqlResult);
    this.supplier = new SupplierDto(sqlResult);
    this.totalQuantity = sqlResult.getTotalQuantity();
    this.totalSum = sqlResult.getTotalSum();
  }

  public ProductDto getProduct() {
    return product;
  }

  public void setProduct(ProductDto product) {
    this.product = product;
  }

  public SupplierDto getSupplier() {
    return supplier;
  }

  public void setSupplier(SupplierDto supplier) {
    this.supplier = supplier;
  }

  public Long getTotalQuantity() {
    return totalQuantity;
  }

  public void setTotalQuantity(Long totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  public Long getTotalSum() {
    return totalSum;
  }

  public void setTotalSum(Long totalSum) {
    this.totalSum = totalSum;
  }
}
