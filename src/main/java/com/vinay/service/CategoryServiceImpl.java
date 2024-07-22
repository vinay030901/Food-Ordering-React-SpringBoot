package com.vinay.service;

import com.vinay.models.Category;
import com.vinay.models.Restaurant;
import com.vinay.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantByUserId(userId);
        Category category=new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(id);
        return categoryRepository.findByRestaurantId(id);
    }

    @Override
    public Category findCategoryById(Long categoryId) throws Exception {
        Optional<Category> category=categoryRepository.findById(categoryId);
        if(category.isEmpty())
            throw new Exception("Category not found");
        return category.get();
    }
}
