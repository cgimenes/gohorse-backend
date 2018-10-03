package com.xgh.eventhandlers.financial;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.financial.account.Account;
import com.xgh.model.command.financial.account.events.AccountWasCreated;
import com.xgh.model.command.financial.account.events.AccountWasDeleted;
import com.xgh.model.query.financial.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountProjector implements EventHandler {
    private final EventStore eventStore;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountProjector(EventStore eventStore, AccountRepository accountRepository) {
        this.eventStore = eventStore;
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean isSubscribedTo(Event event) {
        return event instanceof AccountWasDeleted
                || event instanceof AccountWasCreated;
    }

    @Override
    public void execute(Event event) {
        Account entity = eventStore.pull(Account.class, ((EntityEvent<?>) event).getEntityId());

        com.xgh.model.query.financial.account.Account projection = new com.xgh.model.query.financial.account.Account(
                entity.getId().getValue(),
                entity.getAccountOwner().getValue(),
                entity.getAccountType().toString(),
                entity.isDeleted());

        accountRepository.save(projection);
    }
}
