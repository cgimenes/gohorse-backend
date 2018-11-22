package com.xgh.model.query.operational.appointment;

import com.xgh.infra.repository.BasicJpaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.xgh.model.command.operational.appointment.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends BasicJpaRepository<Appointment, UUID> {
    default List<Appointment> findByDate(LocalDate date) {
        return findByDeletedFalseAndDateTimeBetweenOrderByDateTimeAsc(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    default List<Appointment> findDatesWithAppointmentByMonth(LocalDate date) {
        LocalDateTime month = date.withDayOfMonth(1).atStartOfDay();
        return findByDeletedFalseAndDateTimeBetweenOrderByDateTimeAsc(month, month.plusMonths(1).withDayOfMonth(1).minusDays(1));
    }

    default List<Appointment> findDatesWithAppointmentBetweenTwelveMonths() {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        return findByDeletedFalseAndDateTimeBetweenOrderByDateTimeAsc(today.minusMonths(11).withDayOfMonth(1), today.plusMonths(1).minusDays(1));
    }

    Page<Appointment> findByStatus(Pageable pageable, String status);

    List<Appointment> findByDeletedFalseAndDateTimeBetweenOrderByDateTimeAsc(LocalDateTime from, LocalDateTime to);

    boolean existsByDateTime(LocalDateTime dateTime);

    boolean existsByDeletedFalseAndIdNotAndDateTime(UUID id, LocalDateTime dateTime);
}
