package com.rtogether.api.config;

import com.rtogether.api.entity.User;

public class TestUserFactory {

    public static User createSampleUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setName("Test User");
        user.setPhone("+123456789012345");
        user.setPassword_hash("hashedpassword");
        user.setBio("This is a bio.");
        user.setProfile_image_url("http://example.com/profile.jpg");
        user.setJob("Software Engineer");
        user.setInterest(1);
        return user;
    }

    public static User createUserWithCustomEmail(String email) {
        User user = createSampleUser();
        user.setEmail(email);
        return user;
    }
}