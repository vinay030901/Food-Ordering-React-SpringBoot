package com.vinay.service;

import com.vinay.models.Category;
import com.vinay.models.Food;
import com.vinay.models.Restaurant;
import com.vinay.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId,boolean isVegetarian,boolean isNonveg, boolean isSeasonal,String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailability(Long foodId) throws Exception;
//    public Food updateFood(Long foodId, CreateFoodRequest updCreateFood) throws Exception;
}
