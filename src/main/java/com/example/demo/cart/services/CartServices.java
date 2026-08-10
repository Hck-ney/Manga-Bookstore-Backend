package com.example.demo.cart.services;

import com.example.demo.cart.dto.CartRequest;
import com.example.demo.cart.dto.CartResponse;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.cartItem.dto.CartItemRequest;
import com.example.demo.cartItem.dto.CartItemResponse;
import com.example.demo.cartItem.entity.CartItem;
import com.example.demo.cartItem.repository.CartItemRepository;
import com.example.demo.exceptions.OrderException;
import com.example.demo.manga.entity.Manga;
import com.example.demo.manga.repository.MangaRepository;
import com.example.demo.users.entity.Users;
import com.example.demo.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServices {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MangaRepository mangaRepository;

    public CartResponse createCart(CartRequest request){
        Cart cart = new Cart();
        Users user = usersRepository.findById(request.user_id()).orElseThrow(()-> new OrderException("User associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        cart.setUser(user);
        List<CartItem> items = new ArrayList<>();
        for(CartItemRequest x: request.itemList()){
            CartItem ci = new CartItem();
            ci.setCart(cart);
            Manga manga = mangaRepository.findById(x.manga_id()).orElseThrow(()-> new OrderException("Manga associated with this ID cannot be found", HttpStatus.NOT_FOUND));
            ci.setManga(manga);
            ci.setPrice(manga.getPrice());
            ci.setQuantity(x.quantity());
            items.add(ci);
        }
        cart.setCart_items(items);
        Cart newCart = cartRepository.save(cart);
        return new CartResponse(
                newCart.getId(),
                newCart.getUser().getId(),
                CartItemResponse.toListResponse(items)
        );
    }

//    public List<CartResponse> getAllCart(){
//        List<Cart> list = cartRepository.findAll();
//        return CartResponse.toListEntity(list);
//    }

    public CartResponse getCart(String email){
        Users user = usersRepository.findByEmail(email).orElseThrow(()-> new OrderException("User associated with this Email cannot be found", HttpStatus.NOT_FOUND));
        Cart cart = cartRepository.findById(user.getCart().getId()).orElseThrow(()-> new OrderException("Cart associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        return CartResponse.toResponse(cart);
    }


    public CartResponse addItemToCart(String email, CartItemRequest itemRequest) {
        Users user = usersRepository.findByEmail(email).orElseThrow(()-> new OrderException("User associated with this Email cannot be found", HttpStatus.NOT_FOUND));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new OrderException("Cart associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        Manga manga = mangaRepository.findById(itemRequest.manga_id())
                .orElseThrow(() -> new OrderException("Manga not found", HttpStatus.NOT_FOUND));

        // CHECKS IF THE MANGA TO BE ADDED ALREADY EXISTS
        Optional<CartItem> existingItem = cart.getCart_items().stream()
                .filter(item -> item.getManga().getId().equals(manga.getId()))
                .findFirst();

        if(existingItem.isPresent())
        {
            throw new OrderException("This item is already in your cart", HttpStatus.CONFLICT);
        }
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setManga(manga);
        item.setPrice(manga.getPrice());
        item.setQuantity(itemRequest.quantity());


        cart.getCart_items().add(item);

        cartRepository.save(cart);

        return CartResponse.toResponse(cart);
    }

    public CartResponse updateItemQuantity(Long itemId, Integer newQuantity) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new OrderException("Cart item not found", HttpStatus.NOT_FOUND));

        item.setQuantity(newQuantity);
        cartItemRepository.save(item);

        return CartResponse.toResponse(item.getCart());
    }

    public CartResponse removeItemFromCart(Long itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new OrderException("Cart item not found", HttpStatus.NOT_FOUND));

        Cart cart = item.getCart();
        cart.getCart_items().remove(item);
        cartItemRepository.delete(item);
        return CartResponse.toResponse(cart);
    }

    // FOR TESTING ONLY AND SHOULD BE DELETED
    public void deleteCart(Long id){
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new OrderException("Cart associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        cartRepository.delete(cart);
    }
}
