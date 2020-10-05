package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account, Long> {
    List<Account> findAllAccounts();

    List<Account> findAllActiveAccounts();

    Optional<Account> findOneAccountByAccountNumber(String accountNumber);

    List<Account> findManyAccountsByCustomerNationalCode(String customerNationalCode);
}
