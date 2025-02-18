package com.rtogether.api.service;

import com.rtogether.api.entity.Application;
import com.rtogether.api.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }
}
