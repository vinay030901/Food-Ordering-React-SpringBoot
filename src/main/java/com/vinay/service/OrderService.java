package com.vinay.service;

import com.vinay.models.Order;
import com.vinay.models.User;
import com.vinay.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderRequest order, User user) throws Exception;
    public Order updateOrder(Long orderId, String status) throws Exception;
    public void cancelOrder(Long orderId) throws Exception;
    public List<Order> getUsersOrder(Long userId) throws Exception;
    public List<Order> getRestaurantOrders(Long restaurantId,String orderStatus) throws Exception;
    public Order findOrderById(Long orderId) throws Exception;

}
