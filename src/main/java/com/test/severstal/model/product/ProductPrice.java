package com.test.severstal.model.product;

import com.test.severstal.model.Supplier;
import com.test.severstal.model.WeightMeasure;
import com.test.severstal.model.product.Product;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import java.time.LocalDate;
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
@Table(name = "product_price")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class ProductPrice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "supplier_id", referencedColumnName = "id")
  private Supplier supplier;

  @Column(name = "period_start")
  private LocalDate periodStart;

  @Column(name = "period_end")
  private LocalDate periodEnd;

  private Double price;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "weight_measure")
  @Type(type = "pgsql_enum")
  private WeightMeasure weightMeasure;

  public ProductPrice() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public LocalDate getPeriodStart() {
    return periodStart;
  }

  public void setPeriodStart(LocalDate periodStart) {
    this.periodStart = periodStart;
  }

  public LocalDate getPeriodEnd() {
    return periodEnd;
  }

  public void setPeriodEnd(LocalDate periodEnd) {
    this.periodEnd = periodEnd;
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
}
