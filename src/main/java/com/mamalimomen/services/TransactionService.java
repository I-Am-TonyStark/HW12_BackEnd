package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Transaction;

import java.util.Optional;

public interface TransactionService extends BaseService<Transaction, Long> {

    String createTransaction();

    Optional<Transaction> retrieveTransaction();

    String updateTransaction();

    String deleteTransaction();

    void showTransactions();

    void showSucceedTransactions();

    void showTransactionsByAccountNumberAndDate();

    void showTransactionsByAccountNumberTillNow();

    void showTransactionsByAccountNumber();
}
