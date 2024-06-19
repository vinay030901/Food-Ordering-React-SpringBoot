package com.vinay.controller;

import com.vinay.models.Restaurant;
import com.vinay.models.User;
import com.vinay.service.RestaurantService;
import com.vinay.service.RestaurantServiceImpl;
import com.vinay.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.vinay.dto.RestaurantDto;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService = new RestaurantServiceImpl();

    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String jwt, @RequestParam String keyword) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt) throws Exception {
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(
            @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto restaurant = restaurantService.addToFavourites(id, user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

}

