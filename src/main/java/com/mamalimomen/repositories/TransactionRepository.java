package com.mamalimomen.repositories;

import com.mamalimomen.domains.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository {

    List<Transaction> seeAllAccountTransactions(Date from, Long accountID);
}
