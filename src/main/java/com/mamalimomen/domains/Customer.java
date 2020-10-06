package com.mamalimomen.domains;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@SelectBeforeUpdate
@Table(name = "tbl_customer", catalog = "HW12_One", schema = "HW12_One")
@NamedQueries({
        @NamedQuery(
                name = "Customer.findAll",
                query = "SELECT c FROM Customer c"),
        @NamedQuery(
                name = "Customer.findOneByNationalCode",
                query = "SELECT c FROM Customer c WHERE c.nationalCode = ?1")
})
public class Customer extends User {

    @Transient
    private static final long serialVersionUID = 9220704339022596600L;

    @Transient
    private static long count = 1;

    @OneToMany(mappedBy = "ownerCustomer", orphanRemoval = true)
    @OrderBy(value = "balance DESC")
    private Set<Account> activeAccounts = new HashSet<>();

    public Customer() {
        this.setId(count);
        count++;
    }

    public Set<Account> getActiveAccounts() {
        return activeAccounts;
    }

    public void setActiveAccounts(Set<Account> activeAccounts) {
        this.activeAccounts = activeAccounts;
    }

    public void addAccount(Account account) {
        getActiveAccounts().add(account);
        account.setOwnerCustomer(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer c = (Customer) obj;
        return this.hashCode() == c.hashCode();
    }
}
