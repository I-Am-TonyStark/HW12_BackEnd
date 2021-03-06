package com.mamalimomen.controllers.menus;

import com.mamalimomen.base.controllers.utilities.SecurityManager;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.controllers.utilities.AppManager;
import com.mamalimomen.controllers.utilities.MenuFactory;
import com.mamalimomen.controllers.utilities.Services;
import com.mamalimomen.domains.Account;
import com.mamalimomen.domains.Customer;
import com.mamalimomen.domains.Employee;
import com.mamalimomen.domains.User;
import com.mamalimomen.services.*;

import java.util.Optional;

public final class Menus {

    static void createNewAccount(Employee employee) {
        while (true) {
            System.out.printf("%n====== %S ======%n", "CREATE NEW ACCOUNT");
            AccountService accountService = (AccountService) AppManager.getService(Services.ACCOUNT_SERVICE);
            Optional<Account> oAccount = accountService.createAccount();
            if (oAccount.isPresent()) {
                BankBranchService bankBranchService = (BankBranchService) AppManager.getService(Services.BANK_BRANCH_SERVICE);
                System.out.println(bankBranchService.updateBankBranchAccounts(employee, oAccount.get()));
                System.out.printf("%s%n%n", "Your Account was created Successfully!");
                oAccount.get().printCompleteInformation();
                break;
            } else {
                System.out.print("Can not create any new Account!\nDo you want try again (y/n)? ");
                String answer = SingletonScanner.readLine();
                if (!answer.equalsIgnoreCase("y"))
                    break;
            }
        }
    }

    static void seeYourBranch(Employee employee) {
        System.out.printf("%n====== %s ======%n", "SHOW YOUR BRANCH INFORMATION");
        employee.getWorkOffice().printCompleteInformation();
    }

    static void seeMyEmployees(Employee employee) {
        System.out.printf("%n====== %s ======%n", "SHOW YOUR EMPLOYEES");
        EmployeeService employeeService = (EmployeeService) AppManager.getService(Services.EMPLOYEE_SERVICE);
        employeeService.showEmployeesByBossNationalCode(employee.getNationalCode());
    }

    static void changeMyEmployeesPost(Employee employee) {
        System.out.printf("%n====== %s ======%n", "CHANGE ONE OF YOUR EMPLOYEE'S POST");
        EmployeeService employeeService = (EmployeeService) AppManager.getService(Services.EMPLOYEE_SERVICE);
        if (!employeeService.amIManager(employee)) {
            System.out.println("You have not Permission for this operation yet!");
            return;
        }
        System.out.println(employeeService.updateMyEmployeePost(employee));
    }

    static void signUpOneEmployee(Employee employee) {
        while (true) {
            System.out.printf("%n====== %s ======%n", "EMPLOYMENT NEW EMPLOYEE");
            EmployeeService employeeService = (EmployeeService) AppManager.getService(Services.EMPLOYEE_SERVICE);
            if (!employeeService.amIManager(employee)) {
                System.out.println("You have not Permission for this operation yet!");
                return;
            }
            Optional<Employee> oEmployee = employeeService.createEmployee(employee);
            if (oEmployee.isPresent()) {
                System.out.printf("%s%n", "Your Employee was Employment Successfully!");
                break;
            } else {
                System.out.print("Can not Employment any new Employee!\nDo you want try again (y/n)? ");
                String answer = SingletonScanner.readLine();
                if (!answer.equalsIgnoreCase("y"))
                    break;
            }
        }
    }

    static void changeCardFirstPassword(Customer customer) {
        System.out.printf("%n====== %s ======%n", "CHANGE CARD FIRST PASSWORD");
        CreditCardService creditCardService = (CreditCardService) AppManager.getService(Services.CREDIT_CARD_SERVICE);
        System.out.println(creditCardService.changeFirstPassword(customer));
    }

    static void seeMyActiveAccountBalance(Customer customer) {
        System.out.printf("%n====== %s ======%n", "YOUR ACTIVE ACCOUNTS");
        AccountService accountService = (AccountService) AppManager.getService(Services.ACCOUNT_SERVICE);
        accountService.showActiveAccountsByCustomerNationalNumber(customer);
    }

