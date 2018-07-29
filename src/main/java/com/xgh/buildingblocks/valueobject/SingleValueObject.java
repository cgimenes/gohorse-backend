package com.xgh.buildingblocks.valueobject;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;

public abstract class SingleValueObject<T> implements ValueObject {
    private final T value;

    protected SingleValueObject() {
        this.value = null;
    }

    protected SingleValueObject(T value) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("O valor não pode ser nulo para o tipo: %s", this.getClass().getName()));
        }
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SingleValueObject<T> other = (SingleValueObject<T>) obj;
        if (value == null) {
            return other.getValue() == null;
        }
        return value.equals(other.getValue());
    }
}
