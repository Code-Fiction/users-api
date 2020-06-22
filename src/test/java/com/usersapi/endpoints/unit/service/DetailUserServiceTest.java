package com.usersapi.endpoints.unit.service;

import com.usersapi.domain.user.User;
import com.usersapi.domain.user.UserRepository;
import com.usersapi.endpoints.detail.DetailUserService;
import com.usersapi.endpoints.detail.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DetailUserService detailUserService;

    @Test
    public void whenGivenId_shouldReturnUser_ifFound() {
        User user = new User();
        user.setId(89L);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User expected = detailUserService.listUser(user.getId());

        assertThat(expected).isSameAs(user);
        verify(userRepository).findById(user.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void should_throw_exception_when_user_doesnt_exist() {
        User user = new User();
        user.setId(89L);
        user.setName("Test Name");

        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        detailUserService.listUser(user.getId());
    }
}