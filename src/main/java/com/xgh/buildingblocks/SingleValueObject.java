package com.xgh.buildingblocks;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonValue;

@Embeddable
public abstract class SingleValueObject<T> extends ValueObject {
	private final T value;

    public SingleValueObject(T value) {
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
    
    @SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SingleValueObject<T> other = (SingleValueObject<T>) obj;
        if (value == null)
            return other.getValue() == null;
        return value.equals(other.getValue());
    }
}
