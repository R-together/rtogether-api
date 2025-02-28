package com.rtogether.api.controller;

import com.rtogether.api.dto.ApplicationDTO;
import com.rtogether.api.entity.Application;
import com.rtogether.api.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody ApplicationDTO application) {
        Application savedApplication = applicationService.createApplication(application);
        return ResponseEntity.ok(savedApplication);
    }

    @GetMapping
    public  ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @GetMapping("/{application_id}")
    public  ResponseEntity<Application> getApplicationsByApplicationId(@PathVariable Long application_id){
        Application application = applicationService.getApplicationsByApplicationId(application_id);
        return ResponseEntity.ok(application);
    }

    @PutMapping("/{application_id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long application_id, @RequestBody ApplicationDTO applicationDTO){
        Application updatedApplication = applicationService.updateApplication(
                application_id, applicationDTO
        );
        return ResponseEntity.ok(updatedApplication);
    }

    @DeleteMapping("/{application_id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long application_id){
        applicationService.deleteApplication(application_id);
        return ResponseEntity.noContent().build();
    }
}
