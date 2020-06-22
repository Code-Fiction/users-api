package com.usersapi.endpoints.unit.service;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import com.usersapi.endpoints.list.ListUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ListUserService listUserService;

    @Test
    public void shouldReturnAllUsers() {
        List<User> users = new ArrayList();
        users.add(new User());

        given(userRepository.findAll()).willReturn(users);

        List<User> expected = listUserService.listAllUsers();

        assertEquals(expected, users);
        verify(userRepository).findAll();
    }
}
