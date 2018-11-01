package com.xgh.model.command.financial.account.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.financial.account.Account;
import com.xgh.model.command.financial.account.commands.CreateAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountCreation implements CommandHandler<CreateAccount> {
    private final EventStore eventStore;

    @Autowired
    public AccountCreation(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(CreateAccount command) {
        Account account = new Account();
        account.create(command.getId(), command.getAccountType(), command.getAccountOwner());
        eventStore.push(account);
    }
}
