package com.usersapi.endpoints.delete;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{id}")
@Api(tags = "Delete an existing user with the DELETE method")
public class DeleteUserController {

    @Autowired
    DeleteUserService service;

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Execute DELETE method")
    public void deleteUser_whenDeleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}