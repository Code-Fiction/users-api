package com.usersapi.endpoints.detail;

import com.usersapi.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users/{id}")
public class DetailUserController {

    @Autowired
    DetailUserService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<User>> list(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.listUser(id));
    }
}
