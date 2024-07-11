package com.vinay.repository;

import com.vinay.models.Cart;
import com.vinay.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

//    public Cart findByCustomerId(Long userId);
}
