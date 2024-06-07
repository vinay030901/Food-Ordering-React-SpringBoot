package com.vinay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinay.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String username);

}
