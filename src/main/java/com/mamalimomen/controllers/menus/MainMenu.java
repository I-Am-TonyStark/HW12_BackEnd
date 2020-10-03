package com.mamalimomen.controllers.menus;

public class MainMenu extends AbstractMenu {
    public MainMenu() {
        super("main menu", new String[]{
                "Login user",
                "SignUp user",
                "See articles"
        }, null);
    }

    @Override
    public void routerMenu() {
        while (true) {
            switch (showMenu()) {
                case 1:
                    Menus.logInUser();
                    break;
                case 2:
                    Menus.signUpUser();
                    break;
                case 3:
                    Menus.seePublishedArticles();
                    break;
                default:
                    return;
            }
        }
    }
}
