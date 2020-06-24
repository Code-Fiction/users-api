package com.usersapi.endpoints.unit.service;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import com.usersapi.endpoints.update.UpdateUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserService updateUserService;

    @Test
    public void whenGivenId_shouldUpdateUser_ifFound() {
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        User newUser = new User();
        user.setName("New Test Name");

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        updateUserService.updateUser(user.getId(), newUser);

        verify(userRepository).save(newUser);
        verify(userRepository).findById(user.getId());
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_user_doesnt_exist() {
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        User newUser = new User();
        newUser.setId(90L);
        user.setName("New Test Name");

        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        updateUserService.updateUser(user.getId(), newUser);
    }
}