    static void changeCardSecondPassword(Customer customer) {
        System.out.printf("%n====== %s ======%n", "CHANGE CARD SECOND PASSWORD");
        CreditCardService creditCardService = (CreditCardService) AppManager.getService(Services.CREDIT_CARD_SERVICE);
        System.out.println(creditCardService.changeSecondPassword(customer));
    }

    static void cardToCardTransaction(Customer customer) {
        System.out.printf("%n====== %s ======%n", "CARD TO CARD TRANSACTION");
        CreditCardService creditCardService = (CreditCardService) AppManager.getService(Services.CREDIT_CARD_SERVICE);
        System.out.println(creditCardService.cardToCardTransaction(customer));
    }

    static void seeMyTransactionsByDate(Customer customer) {
        System.out.printf("%n====== %s ======%n", "SEE YOUR TRANSACTIONS");
        TransactionService transactionService = (TransactionService) AppManager.getService(Services.TRANSACTION_SERVICE);
        transactionService.showTransactionsByAccountNumberAndDate(customer);
    }

    static void logInEmployee() {
        while (true) {
            System.out.printf("%n====== %s ======%n", "LOGIN EMPLOYEE");
            EmployeeService employeeService = (EmployeeService) AppManager.getService(Services.EMPLOYEE_SERVICE);
            Optional<Employee> oEmployee = employeeService.retrieveEmployee();

            if (oEmployee.isEmpty()) {
                System.out.print("There is not any Employee with this national code!\nDo you want try again (y/n)? ");
                String answer = SingletonScanner.readLine();
                if (!answer.equalsIgnoreCase("y"))
                    break;
                continue;
            }
            Employee employee = oEmployee.get();

            System.out.print("Employee Password: ");
            String password = SingletonScanner.readLine();
            if (SecurityManager.checkPasswordHash(password, employee.getPassword())) {
                MenuFactory.getMenu(employee).routerMenu();
                break;
            } else {
                System.out.println("Wrong Password!");
            }
        }
    }

    static void activeOrDeActiveAnAccount(Employee employee) {
        System.out.printf("%n====== %s ======%n", "ACTIVE/DeACTIVE AN ACCOUNT");
        AccountService accountService = (AccountService) AppManager.getService(Services.ACCOUNT_SERVICE);
        System.out.println(accountService.updateAccountActiveState());
    }

    static void changeMyPassword(User user) {
        System.out.printf("%n====== %s ======%n", "CHANGE MY PASSWORD");
        if (user instanceof Employee) {
            EmployeeService employeeService = (EmployeeService) AppManager.getService(Services.EMPLOYEE_SERVICE);
            System.out.println(employeeService.updateEmployeePassword((Employee) user));
        } else if (user instanceof Customer) {
            CustomerService customerService = (CustomerService) AppManager.getService(Services.CUSTOMER_SERVICE);
            System.out.println(customerService.updateCustomerPassword((Customer) user));
        }
    }

    static void logInCustomer() {
        while (true) {
            System.out.printf("%n====== %s ======%n", "LOGIN CUSTOMER");
            CustomerService customerService = (CustomerService) AppManager.getService(Services.CUSTOMER_SERVICE);
            Optional<Customer> oCustomer = customerService.retrieveCustomer();

            if (oCustomer.isEmpty()) {
                System.out.print("There is not any Customer with this national code!\nDo you want try again (y/n)? ");
                String answer = SingletonScanner.readLine();
                if (!answer.equalsIgnoreCase("y"))
                    break;
                continue;
            }
            Customer customer = oCustomer.get();

            System.out.print("Customer Password: ");
            String password = SingletonScanner.readLine();
            if (SecurityManager.checkPasswordHash(password, customer.getPassword())) {
                MenuFactory.getMenu(customer).routerMenu();
                break;
            } else {
                System.out.println("Wrong Password!");
            }
        }
    }
}

