package com.example.ohlccryptoapi.domain.service.security;



import com.example.ohlccryptoapi.domain.model.security.User;
import com.example.ohlccryptoapi.domain.model.security.UserDto;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> findAll();
    User findOne(String username);
}
