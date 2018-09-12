package com.xgh.model.command.financial.invoice.commandhandlers;

import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.buildingblocks.event.EventBus;
import com.xgh.model.command.financial.invoice.commands.GenerateInvoices;
import com.xgh.model.command.financial.invoice.events.InvocesWereGenerated;
import org.springframework.stereotype.Component;

@Component
public class InvoiceGeneration implements CommandHandler<GenerateInvoices> {
    @Override
    public void execute(GenerateInvoices command) {
        switch (command.getOperation()) {
            case SURGERY:
                generateSurgeryInvoices(command);
                break;
            case SHOPPING:
                generateShoppingInvoices(command);
                break;
            case INTERNMENT:
                generateInternmentInvoices(command);
                break;
            case APPOINTMENT:
                generateAppointmentInvoices(command);
                break;
        }
        EventBus.dispatch(new InvocesWereGenerated());
    }

    private void generateInternmentInvoices(GenerateInvoices command) {
    }

    private void generateShoppingInvoices(GenerateInvoices command) {
    }

    private void generateSurgeryInvoices(GenerateInvoices command) {
    }

    private void generateAppointmentInvoices(GenerateInvoices command) {
    }
}
