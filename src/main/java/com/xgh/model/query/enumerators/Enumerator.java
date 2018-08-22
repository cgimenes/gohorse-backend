package com.xgh.model.query.enumerators;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enumerator")
public final class Enumerator {
    @Id
    private UUID id;

    private String kind;

    private String name;

    @JsonIgnore
    private Boolean deleted = false;

    protected Enumerator() {
    }

    public Enumerator(UUID id, String kind, String name, Boolean deleted) {
        this.id = id;
        this.kind = kind;
        this.name = name;
        this.deleted = deleted;
    }

    public String getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }
}
