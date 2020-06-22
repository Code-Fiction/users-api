package com.usersapi.endpoints.unit.service;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import com.usersapi.endpoints.delete.DeleteUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserService deleteUserService;

    @Test
    public void whenGivenId_shouldDeleteUser_ifFound(){
        User user = new User();
        user.setName("Test Name");
        user.setId(1L);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        deleteUserService.deleteUser(user.getId());
        verify(userRepository).deleteById(user.getId());
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_user_doesnt_exist() {
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        deleteUserService.deleteUser(user.getId());
    }
}