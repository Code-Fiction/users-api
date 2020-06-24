package com.usersapi.endpoints.unit.controller;

import com.usersapi.domain.user.User;
import com.usersapi.endpoints.detail.DetailUserController;
import com.usersapi.endpoints.detail.DetailUserService;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DetailUserController.class)
public class DetailUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DetailUserService detailUserService;

    @Test
    public void listUserById_whenGetMethod() throws Exception {

        User user = new User();
        user.setName("Test Name");
        user.setId(89L);

        given(detailUserService.listUser(user.getId())).willReturn(user);

        mvc.perform(get("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(user.getName())));
    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist() throws Exception {
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        Mockito.doThrow(new UserNotFoundException(user.getId())).when(detailUserService).listUser(user.getId());

        mvc.perform(get("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}