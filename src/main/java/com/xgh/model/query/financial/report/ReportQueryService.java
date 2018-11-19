package com.xgh.model.query.financial.report;

import com.xgh.model.query.financial.invoice.Invoice;
import com.xgh.model.query.financial.invoice.InvoiceRepository;
import com.xgh.model.query.financial.report.cashflow.YearCashFlow;
import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportQueryService {
    @Autowired
    private InvoiceRepository repository;

    public YearCashFlow buildCashFlow(Year year) {
        List<Invoice> invoices = repository.findPaidByPaymentYear(year);
        return new YearCashFlow(year, new BigDecimal(0), invoices);
    }
}
