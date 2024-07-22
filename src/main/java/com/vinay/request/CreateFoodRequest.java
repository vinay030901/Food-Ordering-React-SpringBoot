package com.vinay.request;

import com.vinay.models.Category;
import com.vinay.models.IngredientItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private String price;

    private Category category;
    private List<String>images;
    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;
    private List<IngredientItem>ingredients;
}
