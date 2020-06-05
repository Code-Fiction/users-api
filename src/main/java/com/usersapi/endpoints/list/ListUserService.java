package com.usersapi.endpoints.list;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUserService {

    @Autowired
    UserRepository repository;

    public List<User> listAllUsers() {
        return repository.findAll();
    }
}
