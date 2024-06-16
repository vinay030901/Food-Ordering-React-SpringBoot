package com.vinay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinay.dto.RestaurantDto;
import com.vinay.models.Restaurant;
import com.vinay.models.User;
import com.vinay.repository.RestaurantRepository;
import com.vinay.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRestaurant'");
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest request) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRestaurant'");
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRestaurant'");
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRestaurant'");
    }

    @Override
    public List<Restaurant> searchRestaurant() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchRestaurant'");
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findRestaurantById'");
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRestaurantByUserId'");
    }

    @Override
    public RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToFavourites'");
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRestaurantStatus'");
    }

}
