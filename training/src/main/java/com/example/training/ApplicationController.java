package com.example.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;              //reference to Service
    @Autowired
    //private MongoTemplate mongoTemplate;

    @GetMapping("/applications")                 //to get all applications in the collection
    public ResponseEntity<List<Application>> getAllApplications() {
        return new ResponseEntity<List<Application>>(applicationService.getAllApplications(), HttpStatus.OK);
    }

    @PostMapping("/applications/{email}")                        //to add or create a new application
    public ResponseEntity<?> createApplication(@PathVariable String email, @RequestBody Application application) {
        Optional<Application> existingApplication = applicationService.findByEmail(email);
        if(existingApplication.isPresent() && existingApplication.get().getCourse().equals(application.getCourse())) {
            return new ResponseEntity<String>("Already submitted an application for this course!", HttpStatus.BAD_REQUEST);
        }
        Application savedApplication = applicationService.saveApplication(application);

        return ResponseEntity.ok(savedApplication);
    }

    @PutMapping("/applications/{email}")                             //to update one application details
    public ResponseEntity<Application> updateApplication(@PathVariable String email, @RequestBody Application application) {
        Optional<Application> existingApplication = applicationService.findByEmail(email);
        if(existingApplication.isPresent()) {
            Application updatedApplication = existingApplication.get();
            updatedApplication.setTraineeName(application.getTraineeName());
            updatedApplication.setModule(application.getModule());
            return new ResponseEntity<>(applicationService.saveApplication(updatedApplication), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    @DeleteMapping("/applications/{email}/{course}")                          //to delete one application
    public ResponseEntity<?> deleteApplication(@PathVariable("email") String email, @PathVariable String course) {
        Optional<Application> application = applicationService.findByEmail(email);
        if(application.isPresent() && application.get().getCourse().equals(course)) {
            applicationService.deleteApplication(application.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
