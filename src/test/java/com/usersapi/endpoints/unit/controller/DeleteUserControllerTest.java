package com.usersapi.endpoints.unit.controller;

import com.usersapi.domain.user.User;
import com.usersapi.endpoints.delete.DeleteUserController;
import com.usersapi.endpoints.delete.DeleteUserService;
import com.usersapi.endpoints.detail.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DeleteUserController.class)
public class DeleteUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DeleteUserService deleteUserService;

    @Test
    public void removeUserById_whenDeleteMethod() throws Exception {
        User user = new User();
        user.setName("Test Name");
        user.setId(89L);

        doNothing().when(deleteUserService).deleteUser(user.getId());

        mvc.perform(delete("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist() throws Exception {
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        Mockito.doThrow(new UserNotFoundException(user.getId())).when(deleteUserService).deleteUser(user.getId());

        mvc.perform(delete("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
