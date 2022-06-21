package com.mentorproject.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "student")
@Component
public class StudentBean {

    private String studentId;

    private String studentName;

    private Integer gender;

    private Double gpa;

    private String password;

}
