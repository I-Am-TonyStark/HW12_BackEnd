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

public class CreditCardServiceImpl extends BaseServiceImpl<CreditCard, Long, CreditCardRepository> implements CreditCardService {

    private static final BigDecimal cardToCardTransactionCost = BigDecimal.valueOf(5_000.00);
    private static final BigDecimal seeBalanceTransactionCost = BigDecimal.valueOf(1_000.00);

    public CreditCardServiceImpl(EntityManager em) {
        super(new CreditCardRepositoryImpl(em));
    }


    @Override
    public Optional<CreditCard> findOneCreditCard(Long ownerID) {
        return Optional.empty();
    }

    @Override
    public boolean setFirstPassword(Long cardID) {
        return false;
    }

    @Override
    public boolean changeFirstPassword(Long cardID) {
        return false;
    }

    @Override
    public boolean changeSecondPassword(Long cardID) {
        return false;
    }

    @Override
    public boolean setSecondPassword(Long cardID) {
        return false;
    }

    @Override
    public List<CreditCard> findAllCreditCards() {
        return null;
    }

    @Override
    public boolean transactionPermission() { //check at first every transaction
        //TODO validation account activity
    }

    @Override
    public boolean cardToCard() {
        while (true) {

            if (transactionPermission()) {

                System.out.printf("Enter origin card number: ");
                String originCardNumber = SingletonScanner.readLine();

                System.out.printf("Enter destination card number: ");
                String destinationCardNumber = SingletonScanner.readLine();

                System.out.printf("Enter transition money amount: ");
                String transitionMoneyAmount = SingletonScanner.readLine();

                //TODO validations

                while (true) {
                    System.out.printf("Enter origin card second password: "); //after three wrong -> account blocking -> employee must unBlocking it
                    String originCardSecondPassword = SingletonScanner.readLine();

                    System.out.printf("Enter origin card cvv2: ");
                    String originCardCvv2 = SingletonScanner.readLine();

                    System.out.printf("Enter origin card expire date: ");
                    String originExpireDate = SingletonScanner.readLine();

                    //TODO validation and enough balance
                    //TODO do transaction with mines cost 500.00 t

                }
            }
        }
    }
}
