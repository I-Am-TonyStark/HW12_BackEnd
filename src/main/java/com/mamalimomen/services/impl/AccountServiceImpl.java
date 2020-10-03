package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Account;
import com.mamalimomen.repositories.AccountRepository;
import com.mamalimomen.repositories.impl.AccountRepositoryImpl;
import com.mamalimomen.services.AccountService;

import javax.persistence.EntityManager;
import java.util.function.Predicate;

public class AccountServiceImpl extends BaseServiceImpl<Account, Long, AccountRepository> implements AccountService {
    public static final Predicate<Account> isActive = account
            -> account.isActive();

    public AccountServiceImpl(EntityManager em) {
        super(new AccountRepositoryImpl(em));
    }
}
