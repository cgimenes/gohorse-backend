package com.xgh.test.model.command.financial.invoice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.model.command.financial.invoice.InvoiceId;
import com.xgh.model.command.financial.invoice.InvoiceStatus;
import com.xgh.model.command.financial.invoice.InvoiceType;
import com.xgh.model.command.financial.invoice.commands.GenerateInvoices;
import com.xgh.model.command.financial.invoice.commands.PayInvoice;
import com.xgh.model.command.financial.valueobjects.Operation;
import com.xgh.model.command.operational.appointment.Appointment;
import com.xgh.model.command.operational.appointment.commands.FinishAppointment;
import com.xgh.model.query.financial.invoice.Invoice;
import com.xgh.model.query.financial.invoice.InvoiceRepository;
import com.xgh.test.model.command.operational.appointment.AppointmentSampleData;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class InvoiceCommandControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AppointmentSampleData appointmentSampleData;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    public void register() {
        Appointment entity = appointmentSampleData.getSample();
        FinishAppointment command = new FinishAppointment(entity.getId(), new BigDecimal(20.50));

        CommandBus.dispatch(command);

        List<Invoice> invoices = invoiceRepository.findByOperationId(command.getId().getValue());

        assertEquals(1, invoices.size());
        assertEquals(Operation.APPOINTMENT.toString(), invoices.get(0).getOperationName());
        assertEquals(command.getId().getValue(), invoices.get(0).getOperationId());
        assertEquals(command.getPrice().setScale(2), invoices.get(0).getTotalValue());
        assertEquals(InvoiceType.INCOME.toString(), invoices.get(0).getType());
        assertNull(invoices.get(0).getPaymentDate());
        assertEquals(LocalDateTime.now().toLocalDate(), invoices.get(0).getIssueDate().toLocalDate());
        assertEquals(InvoiceStatus.CREATED.toString(), invoices.get(0).getStatus());
    }

    @Test
    public void pay() {
        Appointment entity = appointmentSampleData.getSample();
        GenerateInvoices generateInvoices = new GenerateInvoices(entity.getId(), Operation.APPOINTMENT, entity.getPrice());

        CommandBus.dispatch(generateInvoices);

        List<Invoice> invoices = invoiceRepository.findByOperationId(generateInvoices.getOperationId().getValue());

        PayInvoice payInvoice = new PayInvoice(new InvoiceId(invoices.get(0).getId()));

        ResponseEntity<Void> response = restTemplate.postForEntity("/invoices/pay", payInvoice, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        invoices = invoiceRepository.findByOperationId(generateInvoices.getOperationId().getValue());

        assertEquals(LocalDateTime.now().toLocalDate(), invoices.get(0).getPaymentDate().toLocalDate());
        assertEquals(InvoiceStatus.PAID.toString(), invoices.get(0).getStatus());
    }
}
