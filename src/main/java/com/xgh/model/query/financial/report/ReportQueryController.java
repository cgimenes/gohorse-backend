package com.xgh.model.query.financial.report;

import com.xgh.model.query.financial.report.cashflow.YearCashFlow;
import java.time.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportQueryController {
    private ReportQueryService service;

    @Autowired
    public ReportQueryController(ReportQueryService service) {
        this.service = service;
    }

    @GetMapping("/cashflow/{year}")
    public ResponseEntity<YearCashFlow> cashFlow(@PathVariable("year") Year year) {
        YearCashFlow cashFlow = service.buildCashFlow(year);
        return ResponseEntity.ok(cashFlow);
    }
}
