package com.mamalimomen.domains;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@SelectBeforeUpdate
@Table(name = "tbl_employee", catalog = "HW12_One", schema = "HW12_One")
@NamedQueries({
        @NamedQuery(
                name = "Employee.findAll",
                query = "SELECT e FROM Employee e"),
        @NamedQuery(
                name = "Employee.findManyByPostTitle",
                query = "SELECT e FROM Employee e JOIN e.post p where p.title = ?1"),
        @NamedQuery(
                name = "Employee.findManyByBossNationalCode",
                query = "SELECT e FROM Employee e JOIN e.boss b where b.nationalCode = ?1"),
        @NamedQuery(
                name = "Employee.findManyByBranchName",
                query = "SELECT e FROM Employee e JOIN e.workOffice o where o.branchName = ?1"),
        @NamedQuery(
                name = "Employee.findOneByNationalCode",
                query = "SELECT e FROM Employee e WHERE e.nationalCode = ?1")
})
public class Employee extends User {

    @Transient
    private static final long serialVersionUID = -7597451830919628484L;

    @Transient
    private static long count = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_branch", nullable = false)
    private BankBranch workOffice;

    @ManyToOne
    @JoinColumn(name = "fk_boss")
    private Employee boss;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_post", nullable = false)
    private Post post;

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
}
