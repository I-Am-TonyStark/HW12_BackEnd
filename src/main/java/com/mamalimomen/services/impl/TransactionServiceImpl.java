package com.mamalimomen.services.impl;

import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.controllers.utilities.AppManager;
import com.mamalimomen.controllers.utilities.Services;
import com.mamalimomen.domains.Account;
import com.mamalimomen.domains.Customer;
import com.mamalimomen.domains.Transaction;
import com.mamalimomen.repositories.TransactionRepository;
import com.mamalimomen.repositories.impl.TransactionRepositoryImpl;
import com.mamalimomen.services.AccountService;
import com.mamalimomen.services.TransactionService;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TransactionServiceImpl extends BaseServiceImpl<Transaction, Long, TransactionRepository> implements TransactionService {

    public TransactionServiceImpl(EntityManager em) {
        super(new TransactionRepositoryImpl(em));
    }

    private Predicate<Transaction> getFilterByDatePredicate(Date date) {
        return transaction -> transaction.getDate().compareTo(date) > 0;
    }

    @Override
    public Optional<Transaction> createTransaction() {
        return Optional.empty();
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
    public void showTransactionsByAccountNumberAndDate(Customer customer) {
        while (true) {
            AccountService accountService = (AccountService) AppManager.getService(Services.ACCOUNT_SERVICE);
            List<Account> accounts = accountService.retrieveManyActiveAccountsByCustomerNationalCode(customer.getNationalCode());
            if (accounts.isEmpty()) {
                System.out.println("You have not any Active Account now!");
            }
            try {
                for (int i = 1; i <= accounts.size(); i++) {
                    System.out.println(accounts.get(i - 1));
                }
                System.out.print("Which account? ");
                Account account = accounts.get(SingletonScanner.readInteger() - 1);

                System.out.println("Enter from date: ");
                String fromDate = SingletonScanner.readLine();
                String[] tempArray = fromDate.split("/");
                Date date = new Date(Integer.parseInt(tempArray[0]) - 1900, Integer.parseInt(tempArray[1]), 0);

                List<Transaction> transactions = baseRepository
                        .findManyTransactionsByAccountNumberAndDate(account
                                .getAccountNumber(), date, new Date(System
                                .currentTimeMillis()));

                if (transactions.isEmpty()) {
                    System.out.println("There is not any Transaction with this information");
                    return;
                }

                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
                break;
            } catch (IndexOutOfBoundsException e) {
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, Please enter an integer number!");
                SingletonScanner.clearBuffer();
            }
        }
    }

    @Override
    public void showTransactionsByAccountNumberTillNow() {

    }

    @Override
    public void showTransactionsByAccountNumber() {

    }
}
