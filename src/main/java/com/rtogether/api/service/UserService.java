package com.rtogether.api.service;
import com.rtogether.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rtogether.api.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String getHelloWorldText(){
        return "Hello world from service";
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());
        user.setPhone(userDetails.getPhone());
        user.setPassword_hash(userDetails.getPassword_hash());
        user.setBio(userDetails.getBio());
        user.setProfile_image_url(userDetails.getProfile_image_url());
        user.setJob(userDetails.getJob());
        user.setInterest(userDetails.getInterest());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
