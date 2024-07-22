package com.vinay.service;

import com.vinay.models.Category;
import com.vinay.models.Food;
import com.vinay.models.Restaurant;
import com.vinay.repository.FoodRepository;
import com.vinay.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository foodRepository;
    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(Long.valueOf(req.getPrice()));
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());
        food.setCreationDate(new Date());
        Food savedFood=foodRepository.save(food);
        restaurant.getFoods().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food= findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food); // the food will remain in our restaurant but when the customer extracts all the food of a restaurant
        // it won't be able to extract the deleted food
    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) {
        List<Food> foods=foodRepository.findByRestaurantId(restaurantId);
        if(isVegetarian){
            foods.removeIf(food -> !food.isVegetarian());
        }
        if(isNonveg){
            foods.removeIf(food -> food.isVegetarian());
        }
        if(isSeasonal){
            foods.removeIf(food -> !food.isSeasonal());
        }
        if(foodCategory!=null && !foodCategory.isEmpty()){
            foods.removeIf(food -> !food.getFoodCategory().getName().equals(foodCategory));
        }
        return foods;
    }



    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> food = foodRepository.findById(foodId);
        if (food.isEmpty()) {
            throw new Exception("Food not found with this id: "+foodId);
        }
        return food.get();
    }

    @Override
    public Food updateAvailability(Long foodId) throws Exception {
        Food food=findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
