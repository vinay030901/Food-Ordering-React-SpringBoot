package com.vinay.service;

import com.vinay.models.IngredientCategory;
import com.vinay.models.IngredientItem;

import java.util.List;

public interface IngredientsService {
    public IngredientCategory createIngredientCategory(String name,Long restaurantId) throws Exception;
    public IngredientCategory findIngredientCategoryById(Long categoryId) throws Exception;
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception;
    public IngredientItem createIngredientItem(Long restaurantId,String ingredientName, Long categoryId) throws Exception;
    public List<IngredientItem> findRestaurantsIngredients(Long restaurantId) throws Exception;
    public IngredientItem updateStock(Long id) throws Exception;
}
