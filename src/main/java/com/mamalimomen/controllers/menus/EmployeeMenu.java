package com.mamalimomen.controllers.menus;

import com.mamalimomen.domains.Employee;

public class EmployeeMenu<U extends Employee> extends AbstractMenu<U>{

    public EmployeeMenu(U employee) {
        super(employee.getFullName() + "'s menu", new String[]{
                "Change your password",
                "See and Modify your articles",
                "Write new article",
                "See your CreditCard",
                "Change role of users",
                "Publish or unPublish an article",
                "Delete an article",
                "Create a category",
                "Create a tag"
        }, employee);
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
                case 5:
                    Menus.changeRoleOfUsers(thisMenuUser);
                    break;
                case 6:
                    Menus.seeAllWrittenArticles(thisMenuUser);
                    break;
                case 7:
                    Menus.deleteArticles(thisMenuUser);
                    break;
                case 8:
                    Menus.createNewCategory(thisMenuUser);
                    break;
                case 9:
                    Menus.createNewTag(thisMenuUser);
                    break;
                default:
                    return;
            }
        }
    }
}
