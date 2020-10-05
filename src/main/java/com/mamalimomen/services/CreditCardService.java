package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardService extends BaseService<CreditCard, Long> {

    Optional<CreditCard> createCreditCard();

    Optional<CreditCard> retrieveCreditCard();

    String updateCreditCard();

    String deleteCreditCard();

    List<CreditCard> findCreditCards();

    Optional<CreditCard> findCreditCardByNumber();

    String setFirstPassword();

    String changeFirstPassword();

    String setSecondPassword();

    String changeSecondPassword();

    String cardToCardTransaction();

    String checkAccountBalanceTransaction();

    boolean transactionPermission();
}
