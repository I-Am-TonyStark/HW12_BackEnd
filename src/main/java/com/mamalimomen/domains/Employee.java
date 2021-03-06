package com.mamalimomen.domains;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@SelectBeforeUpdate
@Table(name = "tbl_employee", catalog = "HW12_One", schema = "HW12_One")
@NamedQueries({
        @NamedQuery(
                name = "Employee.findAll",
                query = "SELECT e FROM Employee e"),
        @NamedQuery(
                name = "Employee.findManyByPostTitle",
                query = "SELECT e FROM Employee e JOIN e.post p WHERE p.title = ?1"),
        @NamedQuery(
                name = "Employee.findManyByBossNationalCode",
                query = "SELECT e FROM Employee e JOIN e.boss b WHERE b.nationalCode = ?1 AND e.nationalCode <> b.nationalCode"),
        @NamedQuery(
                name = "Employee.findManyByBranchName",
                query = "SELECT e FROM Employee e JOIN e.workOffice o WHERE o.branchName = ?1"),
        @NamedQuery(
                name = "Employee.findOneByNationalCode",
                query = "SELECT e FROM Employee e WHERE e.nationalCode = ?1")
})
public class Employee extends User {

    @Transient
    private static final long serialVersionUID = -7597451830919628484L;

    @Transient
    private static long count = 1;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_branch", nullable = false)
    private BankBranch workOffice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_boss", nullable = false)
    private Employee boss;

    @OneToMany
    @JoinColumn(name = "fk_boss")
    private Set<Employee> childrenEmployee = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_post", nullable = false)
    private Post post;

    public Employee() {
        this.setId(count);
        count++;
    }

    public BankBranch getWorkOffice() {
        return workOffice;
    }

    public void setWorkOffice(BankBranch workOffice) {
        this.workOffice = workOffice;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee e = (Employee) obj;
        return this.hashCode() == e.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%sPost: %s%nBoss: %s%nOffice: %s%n", super.toString(), getPost().getTitle(), getBoss().getFullName(), getWorkOffice().getBranchName());
    }
}
