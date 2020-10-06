package com.mamalimomen.services.impl;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.controllers.utilities.SecurityManager;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.controllers.utilities.AppManager;
import com.mamalimomen.controllers.utilities.Services;
import com.mamalimomen.domains.Account;
import com.mamalimomen.domains.CreditCard;
import com.mamalimomen.domains.Customer;
import com.mamalimomen.domains.Transaction;
import com.mamalimomen.repositories.CreditCardRepository;
import com.mamalimomen.repositories.impl.CreditCardRepositoryImpl;
import com.mamalimomen.services.AccountService;
import com.mamalimomen.services.CreditCardService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

public class CreditCardServiceImpl extends BaseServiceImpl<CreditCard, Long, CreditCardRepository> implements CreditCardService {

    private static final BigDecimal cardToCardTransactionCost = BigDecimal.valueOf(5_000.00);
    private static final BigDecimal checkAccountBalanceTransactionCost = BigDecimal.valueOf(1_000.00);
    private short tries = 0;

    public CreditCardServiceImpl(EntityManager em) {
        super(new CreditCardRepositoryImpl(em));
    }

    @Override
    public Optional<CreditCard> createCreditCard() {
        CreditCard creditCard = new CreditCard();
        while (true) {
            try {
                System.out.print("Card Number (16 Digit): ");
                String cardNumber = SingletonScanner.readLine();
                if (cardNumber.equalsIgnoreCase("esc")) {
                    break;
                }
                creditCard.setCardNumber(cardNumber);
                if (baseRepository.findOneCreditCardByNumber(cardNumber).isPresent()) {
                    System.out.println("This Card Number has taken already!\n");
                    continue;
                }

                System.out.print("First Password (4 Digit): ");
                creditCard.setFirstPassword(SingletonScanner.readLine());

                System.out.print("CVV2 (4 Digit): ");
                creditCard.setCvv2(SingletonScanner.readLine());

                System.out.print("Expire date (Year/Month): ");
                creditCard.setExpireDateString(SingletonScanner.readLine());

                return Optional.of(creditCard);
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<CreditCard> retrieveCreditCard() {
        CreditCard creditCard = new CreditCard();
        while (true) {
            try {
                System.out.print("Credit Card Number: ");
                String cardNumber = SingletonScanner.readLine();
                creditCard.setCardNumber(cardNumber);
                return baseRepository.findOneCreditCardByNumber(cardNumber);
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            }
        }
    }

    @Override
    public String updateCreditCard() {
        return null;
    }

    @Override
    public String deleteCreditCard() {
        return null;
    }

    @Override
    public List<CreditCard> findCreditCards() {
        return null;
    }

    @Override
    public Optional<CreditCard> findCreditCardByNumber() {
        return Optional.empty();
    }

    @Override
    public String setFirstPassword() {
        return null;
    }

    @Override
    public String changeFirstPassword(Customer customer) {
        while (true) {
            AccountService accountService = (AccountService) AppManager.getService(Services.ACCOUNT_SERVICE);
            List<Account> accounts = accountService.retrieveManyActiveAccountsByCustomerNationalCode(customer.getNationalCode());
            if (accounts.isEmpty()) {
                return "You have not any Active Account now!";
            }
            try {
                for (int i = 1; i <= accounts.size(); i++) {
                    System.out.printf("%d. %s%n", i, accounts.get(i - 1));
                }
                System.out.print("Which account? ");
                Account account = accounts.get(SingletonScanner.readInteger() - 1);
                CreditCard creditCard = account.getActiveCard();

                System.out.print("Old Password: ");
                String oldPassword = SingletonScanner.readLine();
                if (oldPassword.equalsIgnoreCase("esc")) {
                    break;
                } else if (!SecurityManager.checkPasswordHash(oldPassword,creditCard.getFirstPassword())) {
                    System.out.println("Wrong Password!\n");
                    continue;
                }
                System.out.print("New Password: ");
                String newPassword = SingletonScanner.readLine();
                if (newPassword.equalsIgnoreCase("esc")) {
                    break;
                }
                creditCard.setFirstPassword(newPassword);

                if (baseRepository.updateOne(creditCard)) {
                    return "Your first password was update successfully!";
                } else {
                    return "There is a problem, We can not update your first password!";
                }
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            } catch (IndexOutOfBoundsException e) {
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, Please enter an integer number!\n");
                SingletonScanner.clearBuffer();
            }
        }
        return "You Cancelled this operation!";
    }

    @Override
    public String setSecondPassword(CreditCard creditCard) {
        while (true) {
            try {
                System.out.print("New Password: ");
                String newPassword = SingletonScanner.readLine();
                if (newPassword.equalsIgnoreCase("esc")) {
                    break;
                }
                creditCard.setSecondPassword(newPassword);

                if (baseRepository.updateOne(creditCard)) {
                    return "Your second password was updated successfully!";
                } else {
                    return "There is a problem, We can not update your second password!";
                }
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!");
            }
        }
        return "You Cancelled this operation!";
    }

    @Override
    public String changeSecondPassword(Customer customer) {
        while (true) {
            AccountService accountService = (AccountService) AppManager.getService(Services.ACCOUNT_SERVICE);
            List<Account> accounts = accountService.retrieveManyActiveAccountsByCustomerNationalCode(customer.getNationalCode());
            if (accounts.isEmpty()) {
                return "You have not any Active Account now!";
            }
            try {
                for (int i = 1; i <= accounts.size(); i++) {
                    System.out.printf("%d. %s%n", i, accounts.get(i - 1));
                }
                System.out.print("Which account? ");
                Account account = accounts.get(SingletonScanner.readInteger() - 1);
                CreditCard creditCard = account.getActiveCard();

                if (creditCard.getSecondPassword() == null) {
                    return setSecondPassword(creditCard);
                }

                System.out.print("Old Password: ");
                String oldPassword = SingletonScanner.readLine();
                if (oldPassword.equalsIgnoreCase("esc")) {
                    break;
                } else if (!SecurityManager.checkPasswordHash(oldPassword,creditCard.getSecondPassword())) {
                    System.out.println("Wrong Password!\n");
                    continue;
                }
                System.out.print("New Password: ");
                String newPassword = SingletonScanner.readLine();
                if (newPassword.equalsIgnoreCase("esc")) {
                    break;
                }
                creditCard.setSecondPassword(newPassword);

                if (baseRepository.updateOne(creditCard)) {
                    return "Your second password was update successfully!";
                } else {
                    return "There is a problem, We can not update your second password!";
                }
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            } catch (IndexOutOfBoundsException e) {
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, Please enter an integer number!\n");
                SingletonScanner.clearBuffer();
            }
        }
        return "You Cancelled this operation!";
    }

    @Override
    public String cardToCardTransaction(Customer customer) {
        while (true) {
            AccountService accountService = (AccountService) AppManager.getService(Services.ACCOUNT_SERVICE);
            List<Account> accounts = accountService.retrieveManyActiveAccountsByCustomerNationalCode(customer.getNationalCode());
            if (accounts.isEmpty()) {
                return "You have not any Active Account now!";
            }
            try {
                for (int i = 1; i <= accounts.size(); i++) {
                    System.out.printf("%d. %s%n", i, accounts.get(i - 1));
                }
                System.out.print("Which account? ");
                Account account = accounts.get(SingletonScanner.readInteger() - 1);

                CreditCard originCreditCard = account.getActiveCard();
                if (originCreditCard.getSecondPassword() == null) {
                    System.out.println("This CreditCard does not have second password!\n");
                    continue;
                }
                System.out.printf("%s %s%n", "Your origin card number is", originCreditCard.getCardNumber());

                System.out.print("Enter Destination ");
                Optional<CreditCard> oCreditCard = retrieveCreditCard();
                if (oCreditCard.isEmpty()) {
                    System.out.println("There is not any Credit Card with this number\n");
                    continue;
                }
                CreditCard destinationCreditCard = oCreditCard.get();

                if (destinationCreditCard.getCardNumber().equals(originCreditCard.getCardNumber())) {
                    System.out.println("You can not do money transition to same card!\n");
                    continue;
                }

                Transaction transaction = new Transaction();
                System.out.print("Enter transition money amount: ");
                transaction.setMoneyAmountString(SingletonScanner.readLine());
                transaction.setOriginCardNumber(originCreditCard.getCardNumber());
                transaction.setDestinationCardNumber(destinationCreditCard.getCardNumber());
                transaction.settCost(cardToCardTransactionCost);
                transaction.setAccount(account);
                transaction.setSucceed(true);

                while (tries < 3) {
                    try {
                        System.out.print("Enter origin card second password: ");
                        String originCardSecondPassword = SingletonScanner.readLine();

                        System.out.print("Enter origin card cvv2: ");
                        String originCardCvv2 = SingletonScanner.readLine();

                        System.out.print("Enter origin card expire date (Year/Month): ");
                        String originExpireDate = SingletonScanner.readLine();
                        String[] tempArray = originExpireDate.split("/");
                        Date date = new Date(Integer.parseInt(tempArray[0]) - 1900, Integer.parseInt(tempArray[1]), 0);

                        if (SecurityManager.checkPasswordHash(originCardSecondPassword,originCreditCard.getSecondPassword())
                                && originCardCvv2.equals(originCreditCard.getCvv2())
                                && date.equals(originCreditCard.getExpireDate())) {

                            if (accountService.updateAccountBalanceAuto(transaction)) {
                                return "Your transaction done successfully!\nTransaction ID: " + transaction.getId();
                            } else if (!transaction.getSucceed()) {
                                return "You have not enough money for this transaction";
                            }
                            return "There is a database Error for this transaction";
                        }
                        tries++;
                        System.out.println("Wrong origin card data!\n\nAllowed tries: " + (3 - tries));
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        System.out.println("Wrong Date format, Please enter true Date format!\n\nAllowed tries: " + (3 - tries));
                    }
                }
                account.setActive(false);
                accountService.updateOne(account);
                return "Your allowed tries was ended!\nWe have to block your account for security reasons.";
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            } catch (IndexOutOfBoundsException e) {
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, Please enter an integer number!\n");
                SingletonScanner.clearBuffer();
            }
        }
        return "You Cancelled this operation!";
    }

    @Override
    public String checkAccountBalanceTransaction() {
        return null;
    }
}
