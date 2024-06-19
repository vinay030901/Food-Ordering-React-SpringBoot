package com.vinay.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinay.dto.RestaurantDto;
import com.vinay.models.Address;
import com.vinay.models.Restaurant;
import com.vinay.models.User;
import com.vinay.repository.AddressRepository;
import com.vinay.repository.RestaurantRepository;
import com.vinay.repository.UserRepository;
import com.vinay.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {
        Address address = addressRepository.save(request.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setDescription(request.getDescription());
        restaurant.setImages(request.getImages());
        restaurant.setName(request.getName());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updCreateRestaurant)
            throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if (updCreateRestaurant.getContactInformation() != null) {
            restaurant.setContactInformation(updCreateRestaurant.getContactInformation());
        }
        if (updCreateRestaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updCreateRestaurant.getCuisineType());
        }
        if (updCreateRestaurant.getDescription() != null) {
            restaurant.setDescription(updCreateRestaurant.getDescription());
        }
        if (updCreateRestaurant.getImages() != null) {
            restaurant.setImages(updCreateRestaurant.getImages());
        }
        if (updCreateRestaurant.getName() != null) {
            restaurant.setName(updCreateRestaurant.getName());
        }
        if (updCreateRestaurant.getOpeningHours() != null) {
            restaurant.setOpeningHours(updCreateRestaurant.getOpeningHours());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
        if (opt.isEmpty())
            throw new Exception("restaurant not found with this id");
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null)
            throw new Exception("restaurant not found with owner id: " + userId);
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        boolean isFavorited=false;
        List<RestaurantDto> favorites = user.getFavourites();
        for(RestaurantDto fav : favorites) {
            if (fav.getId() == restaurantId) {
                isFavorited = true;
                break;
            }
        }
        if(isFavorited)
            favorites.removeIf(fav -> fav.getId() == restaurantId);
        else {
            favorites.add(dto);
        }
//        user.setFavourites(favorites);
        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }

}
