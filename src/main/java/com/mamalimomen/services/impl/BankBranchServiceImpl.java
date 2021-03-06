package com.mamalimomen.services.impl;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Account;
import com.mamalimomen.domains.BankBranch;
import com.mamalimomen.domains.Employee;
import com.mamalimomen.repositories.BankBranchRepository;
import com.mamalimomen.repositories.impl.BankBranchRepositoryImpl;
import com.mamalimomen.services.BankBranchService;

import javax.persistence.EntityManager;
import java.util.Optional;

public class BankBranchServiceImpl extends BaseServiceImpl<BankBranch, Long, BankBranchRepository> implements BankBranchService {
    public BankBranchServiceImpl(EntityManager em) {
        super(new BankBranchRepositoryImpl(em));
    }


    @Override
    public Optional<BankBranch> createBankBranch() {
        return Optional.empty();
    }

    @Override
    public Optional<BankBranch> retrieveBankBranch() {
        BankBranch bankBranch = new BankBranch();
        while (true) {
            try {
                System.out.print("Branch Name: ");
                String branchName = SingletonScanner.readLine();
                bankBranch.setBranchName(branchName);
                return baseRepository.findOneBankBranchByName(branchName);
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            }
        }
    }

    @Override
    public String updateBankBranchAccounts(Employee employee, Account account) {
        BankBranch bankBranch = employee.getWorkOffice();
        bankBranch.addAccount(account);
        if (baseRepository.updateOne(bankBranch)) {
            return "BankBranch's Accounts was update successfully!";
        } else return "There is a problem when update BankBranch's Accounts in database";
    }

    @Override
    public String updateBankBranch() {
        return null;
    }

    @Override
    public String deleteBankBranch() {
        return null;
    }

    @Override
    public void showBankBranches() {

    }

    @Override
    public void showBankBranchByName() {

    }
}
