package com.mamalimomen.services.impl;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.controllers.utilities.SecurityManager;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.controllers.utilities.AppManager;
import com.mamalimomen.controllers.utilities.Services;
import com.mamalimomen.domains.Address;
import com.mamalimomen.domains.BankBranch;
import com.mamalimomen.domains.Employee;
import com.mamalimomen.domains.Post;
import com.mamalimomen.repositories.EmployeeRepository;
import com.mamalimomen.repositories.impl.EmployeeRepositoryImpl;
import com.mamalimomen.services.BankBranchService;
import com.mamalimomen.services.EmployeeService;
import com.mamalimomen.services.PostService;

import javax.persistence.EntityManager;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long, EmployeeRepository> implements EmployeeService {
    public EmployeeServiceImpl(EntityManager em) {
        super(new EmployeeRepositoryImpl(em));
    }

    @Override
    public Optional<Employee> createEmployee(Employee manager) {
        Employee employee = new Employee();
        while (true) {
            try {
                System.out.print("First Name: ");
                String firstName = SingletonScanner.readLine();
                if (firstName.equalsIgnoreCase("esc")) {
                    break;
                }
                employee.setFirstName(firstName);

                System.out.print("Last Name: ");
                employee.setLastName(SingletonScanner.readLine());

                System.out.print("National Code: ");
                employee.setNationalCode(SingletonScanner.readLine());

                System.out.print("Password: ");
                employee.setPassword(SingletonScanner.readLine());

                Address address = new Address();
                System.out.print("Country: ");
                address.setCountry(SingletonScanner.readLine());

                System.out.print("City: ");
                address.setCity(SingletonScanner.readLine());

                System.out.print("Avenue: ");
                address.setAvenue(SingletonScanner.readLine());

                System.out.print("Postal Code: ");
                address.setPostalCode(SingletonScanner.readLine());

                employee.setAddress(address);

                while (true) {
                    BankBranchService bankBranchService = (BankBranchService) AppManager.getService(Services.BANK_BRANCH_SERVICE);
                    Optional<BankBranch> oBankBranch = bankBranchService.retrieveBankBranch();
                    if (oBankBranch.isEmpty()) {
                        System.out.println("There is not any Branch with this Name yet!\n");
                        continue;
                    }
                    employee.setWorkOffice(oBankBranch.get());
                    break;
                }

                employee.setBoss(manager);

                while (true) {
                    PostService postService = (PostService) AppManager.getService(Services.POST_SERVICE);
                    Optional<Post> oPost = postService.retrievePost();
                    if (oPost.isEmpty()) {
                        System.out.println("There is not any post with this Title yet!\n");
                        continue;
                    }
                    employee.setPost(oPost.get());
                    break;
                }

                if (baseRepository.saveOne(employee)) {
                    return Optional.of(employee);
                } else {
                    System.out.println("There is a problem when saving This new Employee in database!");
                    break;
                }
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employee> retrieveEmployee() {
        Employee employee = new Employee();
        while (true) {
            try {
                System.out.print("Employee NationalCode: ");
                String employeeNationalCode = SingletonScanner.readLine();
                employee.setNationalCode(employeeNationalCode);
                return baseRepository.findOneEmployeeByNationalCode(employeeNationalCode);
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            }
        }
    }

    @Override
    public String updateEmployee() {
        return null;
    }

    @Override
    public String updateEmployeePassword(Employee employee) {
        while (true) {
            try {
                System.out.print("Old Password: ");
                String oldPassword = SingletonScanner.readLine();
                if (oldPassword.equalsIgnoreCase("esc")) {
                    break;
                } else if (!SecurityManager.checkPasswordHash(oldPassword, employee.getPassword())) {
                    System.out.println("Wrong Password!\n");
                    continue;
                }
                System.out.print("New Password: ");
                String newPassword = SingletonScanner.readLine();
                if (newPassword.equalsIgnoreCase("esc")) {
                    break;
                }
                employee.setPassword(newPassword);

                if (baseRepository.updateOne(employee)) {
                    return "Your password was update successfully!";
                } else {
                    return "There is a problem, We can not update your password!";
                }
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!");
            }
        }
        return "You Cancelled this operation!";
    }

    @Override
    public String updateMyEmployeePost(Employee manager) {
        while (true) {
            try {
                List<Employee> employees = baseRepository.findManyEmployeesByBossNationalCode(manager.getNationalCode());
                if (employees.isEmpty()) {
                    return "You have not any Employee yet!";
                }
                for (int i = 1; i <= employees.size(); i++) {
                    System.out.printf("%d. %s", i, employees.get(i - 1));
                }
                System.out.print("Which employee? ");
                Employee employee = employees.get(SingletonScanner.readInteger() - 1);

                PostService postService = (PostService) AppManager.getService(Services.POST_SERVICE);
                Optional<Post> oPost = postService.retrievePost();
                if (oPost.isEmpty()) {
                    return "There is not any post with this Title yet!";
                }
                employee.setPost(oPost.get());

                if (baseRepository.updateOne(employee)) {
                    return "Your Employee was updated successfully!";
                } else {
                    return "There is a problem, We can not update your Employee!";
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, enter an integer number please!\n");
                SingletonScanner.clearBuffer();
            }
        }
        return "You Cancelled this operation!";
    }

    @Override
    public boolean amIManager(Employee employee) {
        return employee.getPost().getTitle().equalsIgnoreCase("manager");
    }

    @Override
    public String deleteEmployee() {
        return null;
    }

    @Override
    public void showEmployees() {

    }

    @Override
    public void showEmployeesByPostTitle() {

    }

    @Override
    public void showEmployeesByBossNationalCode(String bossNationalCode) {
        List<Employee> employees = baseRepository.findManyEmployeesByBossNationalCode(bossNationalCode);
        if (employees.isEmpty()) {
            System.out.println("You have not any Employee yet!");
            return;
        }
        for (int i = 1; i <= employees.size(); i++) {
            System.out.printf("%d. %s", i, employees.get(i - 1));
        }
    }

    @Override
    public void showEmployeesByBranchName() {

    }

    @Override
    public void showEmployeeByNationalCode() {

    }
}
