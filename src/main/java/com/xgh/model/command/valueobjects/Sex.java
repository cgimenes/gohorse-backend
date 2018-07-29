package com.xgh.model.command.valueobjects;

public enum Sex {
    MALE, FEMALE;

    public Character asCharacter() {
        if (this.equals(Sex.MALE)) {
            return 'M';
        }
        return 'F';
    }

    public String toString() {
        return asCharacter().toString();
    }
}
