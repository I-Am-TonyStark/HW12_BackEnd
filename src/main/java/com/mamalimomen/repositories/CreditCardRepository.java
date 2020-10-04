package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.CreditCard;

public interface CreditCardRepository extends BaseRepository<CreditCard,Long> {

    //TODO crud services
    //TODO setFirstPassword service
    //TODO changeFirstPassword service
    //TODO setSecondPassword service
    //TODO changeSecondPassword service
    //TODO cardToCard with cost

    //validate origin and destination card number (12 or 16 digit) and exist in system
    //validate activity of account before transaction
    //after three try for secondPassword, diActive account and customer can not do any transaction!
}
