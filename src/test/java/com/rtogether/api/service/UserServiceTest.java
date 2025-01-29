
package com.rtogether.api.service;

import com.rtogether.api.config.TestUserFactory;
import com.rtogether.api.entity.User;
import com.rtogether.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private static final String EXPECTED_HELLO_WORLD_TEXT = "Hello world from service";

    @Test
    public void testHelloWorld() {
        UserService userService = new UserService();
        assertEquals(EXPECTED_HELLO_WORLD_TEXT, userService.getHelloWorldText());
    }

    @Test
    public void testCreateUser() {
        User user = TestUserFactory.createSampleUser();
        when(userRepository.save(user)).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertEquals(user.getEmail(), createdUser.getEmail());
    }

    @Test
    public void testGetUserById() {
        User user = TestUserFactory.createSampleUser();
        user.setUser_id(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User foundUser = userService.getUserById(1L).orElse(null);
        assertEquals(user.getEmail(), foundUser.getEmail());
    }
}