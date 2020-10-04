package com.mamalimomen.domains;

import com.mamalimomen.base.domains.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class User extends BaseEntity<Long> implements Comparable<User> {

    @Transient
    private static final long serialVersionUID = -3789293023680252260L;

    @Transient
    private static long count = 4;

    @Column(nullable = false, updatable = false)
    private String firstName;

    @Column(nullable = false, updatable = false)
    private String lastName;

    @Column(nullable = false, unique = true, updatable = false)
    private String userName;

    @Column(nullable = false, unique = true, updatable = false)
    private String nationalCode;

    @Temporal(TemporalType.DATE)
    @Column(updatable = false)
    private Date birthDay;

    @Column(nullable = false)
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @Embedded
    private Address address;

    public User() {
        this.setId(count);
        count++;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) throws InValidDataException {
        if (!userName.matches("(\\w\\d*){3,}")) {
            throw new InValidDataException("Username");
        }
        this.userName = userName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) throws InValidDataException {
        if (!nationalCode.matches("\\d{10}")) {
            throw new InValidDataException("National Code");
        }
        this.nationalCode = nationalCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setStringBirthDay(String birthDay) throws InValidDataException {
        if (!birthDay.matches("[01][0-9]{3}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])")) {
            throw new InValidDataException("Birthday");
        }
        String[] tempArray = birthDay.split("-");
        setBirthDay(new Date(Integer.parseInt(tempArray[0]) - 1900, Integer.parseInt(tempArray[1]), Integer.parseInt(tempArray[2])));
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        return String.format("%s: %s", getUserName(), getRole());
    }

    @Override
    public int compareTo(User u) {
        return this.getUserName().compareTo(u.getUserName());
    }
}
