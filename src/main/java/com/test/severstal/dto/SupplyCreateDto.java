package com.test.severstal.dto;

import java.util.List;

public class SupplyCreateDto {

  private Integer supplierId;
  private List<SupplyElementCreateDto> elements;

  public SupplyCreateDto() {
  }

  public Integer getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(Integer supplierId) {
    this.supplierId = supplierId;
  }

  public List<SupplyElementCreateDto> getElements() {
    return elements;
  }

  public void setElements(List<SupplyElementCreateDto> elements) {
    this.elements = elements;
  }
}
