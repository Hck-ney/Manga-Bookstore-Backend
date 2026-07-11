package com.example.demo.services;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.services.interfaces.CustomerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.customerMapper;
import java.util.List;

@Service
public class CustomerService implements CustomerInterface {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private customerMapper map;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepo.getReferenceById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {

    }
}
