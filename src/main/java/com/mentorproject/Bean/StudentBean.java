package com.mentorproject.Bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "student")
@Component
public class StudentBean {
//    @Value("${studentId}")
    private Integer studentId;
//    @Value("${studentName}")
    private String studentName;
//    @Value("${studentGender}")
    private Integer gender;
//    @Value("${gpa}")
    private Double gpa;
//    @Value("${studentPassword}")
    private String password;

}
