package com.example.demo.cartItem.services;

import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.cartItem.entity.CartItem;
import com.example.demo.cartItem.repository.CartItemRepository;
import com.example.demo.exceptions.OrderException;
import com.example.demo.users.entity.Users;
import com.example.demo.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CartItemServices {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CartRepository cartRepository;

    public CartItem createCartItem(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }

//    public List<CartItem> getAllCartItem(){
//        return cartItemRepository.findAll();
//    }

    public void deleteCartItem(String username, Long id){
        // THE ID OF THE MANGA AND USER OWNERSHIP MUST MATCH
        // THIS SHOULD BE A SECURE WAY TO DELETE ONLY ON YOUR CART
        Users user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new OrderException("User associated with this username cannot be found", HttpStatus.NOT_FOUND);
        }
        Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new OrderException("Cart associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        CartItem item = cart.getCart_items().stream()
                .filter((manga)-> manga.getManga().getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new OrderException("Cart Item associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        cart.getCart_items().remove(item);
        cartRepository.save(cart);
    }
}
