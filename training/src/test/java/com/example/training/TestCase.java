package com.example.training;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class TestCase {
    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private ApplicationController applicationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllApplications() {
        Application application1 = new Application();
        Application application2 = new Application();
        List<Application> applications = Arrays.asList(application1, application2);

        when(applicationService.getAllApplications()).thenReturn(applications);
        ResponseEntity<List<Application>> response = applicationController.getAllApplications();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(applications, response.getBody());
    }
}
