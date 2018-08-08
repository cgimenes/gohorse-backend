package com.xgh.model.query.appointment;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends BasicJpaRepository<Appointment, UUID> {
}
