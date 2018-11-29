package com.xgh.model.query.operational.address.postalcode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "postal_code")
public class PostalCode {
    @Id
    @Column(name = "id")
    private String code;

    @Column(name = "street_name")
    private String streetName;

    private String neighbourhood;

    private String city;

    private String state;

    private String country;

    protected PostalCode() {
    }

    public PostalCode(String code, String streetName, String neighbourhood, String city,
                      String state, String country) {
        this.code = code;
        this.streetName = streetName;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getCode() {
        return code;
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
