package com.mamalimomen.domains;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.domains.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
public class CreditCard extends BaseEntity<Long> implements Serializable {

    @Column(name = "first_password", nullable = false)
    private String firstPassword;

    @Column(name = "second_password")
    private String secondPassword;

    @Column(name = "cvv2", nullable = false, updatable = false, unique = true)
    private String cvv2;

    @Column(name = "expire_date", nullable = false, updatable = false)
    private Date expireDate;

    @Column(name = "card_number", nullable = false, updatable = false, unique = true)
    private String cardNumber;

    public String getFirstPassword() {
        return firstPassword;
    }

    public void setFirstPassword(String firstPassword) throws InValidDataException {
        if (!firstPassword.matches("\\d{4}")) {
            throw new InValidDataException("Country");
        }
        this.firstPassword = firstPassword;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) throws InValidDataException {
        if (!firstPassword.matches("\\d{12}|\\d{16}")) {
            throw new InValidDataException("Country");
        }
        this.cardNumber = cardNumber;
    }
}
