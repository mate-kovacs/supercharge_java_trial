package com.supercharge.banking.service;

import com.supercharge.banking.Repository.UserRepository;
import com.supercharge.banking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(User user) {
        try {
            userRepository.save(user);
        } catch (DataAccessException exception) {
            return false;
        }
        return true;
    }
}
