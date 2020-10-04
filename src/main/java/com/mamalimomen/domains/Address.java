package com.mamalimomen.domains;

import com.mamalimomen.base.controllers.utilities.InValidDataException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String avenue;

    @Column(name = "postal_code",nullable = false)
    private String postalCode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws InValidDataException {
        if (!country.matches("\\w{3,}")) {
            throw new InValidDataException("Country name");
        }
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws InValidDataException {
        if (!city.matches("\\w{3,}")) {
            throw new InValidDataException("City name");
        }
        this.city = city;
    }

    public String getAvenue() {
        return avenue;
    }

    public void setAvenue(String avenue) throws InValidDataException {
        if (!avenue.matches("(\\w\\d*)+\\'?(\\w\\s*){2,}")) {
            throw new InValidDataException("Avenue name");
        }
        this.avenue = avenue;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) throws InValidDataException {
        if (!postalCode.matches("\\d{10}")) {
            throw new InValidDataException("Postal code");
        }
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s%n", getCountry(), getCity(), getAvenue(), getPostalCode());
    }
}
