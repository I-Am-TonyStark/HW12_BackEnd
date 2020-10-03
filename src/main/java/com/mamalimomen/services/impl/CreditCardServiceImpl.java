package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.CreditCard;
import com.mamalimomen.repositories.CreditCardRepository;
import com.mamalimomen.repositories.impl.CreditCardRepositoryImpl;
import com.mamalimomen.services.CreditCardService;

import javax.persistence.EntityManager;

public class CreditCardServiceImpl extends BaseServiceImpl<CreditCard, Long, CreditCardRepository> implements CreditCardService {
    public CreditCardServiceImpl(EntityManager em) {
        super(new CreditCardRepositoryImpl(em));
    }
}
