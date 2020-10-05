package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Account;

import java.util.Optional;

public interface AccountService extends BaseService<Account, Long> {

    Optional<Account> createAccount();

    Optional<Account> retrieveAccount();

    String updateAccount();

    String updateAccountActiveState();

    String deleteAccount();

    void showAccounts();

    void showActiveAccounts();

    void showAccountByAccountNumber();

    void showAccountsByCustomerNationalNumber();
}
