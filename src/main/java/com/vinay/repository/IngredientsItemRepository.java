package com.vinay.repository;

import com.vinay.models.IngredientItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsItemRepository extends JpaRepository<IngredientItem,Long> {

    List<IngredientItem> findByRestaurantId(Long restaurantId);
}
