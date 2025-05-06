package com.mycompany.testapi.dao;

import com.mycompany.testapi.model.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerDao {
    private static final Map<Integer, Customer> customers = new HashMap<>();
    private static int currentId = 1;

    public Customer createCustomer(Customer customer) {
        customer.setId(currentId++);
        customers.put(customer.getId(), customer);
        return customer;
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    public Customer getCustomerById(int id) {
        return customers.get(id);
    }

    public Customer updateCustomer(int id, Customer updatedCustomer) {
        updatedCustomer.setId(id);
        customers.put(id, updatedCustomer);
        return updatedCustomer;
    }

    public Customer deleteCustomer(int id) {
        return customers.remove(id);
    }
}
