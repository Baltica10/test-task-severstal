package com.test.severstal.dto;

import com.test.severstal.model.Supplier;
import com.test.severstal.model.supply.SupplyReport;

public class SupplierDto {

  private Integer id;
  private String name;
  private Long inn;
  private String address;

  public SupplierDto() {
  }

  public SupplierDto(Supplier model) {
    this.id = model.getId();
    this.name = model.getName();
    this.inn = model.getInn();
    this.address = model.getAddress();
  }

  public SupplierDto(SupplyReport sqlResult) {
    this.id = sqlResult.getSupplierId();
    this.name = sqlResult.getSupplierName();
    this.inn = sqlResult.getSupplierInn();
    this.address = sqlResult.getSupplierAddress();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getInn() {
    return inn;
  }

  public void setInn(Long inn) {
    this.inn = inn;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

}