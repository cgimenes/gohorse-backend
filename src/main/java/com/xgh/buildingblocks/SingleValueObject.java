package com.xgh.buildingblocks;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonValue;

@Embeddable
@MappedSuperclass
public abstract class SingleValueObject<T> extends ValueObject {
	private static final long serialVersionUID = -1149703165422656566L;
	
	private T value;

	// TODO ver se dá pra tirar esse construtor fora
	protected SingleValueObject() {		
	}
	
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
