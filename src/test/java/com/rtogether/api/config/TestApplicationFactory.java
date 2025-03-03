package com.rtogether.api.config;

import com.rtogether.api.dto.ApplicationDTO;
import com.rtogether.api.entity.Application;

public class TestApplicationFactory {

    public static ApplicationDTO createSampleApplicationDTO() {
        ApplicationDTO application = new ApplicationDTO();
        application.setSlot_id(123);
        application.setSession_id(123);
        application.setMentee_id(1L);
        application.setApplication_reason("Test reason");
        return application;
    }

    public static Application createSampleApplication() {
        Application application = new Application();
        application.setSlot_id(123);
        application.setSession_id(123);
        application.setMentee(TestUserFactory.createSampleUser());
        application.setApplication_reason("Test reason");
        return application;
    }

    public static ApplicationDTO createApplicationWithCustomApplicationReason(String application_reason) {
        ApplicationDTO application = createSampleApplicationDTO();
        application.setApplication_reason(application_reason);
        return application;
    }
}
