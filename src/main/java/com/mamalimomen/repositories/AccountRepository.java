package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.Account;

import java.util.List;

public interface AccountRepository extends BaseRepository<Account,Long> {
    List<Account> advancedSearch(AccountSearchDTO asDTO);
    List<Account> seeAccountsOfCustomer(Long customerID);
    //TODO crud services
}
