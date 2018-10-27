package com.xgh.model.query.financial.invoice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    default List<Invoice> findByPaymentYear(Year year) {
        LocalDateTime startOfYear = LocalDate
                .now()
                .withYear(year.getValue())
                .withDayOfMonth(1).withMonth(1).atStartOfDay();
        return findByPaymentDateBetween(startOfYear, startOfYear.plusYears(1).minusDays(1));
    }

    List<Invoice> findByPaymentDateBetween(LocalDateTime from, LocalDateTime to);
}
