package com.xgh.model.command.financial.transaction;

import com.xgh.model.command.financial.account.Account;
import com.xgh.model.command.financial.invoice.Invoice;
import java.util.Currency;

public class Transaction {
    private Invoice invoice;
    private Account destination;
    private Account origin;
    private Currency value;
}
