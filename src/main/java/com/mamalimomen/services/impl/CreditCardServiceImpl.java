package com.mamalimomen.services.impl;

import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.CreditCard;
import com.mamalimomen.repositories.CreditCardRepository;
import com.mamalimomen.repositories.impl.CreditCardRepositoryImpl;
import com.mamalimomen.services.CreditCardService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

//FIXME
public class CreditCardServiceImpl extends BaseServiceImpl<CreditCard, Long, CreditCardRepository> implements CreditCardService {

    private static final BigDecimal cardToCardTransactionCost = BigDecimal.valueOf(5_000.00);
    private static final BigDecimal checkAccountBalanceTransactionCost = BigDecimal.valueOf(1_000.00);

    public CreditCardServiceImpl(EntityManager em) {
        super(new CreditCardRepositoryImpl(em));
    }

    @Override
    public String createCreditCard() {
        return null;
    }

    @Override
    public Optional<CreditCard> retrieveCreditCard() {
        return Optional.empty();
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
    public String changeFirstPassword() {
        return null;
    }

    @Override
    public String setSecondPassword() {
        return null;
    }

    @Override
    public String changeSecondPassword() {
        return null;
    }

    @Override
    public String cardToCardTransaction() {
        while (true) {

            if (transactionPermission()) {

                System.out.printf("Enter origin card number: ");
                String originCardNumber = SingletonScanner.readLine();

                System.out.printf("Enter destination card number: ");
                String destinationCardNumber = SingletonScanner.readLine();

                System.out.printf("Enter transition money amount: ");
                String transitionMoneyAmount = SingletonScanner.readLine();

                //TODO validations 16 digit and exist

                while (true) {
                    System.out.printf("Enter origin card second password: "); //after three wrong -> account blocking -> employee must unBlocking it
                    String originCardSecondPassword = SingletonScanner.readLine();

                    System.out.printf("Enter origin card cvv2: ");
                    String originCardCvv2 = SingletonScanner.readLine();

                    System.out.printf("Enter origin card expire date: ");
                    String originExpireDate = SingletonScanner.readLine();

                    //TODO validation and enough balance
                    //TODO do transaction with mines cost 500.00 tomans

                }
            }
        }
    }

    @Override
    public String checkAccountBalanceTransaction() {
        return null;
    }

    @Override
    public boolean transactionPermission() { //check at first every transaction
        //TODO validation account activity (not blocking)
        return false;
    }
}
