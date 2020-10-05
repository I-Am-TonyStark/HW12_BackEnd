package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.Account;
import com.mamalimomen.repositories.AccountRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, Long> implements AccountRepository {
    public AccountRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Account> findAllAccounts() {
        return findAllByNamedQuery("Account.findAll", Account.class);
    }

    @Override
    public List<Account> findAllActiveAccounts() {
        return findAllByNamedQuery("Account.findAllActive", Account.class);
    }

    @Override
    public Optional<Account> findOneAccountByAccountNumber(String accountNumber) {
        return findOneByNamedQuery("Account.findOneByAccountNumber", accountNumber, Account.class);
    }

    @Override
    public Optional<Account> findOneAccountByCreditCardNumber(String cardNumber) {
        return findOneByNamedQuery("Account.findOneByCreditCardNumber", cardNumber, Account.class);
    }

    @Override
    public List<Account> findManyActiveAccountsByCustomerNationalCode(String customerNationalCode) {
        return findManyByNamedQuery("Account.findManyActiveByCustomerNationalCode", customerNationalCode, Account.class);
    }

    @Override
    public List<Account> findManyAccountsByCustomerNationalCode(String customerNationalCode) {
        return findManyByNamedQuery("Account.findManyByCustomerNationalCode", customerNationalCode, Account.class);
    }
}
