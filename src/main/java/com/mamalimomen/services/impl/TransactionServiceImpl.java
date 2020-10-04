package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Transaction;
import com.mamalimomen.repositories.TransactionRepository;
import com.mamalimomen.services.TransactionService;

import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

public class TransactionServiceImpl extends BaseServiceImpl<Transaction, Long, TransactionRepository> implements TransactionService {

    public TransactionServiceImpl(TransactionRepository serviceRepository) {
        super(serviceRepository);
    }

    private Predicate<Transaction> getFilterByDatePredicate(Date date) {
        Predicate<Transaction> filterByDate = transaction -> {
            if (transaction.getDate().compareTo(date) > 0)
                return true;
            else return false;
        };
        return filterByDate;
    }

    @Override
    public String createTransaction() {
        return null;
    }

    @Override
    public Optional<Transaction> retrieveTransaction() {
        return Optional.empty();
    }

    @Override
    public String updateTransaction() {
        return null;
    }

    @Override
    public String deleteTransaction() {
        return null;
    }

    @Override
    public void showTransactions() {

    }

    @Override
    public void showSucceedTransactions() {

    }

    @Override
    public void showTransactionsByAccountNumberAndDate() {

    }

    @Override
    public void showTransactionsByAccountNumberTillNow() {

    }

    @Override
    public void showTransactionsByAccountNumber() {

    }
}
