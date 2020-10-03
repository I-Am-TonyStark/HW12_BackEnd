package com.mamalimomen.domains;

import com.mamalimomen.base.domains.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BankBranch extends BaseEntity<Long> {

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "fk_bank_branch")
    private Set<Account> accounts = new HashSet<>();

    @OneToOne
    private Employee manager;
}
