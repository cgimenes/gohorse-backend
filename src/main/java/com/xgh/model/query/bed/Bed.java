package com.xgh.model.query.bed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bed")
public final class Bed {
    @Id
    private UUID id;

    private String code;

    @JsonIgnore
    private Boolean deleted = false;

    protected Bed() {
    }

    public Bed(UUID id, String code, Boolean deleted) {
        this.id = id;
        this.code = code;
        this.deleted = deleted;
    }

    public UUID getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public Boolean isDeleted() {
        return this.deleted;
    }
}
