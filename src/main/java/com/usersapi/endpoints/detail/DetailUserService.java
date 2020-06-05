package com.usersapi.endpoints.detail;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailUserService {

    @Autowired
    UserRepository repository;

    public Optional<User> listUser(Long id) {

        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException(id);
        } else {
            return repository.findById(id);
        }
    }
}