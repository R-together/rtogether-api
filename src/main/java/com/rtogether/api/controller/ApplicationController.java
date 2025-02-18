package com.rtogether.api.controller;

import com.rtogether.api.entity.Application;
import com.rtogether.api.entity.User;
import com.rtogether.api.service.ApplicationService;
import com.rtogether.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Application> createUser(@RequestBody Application application) {
        Application savedApplication = applicationService.createApplication(application);
        return ResponseEntity.ok(savedApplication);
    }
}
