package com.mentorproject.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "teacher")
@Component
public class TeacherBean {

    private String teacherId;

    private String teacherName;

    private Integer gender;

    private String teacherDescription;

    private String password;

}
