package com.usersapi.endpoints.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usersapi.domain.user.User;
import com.usersapi.endpoints.detail.UserNotFoundException;
import com.usersapi.endpoints.update.UpdateUserController;
import com.usersapi.endpoints.update.UpdateUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UpdateUserController.class)
public class UpdateUserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UpdateUserService updateUserService;

    @Test
    public void updateUser_whenPutUser() throws Exception {

        User user = new User();
        user.setName("Test Name");
        user.setId(89L);
        given(updateUserService.updateUser(user.getId(), user)).willReturn(user);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(put("/users/" + user.getId().toString())
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(user.getName())));
    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist() throws Exception {
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        Mockito.doThrow(new UserNotFoundException(user.getId())).when(updateUserService).updateUser(user.getId(), user);

        mvc.perform(put("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
