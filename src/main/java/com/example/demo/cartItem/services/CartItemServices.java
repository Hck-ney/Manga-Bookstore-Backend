package com.example.demo.cartItem.services;

import com.example.demo.cartItem.entity.CartItem;
import com.example.demo.cartItem.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServices {

    private CartItemRepository repo;

    public CartItem createCartItem(CartItem cartItem){
        return repo.save(cartItem);
    }

    public List<CartItem> getAllCartItem(){
        return repo.findAll();
    }
}
