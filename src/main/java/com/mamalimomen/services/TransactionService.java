package com.mamalimomen.services;

import java.util.Date;

public interface TransactionService {

    void seeAllAccountTransactions(Date from,Long accountID);
}
