package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Account;
import com.mamalimomen.dtos.AccountSearchDTO;

import java.util.List;
import java.util.Optional;

public interface AccountService extends BaseService<Account, Long> {
    Optional<Account> findOneAccount(String title);

    List<Account> findAllAccounts();

    List<Account> findPublishedAccounts();

    List<Account> findAllUserAccounts(Long userID);

    List<Account> advancedSearch(AccountSearchDTO asDTO);
}
