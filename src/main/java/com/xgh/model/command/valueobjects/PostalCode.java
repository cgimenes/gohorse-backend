package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.ValueObject;
import com.xgh.exceptions.NullMandatoryArgumentException;

public class PostalCode implements ValueObject {
    private static final long serialVersionUID = 2103152608640452626L;

    private String code;
    private String streetType;
    private String streetName;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;

    protected PostalCode() {}

    public PostalCode(String code, String streetType, String streetName, String neighbourhood, String city,
            String state, String country) {
        if (code == null) {
            throw new NullMandatoryArgumentException("código");
        }

        if (streetType == null) {
            throw new NullMandatoryArgumentException("tipo da rua");
        }

        if (streetName == null) {
            throw new NullMandatoryArgumentException("nome da rua");
        }

        if (neighbourhood == null) {
            throw new NullMandatoryArgumentException("bairro");
        }

        if (city == null) {
            throw new NullMandatoryArgumentException("cidade");
        }

        if (state == null) {
            throw new NullMandatoryArgumentException("estado");
        }

        if (country == null) {
            throw new NullMandatoryArgumentException("país");
        }

        if (!code.matches("[0-9]{5}-[0-9]{3}")) {
            throw new IllegalArgumentException("O código do CEP deve estar no formato 99999-999");
        }
        
        // TODO converter para enum
        if (!streetType.equals("Avenida") && !streetType.equals("Rua")) {
            throw new IllegalArgumentException("O tipo da rua deve ser: 'Rua' ou 'Avenida'");
        }

        this.code = code;
        this.streetType = streetType;
        this.streetName = streetName;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public String getStreetType() {
        return streetType;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
}
