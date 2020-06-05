package com.usersapi.endpoints.update;

import com.usersapi.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{id}")
public class UpdateUserController {

    @Autowired
    UpdateUserService service;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser_whenPutUser(@RequestBody User user, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.updateUser(id, user));
    }
}
