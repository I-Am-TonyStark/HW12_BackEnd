package com.mamalimomen.domains;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.domains.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbl_bank_branch", catalog = "HW12_One", schema = "HW12_One",
        uniqueConstraints = {@UniqueConstraint(name = "unique_branch_manager", columnNames = {"branchName", "manager"})})
@NamedQueries({
        @NamedQuery(
                name = "BankBranch.finaAll",
                query = "SELECT bb FROM BankBranch bb"),
        @NamedQuery(
                name = "BankBranch.findOneByName",
                query = "SELECT bb FROM BankBranch bb WHERE bb.branchName = ?1")
})
public class BankBranch extends BaseEntity<Long> implements Comparable<BankBranch> {

    @Transient
    private static final long serialVersionUID = 2625817444781673072L;

    @Transient
    private static long count = 0;

    @Column(name = "branch_name", nullable = false, updatable = false, unique = true)
    private String branchName;

    @Temporal(TemporalType.DATE)
    @Column(updatable = false, nullable = false)
    private Date crateDate;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "fk_bank_branch", foreignKey = @ForeignKey(name = "FK_BOOK_REVIEW"))
    private Set<Account> accounts = new HashSet<>();

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_manager_id",nullable = false,unique = true)
    private Employee manager;

    public BankBranch() {
        this.setId(count);
        count++;
    }

    public String getAccounts() {
        return accounts.stream().map().collect(Collectors.joining(" & "));
    }

    @Override
    public int compareTo(BankBranch bb) {
        return this.getCrateDate().compareTo(bb.getCrateDate());
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) throws InValidDataException {
        if (!branchName.matches("[a-zA-Z\\s.,&\\d\\(\\)]{5,}")) {
            throw new InValidDataException("Bank Branch Name");
        }
        this.branchName = branchName;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
