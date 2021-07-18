package com.test.severstal.controller;

import static java.util.Objects.nonNull;

import com.test.severstal.dto.ReportDto;
import com.test.severstal.dto.SupplyCreateDto;
import com.test.severstal.dto.SupplyElementCreateDto;
import com.test.severstal.service.SupplyService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class ContentController {

  private final SupplyService service;

  public ContentController(SupplyService service) {
    this.service = service;
  }

  @ApiIgnore
  @GetMapping(value = "/report")
  public String getReport(Model model,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
      @RequestParam(required = false, defaultValue = "2021-01-01")
          LocalDate from,

      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
      @RequestParam(required = false, defaultValue = "2021-12-31")
          LocalDate to
  ) {
    var reports = service.getReportByPeriod(from, to).stream()
        .map(ReportDto::new)
        .collect(Collectors.toList());

    model.addAttribute("reports", reports);
    model.addAttribute("from", from);
    model.addAttribute("to", to);

    return "report";
  }

  //Мне стыдно за это =(
  @ApiIgnore
  @GetMapping(value = "/supply")
  public String supply(Model model,
      @RequestParam(required = false) Integer supplierId,
      @RequestParam(required = false) Integer productOne,
      @RequestParam(required = false) Integer productTwo,
      @RequestParam(required = false) Integer productThree,
      @RequestParam(required = false) Integer productFour
  ) {
    if (nonNull(supplierId)) {
      List<SupplyElementCreateDto> elements = new ArrayList<>();
      if (nonNull(productOne) && productOne > 0) {
        elements.add(new SupplyElementCreateDto(1L, productOne));
      }
      if (nonNull(productTwo) && productTwo > 0) {
        elements.add(new SupplyElementCreateDto(2L, productTwo));
      }
      if (nonNull(productThree) && productThree > 0) {
        elements.add(new SupplyElementCreateDto(3L, productThree));
      }
      if (nonNull(productFour) && productFour > 0) {
        elements.add(new SupplyElementCreateDto(4L, productFour));
      }
      if (!elements.isEmpty()) {
        var request = new SupplyCreateDto();
        request.setSupplierId(supplierId);
        request.setElements(elements);

        service.createSupply(request);
      }
    }

    model.addAttribute("suppliers", service.getAllSuppliers());
    model.addAttribute("supplys", service.getLastSupplyWithSum(10));
    model.addAttribute("productOne", nonNull(productOne) ? productOne : 0);
    model.addAttribute("productTwo", nonNull(productTwo) ? productTwo : 0);
    model.addAttribute("productThree", nonNull(productThree) ? productThree : 0);
    model.addAttribute("productFour", nonNull(productFour) ? productFour : 0);

    return "supply";
  }

}