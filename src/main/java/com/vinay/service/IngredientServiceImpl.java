package com.vinay.service;

import com.vinay.models.IngredientCategory;
import com.vinay.models.IngredientItem;
import com.vinay.models.Restaurant;
import com.vinay.repository.IngredientsCategoryRepository;
import com.vinay.repository.IngredientsItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientsService{

    @Autowired
    private IngredientsCategoryRepository ingredientCategoryRepository;

    @Autowired
    private IngredientsItemRepository ingredientItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        IngredientCategory ingredientCategory=new IngredientCategory();
        ingredientCategory.setName(name);
        ingredientCategory.setRestaurant(restaurantService.findRestaurantById(restaurantId));
        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long categoryId) throws Exception {
        Optional<IngredientCategory> category=ingredientCategoryRepository.findById(categoryId);
        if(category.isEmpty())
            throw new Exception("Category not found");
        return category.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception {
        Optional<Restaurant> restaurant= Optional.ofNullable(restaurantService.findRestaurantById(restaurantId));
        if(restaurant.isEmpty())
            throw new Exception("Restaurant not found");
        return ingredientCategoryRepository.findByRestaurantId(restaurantId);

    }

    @Override
    public IngredientItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        IngredientItem ingredientItem=new IngredientItem();
        IngredientCategory category=findIngredientCategoryById(categoryId);
        ingredientItem.setName(ingredientName);
        ingredientItem.setCategory(category);
        ingredientItem.setRestaurant(restaurantService.findRestaurantById(restaurantId));
        IngredientItem ingredientItem1=ingredientItemRepository.save(ingredientItem);
        category.getIngredients().add(ingredientItem1);
        return ingredientItem1;
    }

    @Override
    public List<IngredientItem> findRestaurantsIngredients(Long restaurantId) throws Exception {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItem updateStock(Long id) throws Exception {
        Optional<IngredientItem> optionalIngredientItem=ingredientItemRepository.findById(id);
        if(optionalIngredientItem.isEmpty())
            throw new Exception("Ingredient not found");
        IngredientItem ingredientItem=optionalIngredientItem.get();
        ingredientItem.setInStock(!ingredientItem.isInStock());
        return ingredientItemRepository.save(ingredientItem);
    }
}
