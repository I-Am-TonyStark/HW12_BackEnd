package com.mamalimomen.controllers.utilities;

import com.mamalimomen.base.controllers.menus.Menu;
import com.mamalimomen.controllers.menus.CustomerMenu;
import com.mamalimomen.controllers.menus.EmployeeMenu;
import com.mamalimomen.controllers.menus.MainMenu;
import com.mamalimomen.domains.Customer;
import com.mamalimomen.domains.Employee;
import com.mamalimomen.domains.User;

public final class MenuFactory {
    private MenuFactory() {
    }

    public static Menu getMenu(User user) {
        if (user instanceof Employee)
            return new EmployeeMenu<>((Employee) user);
        else if (user instanceof Customer)
            return new CustomerMenu<>((Customer) user);
        else return new MainMenu();
    }
}
