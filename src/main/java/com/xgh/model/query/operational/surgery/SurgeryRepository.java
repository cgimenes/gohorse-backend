package com.xgh.model.query.operational.surgery;

import com.xgh.infra.repository.BasicJpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface SurgeryRepository extends BasicJpaRepository<Surgery, UUID> {
	default List<Surgery> findByDate(LocalDate date) {
		return findByDeletedFalseAndDateTimeBetweenOrderByDateTimeAsc(date.atStartOfDay(),
				date.plusDays(1).atStartOfDay());
	}

	default List<Surgery> findDatesWithSurgeryByMonth(LocalDate date) {
		LocalDateTime month = date.withDayOfMonth(1).atStartOfDay();
		return findByDeletedFalseAndDateTimeBetweenOrderByDateTimeAsc(month,
				month.plusMonths(1).withDayOfMonth(1).minusDays(1));
	}

	List<Surgery> findByDeletedFalseAndDateTimeBetweenOrderByDateTimeAsc(LocalDateTime from, LocalDateTime to);

	boolean existsByDateTime(LocalDateTime dateTime);
}
