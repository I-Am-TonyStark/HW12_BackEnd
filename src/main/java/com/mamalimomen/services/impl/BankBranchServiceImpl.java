package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.BankBranch;
import com.mamalimomen.repositories.BankBranchRepository;
import com.mamalimomen.repositories.impl.BankBranchRepositoryImpl;
import com.mamalimomen.services.BankBranchService;

import javax.persistence.EntityManager;

public class BankBranchServiceImpl extends BaseServiceImpl<BankBranch, Long, BankBranchRepository> implements BankBranchService {
    public BankBranchServiceImpl(EntityManager em) {
        super(new BankBranchRepositoryImpl(em));
    }
}
