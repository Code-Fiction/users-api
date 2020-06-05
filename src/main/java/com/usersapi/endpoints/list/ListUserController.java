package com.usersapi.endpoints.list;

import com.usersapi.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ListUserController {
    @Autowired
    ListUserService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> listAllUsers_whenGetUsers() {
        return ResponseEntity.ok().body(service.listAllUsers());
    }
}
