package com.rtogether.api.service;

import com.rtogether.api.config.TestUserFactory;
import com.rtogether.api.entity.User;
import com.rtogether.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = TestUserFactory.createSampleUser();
        when(userRepository.save(user)).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertEquals(user.getEmail(), createdUser.getEmail());
    }

    @Test
    public void testGetAllUsers() {
        User user = TestUserFactory.createSampleUser();
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> users = userService.getAllUsers();
        assertEquals(1, users.size());
        assertEquals(user.getEmail(), users.get(0).getEmail());
    }

    @Test
    public void testGetUserById() {
        User user = TestUserFactory.createSampleUser();
        user.setUser_id(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User foundUser = userService.getUserById(1L).orElse(null);
        assertEquals(user.getEmail(), foundUser.getEmail());
    }

    @Test
    public void testUpdateUser() {
        User user = TestUserFactory.createSampleUser();
        user.setUser_id(1L);
        User updatedDetails = TestUserFactory.createUserWithCustomEmail("updated@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        User updatedUser = userService.updateUser(1L, updatedDetails);
        assertEquals("updated@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        User user = TestUserFactory.createSampleUser();
        user.setUser_id(1L);
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}