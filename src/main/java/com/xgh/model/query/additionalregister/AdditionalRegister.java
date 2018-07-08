package com.xgh.model.query.additionalregister;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "additional_registers")
public final class AdditionalRegister {
    @Id
    private UUID id;

    private String type;

    private String value;

    @JsonIgnore
    private Boolean deleted = false;

    protected AdditionalRegister() {
    }

    public AdditionalRegister(UUID id, String type, String value, Boolean deleted) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.deleted = deleted;
    }

    public String getRegisterType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
