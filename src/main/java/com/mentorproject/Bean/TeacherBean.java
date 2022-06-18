package com.mentorproject.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "teacher")
@Component
public class TeacherBean {

    private Integer teacherId;

    private String teacherName;

    private Integer gender;

    private String description;

    private String password;

}
