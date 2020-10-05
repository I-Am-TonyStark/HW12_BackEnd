package com.mamalimomen.domains;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.domains.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class User extends BaseEntity<Long> implements Comparable<User> {

    @Transient
    private static final long serialVersionUID = -3789293023680252260L;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true, updatable = false)
    private String nationalCode;

    @Column(nullable = false)
    private String password;

    @Embedded
    private Address address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws InValidDataException {
        if (!firstName.matches("\\w{3,}")) {
            throw new InValidDataException("FirstName");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws InValidDataException {
        if (!lastName.matches("\\w{3,}")) {
            throw new InValidDataException("LastName");
        }
        this.lastName = lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) throws InValidDataException {
        if (!nationalCode.matches("\\d{10}")) {
            throw new InValidDataException("National code");
        }
        this.nationalCode = nationalCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InValidDataException {
        if (!password.matches("[a-zA-Z0-9]{3,}")) {
            throw new InValidDataException("Password");
        }
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return this.hashCode() == user.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s%nAddress: %s%n", getFullName(), getAddress());
    }

    @Override
    public int compareTo(User u) {
        return this.getLastName().compareTo(u.getLastName());
    }
}
