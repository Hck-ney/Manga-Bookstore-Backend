package com.example.demo.cart.services;

import com.example.demo.cart.dto.CartRequest;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.exceptions.OrderException;
import com.example.demo.users.entity.Users;
import com.example.demo.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServices {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Cart createCart(CartRequest request){
        Cart cart = new Cart();
        Users user = usersRepository.findById(request.user_id()).orElseThrow(()-> new OrderException("User associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        cart.setUser(user);
        cart.setCart_items(request.itemList());
        return cartRepository.save(cart);
    }

    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }
}
