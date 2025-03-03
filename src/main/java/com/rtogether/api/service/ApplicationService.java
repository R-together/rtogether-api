package com.rtogether.api.service;

import com.rtogether.api.dto.ApplicationDTO;
import com.rtogether.api.entity.Application;
import com.rtogether.api.entity.User;
import com.rtogether.api.mapper.ApplicationMapper;
import com.rtogether.api.repository.ApplicationRepository;
import com.rtogether.api.repository.UserRepository;
import com.rtogether.api.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ApplicationService extends ServiceUtils {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationMapper applicationMapper;

    public Application createApplication(ApplicationDTO applicationDTO) {
        User mentee = userRepository.findById(applicationDTO.getMentee_id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Application application = applicationMapper.toEntity(applicationDTO, mentee);
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationsByApplicationId(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    public List<Application> getApplicationsByMenteeId(Long menteeId) {
        User mentee = userRepository.findById(menteeId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return applicationRepository.findByMentee(mentee);
    }

    public Application updateApplication(Long id, ApplicationDTO applicationDTO) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        copyNonNullProperties(applicationDTO, application);
        application.setUpdated_at(Timestamp.from(Instant.now()));
        return applicationRepository.save(application);
    }

    public void deleteApplication(Long id){
        applicationRepository.deleteById(id);
    }
}
