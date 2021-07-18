package com.test.severstal.repository;

import com.test.severstal.model.supply.SupplyElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyElementRepository extends JpaRepository<SupplyElement, Long> {
}