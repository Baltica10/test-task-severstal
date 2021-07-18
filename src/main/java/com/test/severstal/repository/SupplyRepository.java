package com.test.severstal.repository;

import com.test.severstal.model.supply.Supply;
import com.test.severstal.model.supply.SupplyReport;
import com.test.severstal.model.supply.SupplyWithSum;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplyRepository extends JpaRepository<Supply, Long> {

  @Query(nativeQuery = true,
      value = "select p.id                      as productId,"
          + "       p.description               as productDescription,"
          + "       pt.name                     as productType,"
          + "       pc.name                     as productCategory,"
          + "       spr.id                      as supplierId,"
          + "       spr.name                    as supplierName,"
          + "       spr.inn                     as supplierInn,"
          + "       spr.address                 as supplierAddress,"
          + "       sum(se.quantity)            as totalQuantity,"
          + "       sum(se.price * se.quantity) as totalSum"
          + " from supply s"
          + "         left join supplier spr on s.supplier_id = spr.id"
          + "         left join supply_element se on s.id = se.supply_id"
          + "         left join product p on p.id = se.product_id"
          + "         left join product_category pc on p.category_id = pc.id"
          + "         left join product_type pt on p.type_id = pt.id"
          + " where s.created between :from and :to"
          + " group by p.id, pc.id, pt.id, spr.id"
          + " order by p.id asc, spr.id asc")
  List<SupplyReport> getReportByPeriod(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

  @Query(nativeQuery = true,
      value = "select s.id                      as id,"
          + "       s.created                   as created,"
          + "       spr.name                    as supplier,"
          + "       sum(se.price * se.quantity) as sum"
          + " from supply s"
          + "         left join supplier spr on s.supplier_id = spr.id"
          + "         left join supply_element se on s.id = se.supply_id"
          + " group by s.id, spr.name, s.created"
          + " order by s.created desc"
          + " limit :amount")
  List<SupplyWithSum> getLastSupplyWithSum(@Param("amount") Integer amount);


}