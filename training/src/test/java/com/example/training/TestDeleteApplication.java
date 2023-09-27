package com.example.training;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestDeleteApplication {
    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private ApplicationController applicationController;

    @Test
    public void testDeleteApplication() {
        // mock application
        String email = "pooja@example.com";
        String course = "IT Skills";
        Application application = new Application();
        application.setId(new ObjectId());
        application.setEmail(email);
        application.setCourse(course);

        when(applicationService.findByEmail(email)).thenReturn(Optional.of(application));

        ResponseEntity<?> response = applicationController.deleteApplication(email, course);


        assertDoesNotThrow(() -> applicationService.deleteApplication(application.getId()));

        // Verify that the response has an HTTP OK status
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
