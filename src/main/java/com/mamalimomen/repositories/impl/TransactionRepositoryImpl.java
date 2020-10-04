package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.Transaction;
import com.mamalimomen.repositories.TransactionRepository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class TransactionRepositoryImpl extends BaseRepositoryImpl<Transaction, Long> implements TransactionRepository {

    public TransactionRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return findAllByNamedQuery("Transaction.findAll", Transaction.class);
    }

    @Override
    public List<Transaction> findAllSucceedTransactions() {
        return findAllByNamedQuery("Transaction.findAllSucceed", Transaction.class);
    }

    @Override
    public List<Transaction> findManyTransactionsByAccountNumberAndDate(String accountNumber, Date from, Date till) {
        return findManyByNamedQuery("Transaction.findManyByAccountNumberAndDate", accountNumber, from, till, Transaction.class);
    }

    @Override
    public List<Transaction> findManyTransactionsByAccountNumber(String accountNumber) {
        return findManyByNamedQuery("Transaction.findManyByAccountNumber", accountNumber, Transaction.class);
    }
}
