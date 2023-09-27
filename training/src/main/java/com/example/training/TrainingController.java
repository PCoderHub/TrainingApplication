package com.example.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainingController {
    @Autowired
    private CourseService courseService;
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllTrainings() {
        return new ResponseEntity<List<Course>>(courseService.allCourses(), HttpStatus.OK);
    }
}
