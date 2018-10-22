package com.xgh.model.query.financial.report.cashflow;

import com.xgh.model.query.financial.invoice.Invoice;
import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

// TODO criar uma tabela para representar esse modelo, atualizando o mesmo quando necessário através de um EventHandler
public class YearCashFlow {
    private Year year;
    private YearBalances initialBalances;
    private List<YearBalances> incomes;
    private YearBalances totalIncomesBalances;
    private List<YearBalances> expense;
    private YearBalances totalExpensesBalances;
    private YearBalances totalPeriodBalances;
    private YearBalances totalBalances;

    public YearCashFlow(Year year, BigDecimal previousYearFinalBalance, List<Invoice> invoices) {
        this.year = year;


    }
}
