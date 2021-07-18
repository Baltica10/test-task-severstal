package com.test.severstal.model.supply;

import com.test.severstal.model.WeightMeasure;
import com.test.severstal.model.product.Product;
import com.test.severstal.model.product.ProductPrice;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@Table(name = "supply_element")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class SupplyElement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "supply_id", referencedColumnName = "id")
  private Supply supply;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;

  private Integer quantity;

  private Double price;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "weight_measure")
  @Type(type = "pgsql_enum")
  private WeightMeasure weightMeasure;

  public SupplyElement() {
  }

  public SupplyElement(ProductPrice productPrice, Integer quantity, Supply supply) {
    this.product = productPrice.getProduct();
    this.price = productPrice.getPrice();
    this.weightMeasure = productPrice.getWeightMeasure();
    this.quantity = quantity;
    this.supply = supply;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Supply getSupply() {
    return supply;
  }

  public void setSupply(Supply supply) {
    this.supply = supply;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
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

  public WeightMeasure getWeightMeasure() {
    return weightMeasure;
  }

  public void setWeightMeasure(WeightMeasure weightMeasure) {
    this.weightMeasure = weightMeasure;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SupplyElement that = (SupplyElement) o;
    return Objects.equals(supply, that.supply) && Objects.equals(product, that.product) && Objects
        .equals(quantity, that.quantity) && Objects.equals(price, that.price) && weightMeasure == that.weightMeasure;
  }

  @Override
  public int hashCode() {
    return Objects.hash(supply, product, quantity, price, weightMeasure);
  }
}