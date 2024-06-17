package com.vinay.service;

import com.vinay.models.User;
import org.springframework.stereotype.Service;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
