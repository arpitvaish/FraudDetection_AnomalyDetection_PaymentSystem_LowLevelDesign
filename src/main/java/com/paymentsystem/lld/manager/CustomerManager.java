package com.paymentsystem.lld.manager;

import com.paymentsystem.lld.exception.CustomerNotFoundException;
import com.paymentsystem.lld.model.Customer;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerManager {

    private final ConcurrentHashMap<String, Customer> customers;

    public CustomerManager() {
        this.customers = new ConcurrentHashMap<>();

    }

    public Customer getCustomer(String customerId) {
        if (customers.containsKey(customerId)) {
            return customers.get(customerId);
        } else {
            throw new CustomerNotFoundException();
        }
    }

    public synchronized String createCustomer(Customer customer) {
        String uuid = UUID.randomUUID().toString();
        customer.setId(uuid);
        customers.put(customer.getId(), customer);
        return customer.getId();
    }
}
