package com.example.training;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ApplicationController.class)
public class TestUpdateApplication {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    @Test
    public void updateApplicationTest() throws Exception {
        // mock application
        Application application = new Application("Pooja Bala", "pooja@example.com", "IT Skills", "Java");
        Optional<Application> existingApplication = Optional.of(application);

        when(applicationService.findByEmail("pooja@example.com")).thenReturn(existingApplication);

        //updated application
        Application updatedApplication = new Application("Pooja Bala", "pooja@example.com", "IT Skills", "ReactJS");

        when(applicationService.saveApplication(any(Application.class))).thenReturn(updatedApplication);

        // PUT request to update the application
        mockMvc.perform(MockMvcRequestBuilders.put("/applications/pooja@example.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"traineeName\":\"Pooja Bala\",\"email\":\"pooja@example.com\",\"course\":\"IT Skills\",\"module\":\"ReactJS\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.module").value("ReactJS"));
    }
}
