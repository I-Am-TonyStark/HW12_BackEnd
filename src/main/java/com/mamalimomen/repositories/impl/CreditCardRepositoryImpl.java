package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.CreditCard;
import com.mamalimomen.repositories.CreditCardRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class CreditCardRepositoryImpl extends BaseRepositoryImpl<CreditCard, Long> implements CreditCardRepository {
    public CreditCardRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<CreditCard> findAllCreditCards() {
        return findAllByNamedQuery("CreditCard.findAll", CreditCard.class);
    }

    @Override
    public Optional<CreditCard> findOneCreditCardByNumber(String cardNumber) {
        return findOneByNamedQuery("CreditCard.findOneByNumber", cardNumber, CreditCard.class);
    }
}
