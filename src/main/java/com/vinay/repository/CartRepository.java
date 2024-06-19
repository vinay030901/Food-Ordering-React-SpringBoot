package com.vinay.repository;

import com.vinay.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    public Cart (Long id) throws Exception;
}
