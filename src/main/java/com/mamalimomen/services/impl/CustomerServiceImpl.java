package com.mamalimomen.services.impl;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.controllers.utilities.SecurityManager;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Address;
import com.mamalimomen.domains.Customer;
import com.mamalimomen.repositories.CustomerRepository;
import com.mamalimomen.repositories.impl.CustomerRepositoryImpl;
import com.mamalimomen.services.CustomerService;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long, CustomerRepository> implements CustomerService {
    public CustomerServiceImpl(EntityManager em) {
        super(new CustomerRepositoryImpl(em));
    }

    @Override
    public Optional<Customer> createCustomer() {
        Customer customer = new Customer();
        while (true) {
            try {
                System.out.print("First Name: ");
                String firstName = SingletonScanner.readLine();
                if (firstName.equalsIgnoreCase("esc")) {
                    break;
                }
                customer.setFirstName(firstName);

                System.out.print("Last Name: ");
                customer.setLastName(SingletonScanner.readLine());

                System.out.print("National Code: ");
                customer.setNationalCode(SingletonScanner.readLine());

                System.out.print("Password: ");
                customer.setPassword(SingletonScanner.readLine());

                Address address = new Address();
                System.out.print("Country: ");
                address.setCountry(SingletonScanner.readLine());

                System.out.print("City: ");
                address.setCity(SingletonScanner.readLine());

                System.out.print("Avenue: ");
                address.setAvenue(SingletonScanner.readLine());

                System.out.print("Postal Code: ");
                address.setPostalCode(SingletonScanner.readLine());

                customer.setAddress(address);

                return Optional.of(customer);
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Customer> retrieveCustomer() {
        Customer customer = new Customer();
        while (true) {
            try {
                System.out.print("Customer NationalCode: ");
                String customerNationalCode = SingletonScanner.readLine();
                customer.setNationalCode(customerNationalCode);
                return baseRepository.findOneCustomerByNationalCode(customerNationalCode);
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            }
        }
    }

    @Override
    public String updateCustomer() {
        return null;
    }

    @Override
    public String updateCustomerPassword(Customer customer) {
        while (true) {
            try {
                System.out.print("Old Password: ");
                String oldPassword = SingletonScanner.readLine();
                if (oldPassword.equalsIgnoreCase("esc")) {
                    break;
                } else if (!SecurityManager.checkPasswordHash(oldPassword, customer.getPassword())) {
                    System.out.println("Wrong Password!\n");
                    continue;
                }
                System.out.print("New Password: ");
                String newPassword = SingletonScanner.readLine();
                if (newPassword.equalsIgnoreCase("esc")) {
                    break;
                }
                customer.setPassword(newPassword);

                if (baseRepository.updateOne(customer)) {
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
    public String deleteCustomer() {
        return null;
    }

    @Override
    public void showCustomers() {

    }

    @Override
    public void showCustomerByNationalCode() {

    }
}
