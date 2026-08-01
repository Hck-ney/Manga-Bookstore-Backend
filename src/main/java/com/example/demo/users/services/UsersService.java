package com.example.demo.users.services;
import com.example.demo.users.dto.UsersResponse;
import com.example.demo.users.entity.Users;
import com.example.demo.users.repository.UsersRepository;
import com.example.demo.users.services.interfaces.UsersInterface;
import com.example.demo.exceptions.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersService implements UsersInterface {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users createCustomer(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public UsersResponse getCustomerById(Long customer_id) {
        Users cus = usersRepository.findById(customer_id).orElseThrow(()-> new OrderException("Customer associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        return UsersResponse.from(cus);
    }

    @Override
    public List<Users> getAllCustomers() {
        return usersRepository.findAll();
    }

    @Override
    public Users updateCustomer(Long customer_id, Users users) {
        Users cus = usersRepository.findById(customer_id).orElseThrow(()-> new OrderException("Customer associated with this Id cannot be found", HttpStatus.NOT_FOUND));
        cus.setUsername(users.getUsername());
        cus.setEmail(users.getEmail());
        return usersRepository.save(cus);
    }

    @Override
    public void deleteCustomer(Long customer_id) {
        Users users = usersRepository.findById(customer_id).orElseThrow(()-> new OrderException("Customer associated with this Id cannot be found", HttpStatus.NOT_FOUND));
        usersRepository.delete(users);
    }
}
