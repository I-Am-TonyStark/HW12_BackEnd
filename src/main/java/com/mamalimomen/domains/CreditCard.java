package com.mamalimomen.domains;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.controllers.utilities.SecurityManager;
import com.mamalimomen.base.domains.BaseEntity;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@SelectBeforeUpdate
@Table(name = "tbl_card", catalog = "HW12_One", schema = "HW12_One")
@NamedQueries({
        @NamedQuery(
                name = "CreditCard.findAll",
                query = "SELECT cc FROM CreditCard cc"),
        @NamedQuery(
                name = "CreditCard.findOneByNumber",
                query = "SELECT cc FROM CreditCard cc WHERE cc.cardNumber = ?1")
})
public class CreditCard extends BaseEntity<Long> implements Comparable<CreditCard> {

    @Transient
    private static final long serialVersionUID = -1888484344606840488L;

    @Transient
    private static long count = 1;

    @Column(name = "first_password", nullable = false)
    private String firstPassword;

    @Column(name = "second_password")
    private String secondPassword;

    @Column(name = "cvv2", nullable = false, updatable = false)
    private String cvv2;

    @Temporal(TemporalType.DATE)
    @Column(name = "expire_date", nullable = false, updatable = false)
    private Date expireDate;

    @Column(name = "card_number", nullable = false, updatable = false, unique = true)
    private String cardNumber;

    public CreditCard() {
        this.setId(count);
        count++;
    }

    public String getFirstPassword() {
        return firstPassword;
    }

    public void setFirstPassword(String firstPassword) throws InValidDataException {
        if (!firstPassword.matches("\\d{4}")) {
            throw new InValidDataException("First password");
        }
        this.firstPassword = SecurityManager.getPasswordHash(firstPassword);
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) throws InValidDataException {
        if (!secondPassword.matches("\\d{4,}")) {
            throw new InValidDataException("Second password");
        }

        this.secondPassword = SecurityManager.getPasswordHash(secondPassword);
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) throws InValidDataException {
        if (!firstPassword.matches("\\d{4}")) {
            throw new InValidDataException("CVV2");
        }
        this.cvv2 = cvv2;
    }

    public void setExpireDateString(String expireDate) throws InValidDataException {
        if (!expireDate.matches("[2][0][2-9][0-9]/(0[1-9]|1[0-2])")) {
            throw new InValidDataException("Expire date");
        }
        String[] tempArray = expireDate.split("/");
        setExpireDate(new Date(Integer.parseInt(tempArray[0]) - 1900, Integer.parseInt(tempArray[1]), 0));
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
        if (!cardNumber.matches("\\d{16}")) {
            throw new InValidDataException("Card number");
        }
        this.cardNumber = cardNumber;
    }

    @Override
    public int compareTo(CreditCard cc) {
        return this.getCardNumber().compareTo(cc.getCardNumber());
    }

    @Override
    public String toString() {
        return String.format("Card number: %s%nExpire date: %s%n", getCardNumber(), getExpireDate());
    }

    public void printCompleteInformation() {
        System.out.printf("%nCard number: %d%nExpire date: %b%nCVV2: %s%n%n",
                getCardNumber(), getExpireDate(), getCvv2());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CreditCard cc = (CreditCard) obj;
        return this.hashCode() == cc.hashCode();
    }
}
