package com.test.severstal.dto;

import com.test.severstal.model.product.Product;
import com.test.severstal.model.supply.SupplyReport;

public class ProductDto {

  private Long id;
  private String type;
  private String category;
  private String description;

  public ProductDto() {
  }

  public ProductDto(Product model) {
    this.id = model.getId();
    this.type = model.getType().getName();
    this.category = model.getCategory().getName();
    this.description = model.getDescription();
  }

  public ProductDto(SupplyReport sqlResult) {
    this.id = sqlResult.getProductId();
    this.type = sqlResult.getProductType();
    this.category = sqlResult.getProductCategory();
    this.description = sqlResult.getProductDescription();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}