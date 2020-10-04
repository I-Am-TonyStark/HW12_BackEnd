package com.mamalimomen.domains;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.domains.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_account", catalog = "HW12_One", schema = "HW12_One")
@NamedQueries({
        @NamedQuery(
                name = "Account.finaAll",
                query = "SELECT a FROM Account a"),
        @NamedQuery(
                name = "Account.findAllActive",
                query = "SELECT a FROM Account a WHERE a.active = TRUE"),
        @NamedQuery(
                name = "Account.selectManyByCustomerID",
                query = "SELECT a FROM Account a JOIN a.ownerCustomer w WHERE w.id = ?1")
})
public class Account extends BaseEntity<Long> implements Comparable<Account> {

    @Transient
    private static final long serialVersionUID = 5216996780864192251L;

    @Transient
    private static long count = 0;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column(name = "balance", nullable = false, columnDefinition = "DECIMAL", precision = 2, scale = 2)
    private BigDecimal balance;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Customer ownerCustomer;

    @Temporal(TemporalType.DATE)
    @Column(updatable = false, nullable = false)
    private Date openDate;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_card", nullable = false, unique = true)
    private CreditCard activeCard;

    public Account() {
        this.setId(count);
        count++;
    }

    @Override
    public int compareTo(Account a) {
        return this.getBalance().compareTo(a.getBalance());
    }

    @Override
    public String toString() {
        return String.format("%d.%nBalance: %s%n", getId(), getBalance());
    }

    public void printCompleteInformation() {
        System.out.printf("%n%d.%nIs Active : %b%nBalance : %s%nOwner : %s%nOpen Date : %s%nCredit Card : %s%n%n",
                getId(), getActive(), getBalance(), getOwnerCustomer(), getOpenDate(), getActiveCard());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return this.hashCode() == account.hashCode();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalanceString(String balanceString) throws InValidDataException {
        if (!balanceString.matches("[a-zA-Z\\s.,&\\d\\(\\)]{5,}")) {
            throw new InValidDataException("Balance");
        }
        this.setBalance(BigDecimal.valueOf(Double.valueOf(balanceString)));
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Customer getOwnerCustomer() {
        return ownerCustomer;
    }

    public void setOwnerCustomer(Customer ownerCustomer) {
        this.ownerCustomer = ownerCustomer;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public CreditCard getActiveCard() {
        return activeCard;
    }

    public void setActiveCard(CreditCard activeCard) {
        this.activeCard = activeCard;
    }
}
