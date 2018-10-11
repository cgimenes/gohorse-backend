package com.xgh.model.command.financial.invoice.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.financial.account.Account;
import com.xgh.model.command.financial.account.AccountId;
import com.xgh.model.command.financial.invoice.Invoice;
import com.xgh.model.command.financial.invoice.InvoiceId;
import com.xgh.model.command.financial.invoice.commands.GenerateInvoices;
import com.xgh.model.command.financial.valueobjects.Transaction;
import com.xgh.model.command.financial.valueobjects.Operation;
import com.xgh.model.command.operational.animal.Animal;
import com.xgh.model.command.operational.appointment.Appointment;
import com.xgh.model.command.operational.internment.Internment;
import com.xgh.model.query.financial.account.AccountRepository;
import com.xgh.model.query.operational.veterinary.Veterinary;
import com.xgh.model.query.operational.veterinary.VeterinaryRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoicesGeneration implements CommandHandler<GenerateInvoices> {
    private EventStore eventStore;
    private VeterinaryRepository veterinaryRepository;
    private AccountRepository accountRepository;

    @Autowired
    public InvoicesGeneration(EventStore eventStore, VeterinaryRepository veterinaryRepository, AccountRepository accountRepository) {
        this.eventStore = eventStore;
        this.veterinaryRepository = veterinaryRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void execute(GenerateInvoices command) {
        switch (command.getOperation()) {
            case SURGERY:
                generateSurgeryInvoices(command);
                break;
            case SHOPPING:
                // TODO
                break;
            case INTERNMENT:
                generateInternmentInvoices(command);
                break;
            case APPOINTMENT:
                generateAppointmentInvoices(command);
                break;
        }
    }

    private void generateInternmentInvoices(GenerateInvoices command) {
        Internment internment = eventStore.pull(Internment.class, command.getOperationId());
        Animal animal = eventStore.pull(Animal.class, internment.getAnimalId());
        Account payer = getAccountByOwner(animal.getOwner().getValue());

        List<Veterinary> veterinaries = veterinaryRepository.findAll();

        BigDecimal splittedValue = command.getValue().divide(new BigDecimal(veterinaries.size()), 2, BigDecimal.ROUND_HALF_DOWN);

        List<Transaction> transactions = new ArrayList<>();
        for (Veterinary veterinary : veterinaries) {
            Account receiver = getAccountByOwner(veterinary.getId());
            transactions.add(new Transaction(payer.getId(), receiver.getId(), splittedValue));
        }

        createInvoice(command, transactions);
    }

    private void createInvoice(GenerateInvoices command, List<Transaction> transactions) {
        Invoice invoice = new Invoice();
        invoice.register(
                new InvoiceId(),
                LocalDateTime.now(),
                command.getValue(),
                Operation.INTERNMENT,
                command.getOperationId(),
                transactions
        );
        eventStore.push(invoice);
    }

    private Account getAccountByOwner(UUID ownerId) {
        return eventStore.pull(Account.class, new AccountId(accountRepository.findByOwnerId(ownerId).getId()));
    }

    private void generateSurgeryInvoices(GenerateInvoices command) {
        // RECEBER DO CLIENTE
        // NA CIRURGIA DIVIDO ENTRE OS RESPONSÁVEIS QUE ESTÃO NA ENTIDADE
        // ADICIONO UM A PAGAR PRA UM ANESTESISTA
    }

    private void generateAppointmentInvoices(GenerateInvoices command) {
        Appointment appointment = eventStore.pull(Appointment.class, command.getOperationId());
        Animal animal = eventStore.pull(Animal.class, appointment.getAnimal());
        Account payer = getAccountByOwner(animal.getOwner().getValue());

        Account receiver = getAccountByOwner(appointment.getVeterinary().getValue());
        List<Transaction> transactions = Arrays.asList(new Transaction(payer.getId(), receiver.getId(), command.getValue()));

        createInvoice(command, transactions);
    }
}
