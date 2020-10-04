package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends BaseRepository<Transaction, Long> {

    List<Transaction> findAllTransactions();

    List<Transaction> findAllSucceedTransactions();

    List<Transaction> findManyTransactionsByAccountNumberAndDate(String accountNumber, Date from, Date till);

    List<Transaction> findManyTransactionsByAccountNumber(String accountNumber);
}
