package com.mentorproject.Bean;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mentormacth")
@Component
public class MentormatchBean {
    private String student_id;

    private String teacher_id;
}
