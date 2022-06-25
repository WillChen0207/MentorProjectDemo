package com.mentorproject.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "result")
@Component
public class ResultBean {

    private String student_name;

    private String teacher_name;
}
