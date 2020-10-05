package com.mamalimomen.controllers.menus;

import com.mamalimomen.domains.Employee;

public class EmployeeMenu<U extends Employee> extends AbstractMenu<U> {

    public EmployeeMenu(U employee) {
        super(employee.getFullName() + "'s menu", new String[]{
                "Change your password",
                "Active or DeActive an Account",
                "See Your Branch",
                "See your Employees",
                "Change your Employees's Post",
                "SignUp an Employee",
                "Create new Account"
        }, employee);
    }

    @Override
    public void routerMenu() {
        while (true) {
            switch (showMenu()) {
                case 1:
                    Menus.changeMyPassword(thisMenuUser);
                    break;
                case 2:
                    Menus.activeOrDeActiveAnAccount(thisMenuUser);
                    break;
                case 3:
                    Menus.seeYourBranch(thisMenuUser);
                    break;
                case 4:
                    Menus.seeMyEmployees(thisMenuUser);
                    break;
                case 5:
                    Menus.changeMyEmployeesPost(thisMenuUser);
                    break;
                case 6:
                    Menus.signUpOneEmployee(thisMenuUser);
                    break;
                case 7:
                    Menus.createNewAccount(thisMenuUser);
                    break;
                default:
                    return;
            }
        }
    }
}
