package com.mamalimomen.domains;

import com.mamalimomen.base.domains.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Account extends BaseEntity<Long> {

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Customer ownerCustomer;

    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private CreditCard activeCard;
}
