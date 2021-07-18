package com.test.severstal.dto;

import com.test.severstal.model.supply.SupplyElement;

public class SupplyElementDto {

  private Long id;
  private ProductDto product;
  private Integer quantity;
  private Double price;
  private String weightMeasure;

  public SupplyElementDto() {
  }

  public SupplyElementDto(SupplyElement model) {
    this.id = model.getId();
    this.product = new ProductDto(model.getProduct());
    this.quantity = model.getQuantity();
    this.price = model.getPrice();
    this.weightMeasure = model.getWeightMeasure().toString();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProductDto getProduct() {
    return product;
  }

  public void setProduct(ProductDto product) {
    this.product = product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getWeightMeasure() {
    return weightMeasure;
  }

  public void setWeightMeasure(String weightMeasure) {
    this.weightMeasure = weightMeasure;
  }

}