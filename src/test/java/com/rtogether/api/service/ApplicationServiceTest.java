package com.rtogether.api.service;

import com.rtogether.api.config.TestApplicationFactory;
import com.rtogether.api.config.TestUserFactory;
import com.rtogether.api.dto.ApplicationDTO;
import com.rtogether.api.entity.Application;
import com.rtogether.api.entity.User;
import com.rtogether.api.mapper.ApplicationMapper;
import com.rtogether.api.repository.ApplicationRepository;
import com.rtogether.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ApplicationServiceTest {
    private final String UPDATED_APPLICATION_REASON = "Updated reason";

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApplicationMapper applicationMapper;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateApplication() {
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setMentee_id(1L);
        User mockUser = TestUserFactory.createSampleUser();

        Application mockApplication = new Application();
        mockApplication.setApplication_reason("Test reason");

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(mockUser));
        when(applicationMapper.toEntity(any(ApplicationDTO.class), any(User.class))).thenReturn(mockApplication);
        when(applicationRepository.save(any(Application.class))).thenReturn(mockApplication);

        Application createdApplication = applicationService.createApplication(applicationDTO);

        assertEquals("Test reason", createdApplication.getApplication_reason());
    }

    @Test
    public void testGetApplicationsByApplicationId() {
        Application application = TestApplicationFactory.createSampleApplication();
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        Application foundApplication = applicationService.getApplicationsByApplicationId(1L);
        assertEquals(application.getApplication_reason(), foundApplication.getApplication_reason());
    }

    @Test
    public void testGetApplicationsByApplicationId_NotFound() {
        Long applicationId = 1L;
        when(applicationRepository.findById(applicationId)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            applicationService.getApplicationsByApplicationId(applicationId);
        });
        assertEquals("Application not found", exception.getMessage());
    }

    @Test
    public void testGetAllApplications() {
        Application application = TestApplicationFactory.createSampleApplication();
        when(applicationRepository.findAll()).thenReturn(List.of(application));
        List<Application> applications = applicationService.getAllApplications();
        assertEquals(1, applications.size());
        assertEquals(application, applications.get(0));
    }

    @Test
    public void testUpdateApplication() {
        Application application = TestApplicationFactory.createSampleApplication();
        application.setApplication_id(1L);
        ApplicationDTO updatedDetails = new ApplicationDTO();
        updatedDetails.setApplication_reason(UPDATED_APPLICATION_REASON);
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationRepository.save(application)).thenReturn(application);
        Application updatedApplication = applicationService.updateApplication(1L, updatedDetails);
        assertEquals(UPDATED_APPLICATION_REASON, updatedApplication.getApplication_reason());
    }

    @Test
    public void testDeleteApplication() {
        Application application = TestApplicationFactory.createSampleApplication();
        application.setApplication_id(1L);
        doNothing().when(userRepository).deleteById(1L);
        applicationService.deleteApplication(1L);
        verify(applicationRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetApplicationsByMenteeId() {
        User mockUser = TestUserFactory.createSampleUser();

        Application application = TestApplicationFactory.createSampleApplication();

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(mockUser));
        when(applicationRepository.findByMentee(mockUser)).thenReturn(List.of(application));

        List<Application> applications= applicationService.getApplicationsByMenteeId(1L);

        assertEquals(1, applications.size());
        assertEquals(application, applications.get(0));
    }
}