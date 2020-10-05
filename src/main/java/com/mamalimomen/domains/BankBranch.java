package com.mamalimomen.domains;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.domains.BaseEntity;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@SelectBeforeUpdate
@Table(name = "tbl_branch", catalog = "HW12_One", schema = "HW12_One",
        uniqueConstraints = {@UniqueConstraint(name = "unique_branch_manager", columnNames = {"branch_name", "fk_manager"})})
@NamedQueries({
        @NamedQuery(
                name = "BankBranch.findAll",
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
    @Column(name = "create_date", updatable = false, nullable = false)
    private Date createDate;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "fk_branch", foreignKey = @ForeignKey(name = "FK_BANK_BRANCH"))
    private Set<Account> accounts = new HashSet<>();

    @OneToOne(optional = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_manager", nullable = true, unique = true)
    private Employee manager;

    public BankBranch() {
        this.setId(count);
        count++;
    }

    public String getAccountsString() {
        return getAccounts().stream().map(Account::toString).collect(Collectors.joining("\n"));
    }

    public void printCompleteInformation() {
        System.out.printf("Branch name: %s%nManager fullName: %s%nFoundation date: %s%nAccounts number: %,d%nSum of balance: %,+014.2f Rials%n",
                getBranchName(), getManager().getFullName(), getCreateDate(), getAccounts().size(), getSumBalance());
    }

    public Double getSumBalance() {
        return getAccounts().stream().map(Account::getBalance).mapToDouble(BigDecimal::doubleValue).sum();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) throws InValidDataException {
        if (!branchName.matches("(\\w\\d*)+\\'?(\\w\\s*){2,}")) {
            throw new InValidDataException("Bank Branch Name");
        }
        this.branchName = branchName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date crateDate) {
        this.createDate = crateDate;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        getAccounts().add(account);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BankBranch bb = (BankBranch) obj;
        return this.hashCode() == bb.hashCode();
    }

    @Override
    public int compareTo(BankBranch bb) {
        return this.getSumBalance().compareTo(bb.getSumBalance());
    }

    @Override
    public String toString() {
        return String.format("Branch name: %s%nManager fullName: %s%n", getBranchName(), getManager().getFullName());
    }
}
