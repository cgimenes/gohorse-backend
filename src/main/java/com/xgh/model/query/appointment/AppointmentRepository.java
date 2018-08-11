package com.xgh.model.query.appointment;

import com.xgh.infra.repository.BasicJpaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends BasicJpaRepository<Appointment, UUID> {
    default List<Appointment> findByDate(LocalDate date) {
        return findByDateTimeBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    default List<Appointment> findDatesWithAppointmentByMonth(LocalDate date) {
        LocalDateTime month = date.withDayOfMonth(1).atStartOfDay();
        return findByDateTimeBetween(month, month.plusMonths(1).withDayOfMonth(1).minusDays(1));
    }

    List<Appointment> findByDateTimeBetween(LocalDateTime from, LocalDateTime to);
}
