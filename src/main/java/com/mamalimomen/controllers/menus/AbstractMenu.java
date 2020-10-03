package com.mamalimomen.controllers.menus;

import com.mamalimomen.base.controllers.menus.Menu;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.domains.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public abstract class AbstractMenu<U extends User> implements Menu {
    protected final U thisMenuUser;
    private final String title;
    private final List<String> options;

    public AbstractMenu(String title, String[] options, U user) {
        this.options = new ArrayList<>(List.of(options));
        this.title = title;
        this.thisMenuUser = user;
    }

    @Override
    public int showMenu() {
        while (true) {
            System.out.printf("%n====== %s ======%n", title.toUpperCase());
            for (int i = 1; i <= options.size(); i++) {
                System.out.printf("%d. %s%n", i, options.get(i - 1));
            }
            System.out.print("Enter your choice (or other number for \"exit\"): ");
            try {
                return SingletonScanner.readInteger();
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, enter an integer number please!");
                SingletonScanner.clearBuffer();
            }
        }
    }
}
