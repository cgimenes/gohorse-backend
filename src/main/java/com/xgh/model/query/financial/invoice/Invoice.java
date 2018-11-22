package com.xgh.model.query.financial.invoice;

import com.xgh.ApplicationContextProvider;
import com.xgh.model.query.operational.appointment.AppointmentRepository;
import com.xgh.model.query.operational.internment.InternmentRepository;
import com.xgh.model.query.operational.surgery.SurgeryRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.repository.CrudRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    private UUID id;

    private LocalDateTime issueDate;
    private LocalDateTime paymentDate;
    private BigDecimal totalValue;
    private String operation;
    private UUID operationId;
    private String status;
    private String type;

    protected Invoice() {
    }

    public Invoice(UUID id, LocalDateTime issueDate, LocalDateTime paymentDate, BigDecimal totalValue, String operation, UUID operationId, String status, String type) {
        this.id = id;
        this.issueDate = issueDate;
        this.paymentDate = paymentDate;
        this.totalValue = totalValue;
        this.operation = operation;
        this.operationId = operationId;
        this.status = status;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public String getOperationName() {
        return operation;
    }

    public UUID getOperationId() {
        return operationId;
    }

    public Object getOperation() {
        Class<? extends CrudRepository> repository;
        switch (operation) {
            case "SURGERY":
                repository = SurgeryRepository.class;
                break;
            case "INTERNMENT":
                repository = InternmentRepository.class;
                break;
            case "APPOINTMENT":
                repository = AppointmentRepository.class;
                break;
            default:
                throw new NotImplementedException();
        }
        return ApplicationContextProvider.getApplicationContext().getBean(repository).findById(operationId);
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getType() {
        return type;
    }
}
