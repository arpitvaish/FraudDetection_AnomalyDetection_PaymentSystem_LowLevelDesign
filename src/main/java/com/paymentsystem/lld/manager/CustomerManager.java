package com.paymentsystem.lld.manager;

import com.paymentsystem.lld.exception.CustomerNotFoundException;
import com.paymentsystem.lld.model.Customer;
import com.paymentsystem.lld.model.KYCType;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CustomerManager {

    private final ConcurrentHashMap<String, Customer> customers;

    public CustomerManager() {
        this.customers = new ConcurrentHashMap<>();

    }

    public Customer getCustomer(String customerId) {
        if (customers.containsKey(customerId) && customerId != null && !customerId.isEmpty()) {
            return customers.get(customerId);
        } else {
            throw new CustomerNotFoundException();
        }
    }

    public synchronized String createCustomer(Customer customer) {
        String uuid = UUID.randomUUID().toString();
        customer.setId(uuid);
        if (customer.getCountry() == null || customer.getCountry().isEmpty()) customer.setCountry("India");
        if (customer.getDob() == null || customer.getDob().isEmpty()) customer.setDob("01-01-1990");
        if (customer.getCity() == null || customer.getCity().isEmpty()) customer.setCity("Bangalore");
        if (customer.getFatherName() == null || customer.getFatherName().isEmpty()) customer.setFatherName("Ram");
        if (customer.getMotherName() == null || customer.getMotherName().isEmpty()) customer.setMotherName("Sita");
        if (customer.getKycType() == null) customer.setKycType(KYCType.ADHAAR);
        if (customer.getKycNumber() == null || customer.getKycNumber().isEmpty()) customer.setKycNumber("123-456-789");
        customers.put(customer.getId(), customer);
        return customer.getId();
    }
}
