package com.xgh.model.query.financial.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
    @Id
    private UUID id;

    private UUID ownerId;

    private String accountType;

    @JsonIgnore
    private Boolean deleted;

    protected Account() {
    }

    public Account(UUID id, UUID ownerId, String accountType, Boolean deleted) {
        this.id = id;
        this.ownerId = ownerId;
        this.accountType = accountType;
        this.deleted = deleted;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
