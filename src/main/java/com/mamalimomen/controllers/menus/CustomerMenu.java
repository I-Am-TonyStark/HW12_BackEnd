package com.mamalimomen.controllers.menus;

import com.mamalimomen.domains.Customer;

public class CustomerMenu<U extends Customer> extends AbstractMenu<U> {
    public CustomerMenu(U customer) {
        super(customer.getFullName() + "'s menu", new String[]{
                "Change your password",
                "Change CreditCard First Password",
                "Change CreditCard Second Password",
                "See your Active Accounts",
                "Card to Card Transaction",
                "See your Transactions"
        }, customer);
    }

    @Override
    public void routerMenu() {
        while (true) {
            switch (showMenu()) {
                case 1:
                    Menus.changeMyPassword(thisMenuUser);
                    break;
                case 2:
                    Menus.changeCardFirstPassword(thisMenuUser);
                    break;
                case 3:
                    Menus.changeCardSecondPassword(thisMenuUser);
                    break;
                case 4:
                    Menus.seeMyActiveAccountBalance(thisMenuUser);
                    break;
                case 5:
                    Menus.cardToCardTransaction(thisMenuUser);
                    break;
                case 6:
                    Menus.seeMyTransactionsByDate(thisMenuUser);
                    break;
                default:
                    return;
            }
        }
    }
}
