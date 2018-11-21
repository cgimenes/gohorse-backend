package com.xgh.model.query.operational.internment;

import com.xgh.infra.repository.BasicJpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface InternmentRepository extends BasicJpaRepository<Internment, UUID> {

    default List<Internment> findDatesWithAppointmentBetweenTwelveMonths() {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        return findByDeletedFalseAndBusyAtBetweenOrderByBusyAtAsc(today.minusMonths(11).withDayOfMonth(1), today.plusMonths(1).minusDays(1));
    }

    List<Internment> findByDeletedFalseAndBusyAtBetweenOrderByBusyAtAsc(LocalDateTime from, LocalDateTime to);
}
