package com.vinay.controller;

import com.vinay.models.CartItem;
import com.vinay.models.Order;
import com.vinay.models.User;
import com.vinay.request.AddCartItemRequest;
import com.vinay.request.OrderRequest;
import com.vinay.response.PaymentResponse;
import com.vinay.service.OrderService;
import com.vinay.service.PaymentService;
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


    @Autowired
    private PaymentService paymentService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Order order=orderService.createOrder(req, userService.findUserByJwtToken(jwt));
        PaymentResponse res=paymentService.createPaymentLink(order);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<Order> orders=orderService.getUsersOrder(userService.findUserByJwtToken(jwt).getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
