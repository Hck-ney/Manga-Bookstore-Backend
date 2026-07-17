package com.example.demo.customer.services;
import com.example.demo.customer.dto.CustomerResponse;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.repository.CustomerRepo;
import com.example.demo.customer.services.interfaces.CustomerInterface;
import com.example.demo.exceptions.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService implements CustomerInterface {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public CustomerResponse getCustomerById(Long customer_id) {
        Customer cus = customerRepo.findById(customer_id).orElseThrow(()-> new OrderException("Customer associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        return CustomerResponse.from(cus);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer updateCustomer(Long customer_id, Customer customer) {
        Customer cus = customerRepo.findById(customer_id).orElseThrow(()-> new OrderException("Customer associated with this Id cannot be found", HttpStatus.NOT_FOUND));
        cus.setName(customer.getName());
        cus.setEmail(customer.getEmail());
        return customerRepo.save(cus);
    }

    @Override
    public void deleteCustomer(Long customer_id) {
        Customer customer = customerRepo.findById(customer_id).orElseThrow(()-> new OrderException("Customer associated with this Id cannot be found", HttpStatus.NOT_FOUND));
        customerRepo.delete(customer);
    }
}
