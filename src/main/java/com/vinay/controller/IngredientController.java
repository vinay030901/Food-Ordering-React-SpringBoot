package com.vinay.controller;

import java.util.List;

import com.vinay.models.IngredientCategory;
import com.vinay.models.IngredientItem;
import com.vinay.request.IngredientCategoryRequest;
import com.vinay.request.IngredientRequest;
import com.vinay.service.IngredientsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

    @Autowired
    private IngredientCategory ingredientCategory;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req)
            throws Exception {
        IngredientCategory ingredientCategory = ingredientsService.createIngredientCategory(req.getName(),
                req.getRestaurantId());
        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);

    }

    @PostMapping()
    public ResponseEntity<IngredientItem> createIngredientItem(@RequestBody IngredientRequest req) throws Exception {
        IngredientItem ingredientItem = ingredientsService.createIngredientItem(req.getRestaurantId(), req.getName(),
                req.getCategoryId());
        return new ResponseEntity<>(ingredientItem, HttpStatus.CREATED);

    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientItem> updateIngredientStock(@PathVariable Long id) throws Exception {
        IngredientItem ingredientItem = ingredientsService.updateStock(id);
        return new ResponseEntity<>(ingredientItem, HttpStatus.OK);

    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientItem>> getRestaurantIngredient(@PathVariable Long id) throws Exception {
        List<IngredientItem> items=ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {
        List<IngredientCategory> items=ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }
}
