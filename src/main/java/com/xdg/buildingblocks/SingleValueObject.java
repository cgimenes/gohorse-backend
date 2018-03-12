package com.xdg.buildingblocks;

public abstract class SingleValueObject<T> extends ValueObject {
    private final T value;

    public SingleValueObject(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SingleValueObject other = (SingleValueObject) obj;
        if (value == null)
            return other.getValue() == null;
        return value.equals(other.getValue());
    }
}
