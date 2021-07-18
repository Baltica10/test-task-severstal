package com.test.severstal.controller;

import com.test.severstal.dto.ReportDto;
import com.test.severstal.dto.SupplyCreateDto;
import com.test.severstal.dto.SupplyDto;
import com.test.severstal.service.SupplyService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/supply")
public class SupplyController {

  private final SupplyService service;

  public SupplyController(SupplyService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<SupplyDto> createSupply(@RequestBody SupplyCreateDto request) {
    return ResponseEntity.ok(
        new SupplyDto(service.createSupply(request))
    );
  }

  @GetMapping
  public ResponseEntity<List<ReportDto>> getReportByPeriod(
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate from,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate to) {
    return ResponseEntity.ok(
        service.getReportByPeriod(from, to).stream()
            .map(ReportDto::new)
            .collect(Collectors.toList())
    );
  }

}