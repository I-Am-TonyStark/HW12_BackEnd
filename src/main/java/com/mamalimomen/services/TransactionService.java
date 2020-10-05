package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Customer;
import com.mamalimomen.domains.Transaction;

import java.util.Optional;

public interface TransactionService extends BaseService<Transaction, Long> {

    Optional<Transaction> createTransaction();

    Optional<Transaction> retrieveTransaction();

    String updateTransaction();

    String deleteTransaction();

    void showTransactions();

    void showSucceedTransactions();

    void showTransactionsByAccountNumberAndDate(Customer customer);

    void showTransactionsByAccountNumberTillNow();

    void showTransactionsByAccountNumber();
}
