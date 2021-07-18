package com.test.severstal.repository;

import com.test.severstal.model.product.ProductPrice;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductPrice, Long> {

  @Query(nativeQuery = true,
      value = "select pp.* "
          + "from product_price pp "
          + "where pp.supplier_id = :supplierId "
          + "and pp.product_id in (:productIds) "
          + "and :currentData between pp.period_start and pp.period_end "
  )
  List<ProductPrice> getProductPrices(
      @Param("supplierId") Integer supplierId,
      @Param("productIds") List<Long> productIds,
      @Param("currentData") LocalDate currentData);

}