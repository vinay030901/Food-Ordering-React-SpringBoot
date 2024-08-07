package com.vinay.repository;

import com.vinay.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByRestaurantId(Long restaurantId);

    @Query("Select f from Food f WHERE lower(f.name) LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);
}
