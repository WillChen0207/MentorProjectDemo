package com.mentorproject.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "firstapp")
@Component
public class FirstApplicationBean {
    private String student_id;

    private String firstapp;

    private String is_selected;
}
