package com.example.training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class TestCreateApplication {
    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private ApplicationController applicationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createApplicationTest() {
        // Mock data
        String email = "abc.xyz@example.com";
        Application application = new Application();
        application.setCourse("IT Skills");
        application.setModule("Java");
        application.setTraineeName("Abc Xyz");

        // Mock ApplicationService
        when(applicationService.findByEmail(eq(email))).thenReturn(Optional.empty());
        when(applicationService.saveApplication(eq(application))).thenReturn(application);

        // Call the controller method
        ResponseEntity<?> response = applicationController.createApplication(email, application);

        // Verify the response
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == application;
    }

    @Test
    void createApplicationAlreadyExistsTest() {
        String email = "abc.xyz@example.com";
        Application application = new Application();
        application.setCourse("IT Skills");
        application.setTraineeName("Abc Xyz");

        // Mock ApplicationService
        Application existingApplication = new Application();
        existingApplication.setCourse("IT Skills");
        existingApplication.setEmail(email);
        when(applicationService.findByEmail(eq(email))).thenReturn(Optional.of(existingApplication));

        // Call the controller method
        ResponseEntity<?> response = applicationController.createApplication(email, application);

        // Verify the response
        assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
        assert response.getBody().equals("Already submitted an application for this course!");
    }
}
