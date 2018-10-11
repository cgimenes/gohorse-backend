package com.xgh.eventhandlers.financial;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.financial.invoice.events.InvoiceWasCreated;
import com.xgh.model.command.financial.invoice.events.InvoiceWasPaid;
import com.xgh.model.query.financial.account.AccountRepository;
import com.xgh.model.query.financial.invoice.Invoice;
import com.xgh.model.query.financial.invoice.InvoiceRepository;
import com.xgh.model.query.financial.transaction.Transaction;
import com.xgh.model.query.financial.transaction.TransactionRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceProjector implements EventHandler {
    private final EventStore eventStore;
    private final InvoiceRepository invoiceRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public InvoiceProjector(EventStore eventStore, InvoiceRepository invoiceRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.eventStore = eventStore;
        this.invoiceRepository = invoiceRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public boolean isSubscribedTo(Event event) {
        return event instanceof InvoiceWasCreated
                || event instanceof InvoiceWasPaid;
    }

    @Override
    public void execute(Event event) {
        com.xgh.model.command.financial.invoice.Invoice entity = eventStore.pull(
                com.xgh.model.command.financial.invoice.Invoice.class, ((EntityEvent<?>) event).getEntityId());

        Invoice projection = new Invoice(
                entity.getId().getValue(),
                entity.getIssueDate(),
                entity.getTotalValue(),
                entity.getOperation().toString(),
                entity.getOperationId().getValue(),
                entity.getStatus().toString());

        invoiceRepository.save(projection);

        projectTransactions(projection, entity.getTransactions());
    }

    private void projectTransactions(Invoice invoice, List<com.xgh.model.command.financial.valueobjects.Transaction> transactions) {
        for (com.xgh.model.command.financial.valueobjects.Transaction transaction : transactions) {
            Transaction projection = new Transaction(
                    UUID.randomUUID(),
                    invoice,
                    accountRepository.getOne(transaction.getOrigin().getValue()),
                    accountRepository.getOne(transaction.getDestination().getValue()),
                    transaction.getValue());
            transactionRepository.save(projection);
        }
    }
}
