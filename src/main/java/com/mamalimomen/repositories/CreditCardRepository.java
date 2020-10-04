package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository extends BaseRepository<CreditCard, Long> {

    List<CreditCard> findAllCreditCards();

    Optional<CreditCard> findOneCreditCardByNumber(String cardNumber);
}
