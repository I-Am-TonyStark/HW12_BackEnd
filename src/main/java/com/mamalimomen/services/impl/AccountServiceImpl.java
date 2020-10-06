package com.mamalimomen.services.impl;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.controllers.utilities.AppManager;
import com.mamalimomen.controllers.utilities.Services;
import com.mamalimomen.domains.*;
import com.mamalimomen.repositories.AccountRepository;
import com.mamalimomen.repositories.impl.AccountRepositoryImpl;
import com.mamalimomen.services.AccountService;
import com.mamalimomen.services.CreditCardService;
import com.mamalimomen.services.CustomerService;
import com.mamalimomen.services.TransactionService;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl extends BaseServiceImpl<Account, Long, AccountRepository> implements AccountService {

    public AccountServiceImpl(EntityManager em) {
        super(new AccountRepositoryImpl(em));
    }

    @Override
    public Optional<Account> createAccount() {
        Account account = new Account();
        while (true) {
            try {
                System.out.print("Account Number (13 Digit): ");
                String accountNumber = SingletonScanner.readLine();
                if (accountNumber.equalsIgnoreCase("esc")) {
                    break;
                }
                account.setAccountNumber(accountNumber);
                if (baseRepository.findOneAccountByAccountNumber(accountNumber).isPresent()) {
                    System.out.println("This Account Number has taken already!\n");
                    continue;
                }

                account.setActive(true);

                System.out.print("Balance amount: ");
                account.setBalanceString(SingletonScanner.readLine());

                CustomerService customerService = (CustomerService) AppManager.getService(Services.CUSTOMER_SERVICE);
                Optional<Customer> oCustomer = customerService.retrieveCustomer();
                if (oCustomer.isEmpty()) {
                    oCustomer = customerService.createCustomer();
                    if (oCustomer.isEmpty()) {
                        break;
                    }
                }
                account.setOwnerCustomer(oCustomer.get());

                account.setOpenDate(new Date(System.currentTimeMillis()));

                CreditCardService creditCardService = (CreditCardService) AppManager.getService(Services.CREDIT_CARD_SERVICE);
                Optional<CreditCard> oCreditCard = creditCardService.createCreditCard();
                if (oCreditCard.isEmpty()) {
                    break;
                }
                account.setActiveCard(oCreditCard.get());

                if (baseRepository.saveOne(account)) {
                    return Optional.of(account);
                } else {
                    System.out.println("There is a problem when saving This new Account in database!");
                    break;
                }
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> retrieveAccount() {
        Account account = new Account();
        while (true) {
            try {
                System.out.print("Account Number: ");
                String accountNumber = SingletonScanner.readLine();
                account.setAccountNumber(accountNumber);
                return baseRepository.findOneAccountByAccountNumber(accountNumber);
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!");
            }
        }
    }

    @Override
    public List<Account> retrieveManyActiveAccountsByCustomerNationalCode(String customerNationalCode) {
        return baseRepository.findManyActiveAccountsByCustomerNationalCode(customerNationalCode);
    }

    @Override
    public boolean updateAccountBalanceAuto(Transaction transaction) {
        TransactionService transactionService = (TransactionService) AppManager.getService(Services.TRANSACTION_SERVICE);
        Transaction inverseTransaction = new Transaction();
        Optional<Account> oOriginAccount = baseRepository.findOneAccountByCreditCardNumber(transaction.getOriginCardNumber());
        Optional<Account> oDestinationAccount = baseRepository.findOneAccountByCreditCardNumber(transaction.getDestinationCardNumber());
        Account originAccount = oOriginAccount.get();
        Account destinationAccount = oDestinationAccount.get();
        try {
            originAccount.setBalance(originAccount.getBalance().subtract(transaction.getMoneyAmount().add(transaction.getTCost())));
            destinationAccount.setBalance(destinationAccount.getBalance().add(transaction.getMoneyAmount()));
            if (baseRepository.updateOne(originAccount) &&
                    baseRepository.updateOne(destinationAccount)) {
                transaction.setDate(new Date(System.currentTimeMillis()));
                transaction.cloneMeTo(inverseTransaction, destinationAccount);
                transactionService.saveOne(transaction);
                transactionService.saveOne(inverseTransaction);
                return true;
            }
        } catch (InValidDataException ignored) {}
        transaction.setSucceed(false);
        transaction.setDate(new Date(System.currentTimeMillis()));
        transaction.cloneMeTo(inverseTransaction, destinationAccount);
        transactionService.saveOne(transaction);
        transactionService.saveOne(inverseTransaction);
        return false;
    }

    @Override
    public String updateAccountBalanceManually() {
        return null;
    }

    @Override
    public String updateAccountActiveState() {
        User user = new User();
        while (true) {
            try {
                System.out.print("Customer NationalCode: ");
                String customerNationalCode = SingletonScanner.readLine();
                if (customerNationalCode.equalsIgnoreCase("esc")) {
                    break;
                }
                user.setNationalCode(customerNationalCode);
                List<Account> accounts = baseRepository.findManyAccountsByCustomerNationalCode(customerNationalCode);
                if (accounts.isEmpty()) {
                    return "There is not any Customer with this National Code!";
                }

                for (int i = 1; i <= accounts.size(); i++) {
                    System.out.printf("%d. %s%n", i, accounts.get(i - 1));
                }
                System.out.print("Which account? ");
                Account account = accounts.get(SingletonScanner.readInteger() - 1);

                System.out.print("Toggle activate state (current state: " + account.getActive() + ")? ");
                String answer = SingletonScanner.readLine();
                if (!answer.equalsIgnoreCase("y")) {
                    break;
                }
                account.setActive(!account.getActive());
                if (baseRepository.updateOne(account)) {
                    return "The Account activate state was update successfully!";
                } else {
                    return "There is a problem, We can not update The Account activate state!";
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, Please enter an integer number!");
                SingletonScanner.clearBuffer();
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            }
        }
        return "You Cancelled this operation!";
    }

    @Override
    public String deleteAccount() {
        return null;
    }

    @Override
    public void showAccounts() {

    }

    @Override
    public void showActiveAccounts() {

    }

    @Override
    public void showAccountByAccountNumber() {

    }

    @Override
    public void showActiveAccountsByCustomerNationalNumber(Customer customer) {
        List<Account> accounts = retrieveManyActiveAccountsByCustomerNationalCode(customer.getNationalCode());
        if (accounts.isEmpty()) {
            System.out.println("You have not any Active Account now!");
        }
        for (int i = 1; i <= accounts.size(); i++, System.out.println()) {
            System.out.printf("%d. ", i);
            accounts.get(i - 1).printCompleteInformation();
        }
    }
}