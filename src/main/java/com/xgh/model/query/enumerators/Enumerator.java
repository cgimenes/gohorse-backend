package com.xgh.model.query.enumerators;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enumerators")
public final class Enumerator {
    @Id
    private UUID id;

    private String group;

    private String name;

    @JsonIgnore
    private Boolean deleted = false;

    protected Enumerator() {
    }

    public Enumerator(UUID id, String group, String name, Boolean deleted) {
        this.id = id;
        this.group = group;
        this.name = name;
        this.deleted = deleted;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }
}
