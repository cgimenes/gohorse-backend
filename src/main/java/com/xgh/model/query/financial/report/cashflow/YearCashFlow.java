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
    private final Map<String, BigDecimal> initialBalances = new HashMap<>();
    private final Map<String, Map<String, BigDecimal>> typeTotalBalances;
    private final Map<String, BigDecimal> totalPeriodBalances = new HashMap<>();
    private final Map<String, BigDecimal> totalBalances = new HashMap<>();
    private final Map<String, Map<String, Map<String, BigDecimal>>> summarized;

    public YearCashFlow(Year year, BigDecimal previousYearFinalBalance, List<Invoice> invoices) {
        this.year = year;
        initialBalances.put(Month.JANUARY.toString().toLowerCase(), previousYearFinalBalance);

        summarized = summarize(invoices);

        typeTotalBalances = calculateTypeTotalBalances();

        for (Month monthEnum : Month.values()) {
            String month = monthEnum.toString().toLowerCase();

            Map<String, BigDecimal> incomes = typeTotalBalances.getOrDefault("income", new HashMap<>());
            Map<String, BigDecimal> expenses = typeTotalBalances.getOrDefault("expense", new HashMap<>());

            totalPeriodBalances.put(month,
                    incomes.getOrDefault(month, BigDecimal.ZERO).subtract(expenses.getOrDefault(month, BigDecimal.ZERO)));

            totalBalances.put(month, initialBalances.get(month).add(totalPeriodBalances.get(month)));
            // Verifica se o ciclo dos meses não recomeçou, para não atualizar o balanço inicial de janeiro que veio por parâmetro
            if (monthEnum.plus(1) != Month.JANUARY) {
                initialBalances.put(monthEnum.plus(1).toString().toLowerCase(), totalBalances.get(month));
            }
        }
    }

    private Map<String, Map<String, Map<String, BigDecimal>>> summarize(List<Invoice> invoices) {
        return invoices
                .stream()
                .collect(groupingBy(
                        (invoice) -> invoice.getType().toLowerCase(),
                        groupingBy(
                                (invoice) -> invoice.getOperationName().toLowerCase(),
                                groupingBy(
                                        (Invoice invoice) -> invoice.getPaymentDate().getMonth().toString().toLowerCase(),
                                        reducing(BigDecimal.ZERO, Invoice::getTotalValue, BigDecimal::add)))));
    }

    private Map<String, Map<String, BigDecimal>> calculateTypeTotalBalances() {
        return summarized.entrySet()
                .stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        stringMapEntry -> stringMapEntry.getValue().values().stream().flatMap(map -> map.entrySet().stream())
                                .collect(groupingBy(
                                        Map.Entry::getKey,
                                        reducing(BigDecimal.ZERO, Map.Entry::getValue, BigDecimal::add)))));
    }

    public Map<String, BigDecimal> getInitialBalances() {
        return initialBalances;
    }

    public Map<String, Map<String, Map<String, BigDecimal>>> getSummarized() {
        return summarized;
    }

    public Year getYear() {
        return year;
    }

    public Map<String, BigDecimal> getTotalPeriodBalances() {
        return totalPeriodBalances;
    }

    public Map<String, BigDecimal> getTotalBalances() {
        return totalBalances;
    }

    public Map<String, Map<String, BigDecimal>> getTypeTotalBalances() {
        return typeTotalBalances;
    }
}
