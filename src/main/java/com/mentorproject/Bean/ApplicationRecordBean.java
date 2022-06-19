package com.mentorproject.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "apprec")
@Component
public class ApplicationRecordBean {

    private String studentId;
    private String FirstApp;
    private String SecondApp;
    private String ThirdApp;
    private Integer IsSelected;
}
