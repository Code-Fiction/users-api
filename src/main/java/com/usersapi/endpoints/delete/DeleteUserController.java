package com.usersapi.endpoints.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{id}")
public class DeleteUserController {

    @Autowired
    DeleteUserService service;

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser_whenDeleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}