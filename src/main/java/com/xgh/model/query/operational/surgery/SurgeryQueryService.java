package com.xgh.model.query.operational.surgery;

import com.xgh.infra.service.BasicQueryService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurgeryQueryService extends BasicQueryService<Surgery, SurgeryRepository> {
    @Autowired
    protected SurgeryQueryService(SurgeryRepository repository) {
        super(repository);
    }

    public List<Surgery> findByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public List<LocalDate> findDatesWithAppointmentByMonth(LocalDate month) {
        return repository.findDatesWithSurgeryByMonth(month)
                .stream()
                .map(surgery -> surgery.getDateTime().toLocalDate())
                .collect(Collectors.toList());
    }
}
