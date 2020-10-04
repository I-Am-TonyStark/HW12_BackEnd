package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Account;
import com.mamalimomen.repositories.AccountRepository;
import com.mamalimomen.repositories.impl.AccountRepositoryImpl;
import com.mamalimomen.services.AccountService;

import javax.persistence.EntityManager;
import java.util.Optional;

//FIXME
public class AccountServiceImpl extends BaseServiceImpl<Account, Long, AccountRepository> implements AccountService {

    public AccountServiceImpl(EntityManager em) {
        super(new AccountRepositoryImpl(em));
    }

    @Override
    public String createAccount() {

        return null;
    }

    @Override
    public Optional<Account> retrieveAccount() {
        return Optional.empty();
    }

    @Override
    public String updateAccount() {
        return null;
    }

    @Override
    public String deleteAccount() {
        return null;
    }

    @Override
    public void showAccounts() {

    }

    @Override
    public void showActiveAccounts() {

    }

    @Override
    public void showAccountsByCustomerNationalNumber() {

    }
}
