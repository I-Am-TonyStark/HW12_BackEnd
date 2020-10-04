package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.BankBranch;

import java.util.List;
import java.util.Optional;

public interface BankBranchRepository extends BaseRepository<BankBranch, Long> {
    List<BankBranch> findAllBankBranches();

    Optional<BankBranch> findOneBankBranchByName(String branchName);
}
