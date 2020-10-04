package com.mamalimomen.repositories.impl;

import com.mamalimomen.domains.Transaction;

import java.util.Date;
import java.util.function.Predicate;

public class TransactionRepositoryImpl {

    private Predicate<Transaction> getFilterByDatePredicate(Date date) {
        Predicate<Transaction> filterByDate = transaction -> {
            if (transaction.getDate().compareTo(date) > 0)
                return true;
            else return false;
        };
        return filterByDate;
    }
}
