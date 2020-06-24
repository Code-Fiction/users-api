package com.usersapi.endpoints.update;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import com.usersapi.endpoints.detail.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserService {

    @Autowired
    UserRepository repository;

    public User updateUser(Long id, User user) {

        repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setId(id);
        return repository.save(user);
    }
}