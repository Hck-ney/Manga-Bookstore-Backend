package com.example.demo.users.services;
import com.example.demo.cart.entity.Cart;
import com.example.demo.users.dto.UsersRequest;
import com.example.demo.users.dto.UsersResponse;
import com.example.demo.users.entity.Users;
import com.example.demo.users.repository.UsersRepository;
import com.example.demo.users.services.interfaces.UsersInterface;
import com.example.demo.exceptions.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersService implements UsersInterface {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsersResponse createUser(UsersRequest request) {
        if(request.username() == null){
            throw new IllegalArgumentException("username is null");
        }
        if(request.address() == null){
            throw new IllegalArgumentException("address is null");
        }
        if(request.phone_number() == null){
            throw new IllegalArgumentException("phone number is null");
        }
        if(request.email() == null){
            throw new IllegalArgumentException("email is null");
        }
        if(request.password() == null){
            throw new IllegalArgumentException("password is null");
        }
        String encodedPass = passwordEncoder.encode(request.password());
        Cart cart = new Cart();
        Users user = UsersRequest.toEntity(request, cart, encodedPass);
        cart.setUser(user);
        usersRepository.save(user);
        return UsersResponse.toResponse(user);
    }

    @Override
    public UsersResponse getUserByUsername(String username) {
        if(username == null){
            throw new IllegalArgumentException("Username is null");
        }
        Users cus = usersRepository.findByUsername(username);

        return UsersResponse.toResponse(cus);
    }

    @Override
    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    @Override
    public Users updateUser(Long customer_id, Users users) {
        Users cus = usersRepository.findById(customer_id).orElseThrow(()-> new OrderException("Customer associated with this Id cannot be found", HttpStatus.NOT_FOUND));
        cus.setUsername(users.getUsername());
        cus.setEmail(users.getEmail());
        return usersRepository.save(cus);
    }

    @Override
    public void deleteUser(Long customer_id) {
        Users users = usersRepository.findById(customer_id).orElseThrow(()-> new OrderException("Customer associated with this Id cannot be found", HttpStatus.NOT_FOUND));
        usersRepository.delete(users);
    }
}
