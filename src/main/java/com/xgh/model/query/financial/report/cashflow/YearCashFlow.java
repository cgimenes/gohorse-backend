package com.xgh.model.query.financial.report.cashflow;

import com.xgh.model.query.financial.invoice.Invoice;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Map;

// TODO criar uma tabela para representar esse modelo, atualizando o mesmo quando necessário através de um EventHandler
public class YearCashFlow {
    private Year year;
    private YearBalances initialBalances;
    private Map<String, YearBalances> incomes;
    private YearBalances totalIncomesBalances;
    private Map<String, YearBalances> expense;
    private YearBalances totalExpensesBalances;
    private YearBalances totalPeriodBalances;
    private YearBalances totalBalances;

    public YearCashFlow(Year year, BigDecimal previousYearFinalBalance, List<Invoice> invoices) {
        this.year = year;
        initialBalances = new YearBalances().setBalance(Month.JANUARY, previousYearFinalBalance);
        for (Invoice invoice : invoices) {
            switch (invoice.getType()) {
                case "INCOME":

                    break;
                case "EXPENSE":
                    break;
            }
        }
    }
}
