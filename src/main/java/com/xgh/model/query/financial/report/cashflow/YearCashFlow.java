package com.xgh.model.query.financial.report.cashflow;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toMap;

import com.xgh.model.query.financial.invoice.Invoice;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO criar uma tabela para representar esse modelo, atualizando o mesmo quando necessário através de um EventHandler
public class YearCashFlow {
    private final Year year;
    private final Map<Month, BigDecimal> initialBalances = new HashMap<>();
    private final Map<String, Map<Month, BigDecimal>> typeTotalBalances;
    private final Map<Month, BigDecimal> totalPeriodBalances = new HashMap<>();
    private final Map<Month, BigDecimal> totalBalances = new HashMap<>();
    private final Map<String, Map<String, Map<Month, BigDecimal>>> summarized;

    public YearCashFlow(Year year, BigDecimal previousYearFinalBalance, List<Invoice> invoices) {
        this.year = year;
        initialBalances.put(Month.JANUARY, previousYearFinalBalance);

        summarized = summarize(invoices);

        typeTotalBalances = calculateTypeTotalBalances();

        for (Month month : Month.values()) {
            Map<Month, BigDecimal> incomes = typeTotalBalances.getOrDefault("INCOME", new HashMap<>());
            Map<Month, BigDecimal> expenses = typeTotalBalances.getOrDefault("EXPENSE", new HashMap<>());

            totalPeriodBalances.put(month,
                    incomes.getOrDefault(month, BigDecimal.ZERO).subtract(expenses.getOrDefault(month, BigDecimal.ZERO)));

            totalBalances.put(month, initialBalances.get(month).add(totalPeriodBalances.get(month)));
            // Verifica se o ciclo dos meses não recomeçou, para não atualizar o balanço inicial de janeiro que veio por parâmetro
            if (month.plus(1) != Month.JANUARY) {
                initialBalances.put(month.plus(1), totalBalances.get(month));
            }
        }
    }

    private Map<String, Map<String, Map<Month, BigDecimal>>> summarize(List<Invoice> invoices) {
        return invoices
                .stream()
                .collect(groupingBy(
                        Invoice::getType,
                        groupingBy(
                                Invoice::getOperation,
                                groupingBy(
                                        (Invoice invoice) -> invoice.getPaymentDate().getMonth(),
                                        reducing(BigDecimal.ZERO, Invoice::getTotalValue, BigDecimal::add)))));
    }

    private Map<String, Map<Month, BigDecimal>> calculateTypeTotalBalances() {
        return summarized.entrySet()
                .stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        stringMapEntry -> stringMapEntry.getValue().values().stream().flatMap(map -> map.entrySet().stream())
                                .collect(groupingBy(
                                        Map.Entry::getKey,
                                        reducing(BigDecimal.ZERO, Map.Entry::getValue, BigDecimal::add)))));
    }

    public Map<Month, BigDecimal> getInitialBalances() {
        return initialBalances;
    }

    public Map<String, Map<String, Map<Month, BigDecimal>>> getSummarized() {
        return summarized;
    }

    public Year getYear() {
        return year;
    }

    public Map<Month, BigDecimal> getTotalPeriodBalances() {
        return totalPeriodBalances;
    }

    public Map<Month, BigDecimal> getTotalBalances() {
        return totalBalances;
    }

    public Map<String, Map<Month, BigDecimal>> getTypeTotalBalances() {
        return typeTotalBalances;
    }
}
