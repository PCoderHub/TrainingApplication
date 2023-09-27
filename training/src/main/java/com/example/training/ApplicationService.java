package com.example.training;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;       //reference to repository

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }  //to get all applications

    public void deleteApplication (ObjectId id){                   // to delete an application by Id
        Optional<Application> application = applicationRepository.findById(id);
            if(application!=null) {
                applicationRepository.delete(application.get());
            }
    }

    public Application saveApplication(Application application) {     //to save an application
        application.setDateOfApplication(LocalDate.now());
        return applicationRepository.save(application);
    }
    public Optional<Application> findByEmail(String email) {       //to find an application by email
        return applicationRepository.findByEmail(email);
    }
    public Optional<Application> findById(ObjectId id) {
        return  applicationRepository.findById(id);
    }  // to find an application by Id

}
