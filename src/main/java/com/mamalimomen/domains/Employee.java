package com.mamalimomen.domains;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Employee extends User{

    @ManyToOne
    private BankBranch workOffice;

    @ManyToOne
    private Employee boss;
}
