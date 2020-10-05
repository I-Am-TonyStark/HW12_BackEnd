package com.mamalimomen.controllers.menus;

import com.mamalimomen.domains.User;

public class MainMenu extends AbstractMenu<User> {
    public MainMenu() {
        super("main menu", new String[]{
                "Login Customer",
                "Login Employee",
        }, null);
    }

    @Override
    public void routerMenu() {
        while (true) {
            switch (showMenu()) {
                case 1:
                    Menus.logInCustomer();
                    break;
                case 2:
                    Menus.logInEmployee();
                    break;
                default:
                    return;
            }
        }
    }
}
