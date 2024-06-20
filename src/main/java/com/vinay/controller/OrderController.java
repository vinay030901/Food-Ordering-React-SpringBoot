package com.vinay.controller;

import com.vinay.models.CartItem;
import com.vinay.models.Order;
import com.vinay.request.AddCartItemRequest;
import com.vinay.request.OrderRequest;
import com.vinay.service.OrderService;
import com.vinay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        Order order=orderService.createOrder(req, userService.findUserByJwtToken(jwt));
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception {
        List<Order> orders=orderService.getUsersOrder(userService.findUserByJwtToken(jwt).getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
