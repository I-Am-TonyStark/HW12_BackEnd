package com.mamalimomen.controllers.menus;

import com.mamalimomen.domains.Customer;

public class CustomerMenu<U extends Customer> extends AbstractMenu<U> {
    public CustomerMenu(U customer) {
        super(customer.getFullName() + "'s menu", new String[]{
                "Change your password",
                "See and Modify your articles",
                "Write new article",
                "See your CreditCard"
        }, customer);
    }

    @Override
    public void routerMenu() {
        while (true) {
            switch (showMenu()) {
                case 1:
                    Menus.changePassword(thisMenuUser);
                    break;
                case 2:
                    Menus.seeAndModifyArticles(thisMenuUser);
                    break;
                case 3:
                    Menus.writeNewArticle(thisMenuUser);
                    break;
                case 4:
                    Menus.seeCreditCard(thisMenuUser);
                    break;
                default:
                    return;
            }
        }
    }
}
