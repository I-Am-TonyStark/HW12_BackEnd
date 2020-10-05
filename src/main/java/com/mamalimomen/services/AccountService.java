package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Account;
import com.mamalimomen.domains.Customer;
import com.mamalimomen.domains.Transaction;

import java.util.List;
import java.util.Optional;

public interface AccountService extends BaseService<Account, Long> {

    Optional<Account> createAccount();

    Optional<Account> retrieveAccount();

    List<Account> retrieveManyActiveAccountsByCustomerNationalCode(String customerNationalCode);

    boolean updateAccountBalanceAuto(Transaction transaction);

    String updateAccountBalanceManually();

    String updateAccountActiveState();

    String deleteAccount();

    void showAccounts();

    void showActiveAccounts();

    void showAccountByAccountNumber();

    void showActiveAccountsByCustomerNationalNumber(Customer customer);
}
