package com.mamalimomen.domains;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee extends User{

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_office",nullable = false)
    private BankBranch workOffice;

    @ManyToOne
    @JoinColumn(name = "fk_boss")
    private Employee boss;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_post",nullable = false)
    private Post post; //employee, boss, manager

    /*@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 31;
    }*/
}
