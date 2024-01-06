package org.kkaddus.gallary.backend.Controller;

import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.kkaddus.gallary.backend.dto.OrderDto;
import org.kkaddus.gallary.backend.entity.Cart;
import org.kkaddus.gallary.backend.entity.Item;
import org.kkaddus.gallary.backend.entity.Order;
import org.kkaddus.gallary.backend.repository.CartRepository;
import org.kkaddus.gallary.backend.repository.ItemRepository;
import org.kkaddus.gallary.backend.repository.OrderRepository;
import org.kkaddus.gallary.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/api/orders")
    public ResponseEntity getOrders(@CookieValue(value="token",required = false) String token) {
        if (!jwtService.isValid(token)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        int memberId = jwtService.getId(token);
        List<Order> orders = orderRepository.findByMemberIdOrderByIdDesc(memberId);

        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/api/orders")
    public ResponseEntity getCartItem(@RequestBody OrderDto orderDto,
                                      @CookieValue(value="token",required = false) String token){
        if(!jwtService.isValid(token)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        int memberId = jwtService.getId(token);

        Order order = new Order();
        order.setMemberId(memberId);
        order.setName(orderDto.getName());
        order.setItems(orderDto.getItems());
        order.setAddress(orderDto.getAddress());
        order.setPayment(orderDto.getPayment());
        order.setCardNumber(orderDto.getCardNumber());

        orderRepository.save(order);
        cartRepository.deleteByMemberId(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
