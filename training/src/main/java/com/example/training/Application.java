package com.example.training;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "applications")     //collection from MongoDB database
@Data
@AllArgsConstructor   // -
@NoArgsConstructor    // - Auto generates getters and setters
public class Application {
    @Id
    private ObjectId id;
    private String traineeName;
    private String email;
    private String course;
    private String module;
    private LocalDate dateOfApplication;

    public Application(String traineeName, String email, String course, String module) {
        this.traineeName = traineeName;
        this.email = email;
        this.course = course;
        this.module = module;
    }
}
