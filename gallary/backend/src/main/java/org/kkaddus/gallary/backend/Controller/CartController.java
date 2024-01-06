package org.kkaddus.gallary.backend.Controller;

import org.kkaddus.gallary.backend.entity.Cart;
import org.kkaddus.gallary.backend.entity.Item;
import org.kkaddus.gallary.backend.repository.CartRepository;
import org.kkaddus.gallary.backend.repository.ItemRepository;
import org.kkaddus.gallary.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/api/cart/items")
    public ResponseEntity getCartItems(@CookieValue(value="token",required = false) String token){
        if(!jwtService.isValid(token)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        int memberId = jwtService.getId(token);
        List<Cart> carts = cartRepository.findByMemberId(memberId);
        List<Integer> itemIds = carts.stream().map(Cart::getItemId).toList();
        List<Item> itemList = itemRepository.findByIdIn(itemIds);
        return new ResponseEntity<>(itemList,HttpStatus.OK);
    }

    @PostMapping("/api/cart/items/{itemId}")
    public ResponseEntity getCartItem(@PathVariable int itemId,
                                   @CookieValue(value="token",required = false) String token){
        if(!jwtService.isValid(token)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        int memberId = jwtService.getId(token);

        Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setMemberId(memberId);
            newCart.setItemId(itemId);
            cartRepository.save(newCart);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity removeCartItem(@PathVariable int itemId,
                                      @CookieValue(value="token",required = false) String token){

        if(!jwtService.isValid(token)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        int memberId = jwtService.getId(token);
        Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);
        cartRepository.delete(cart);

        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
