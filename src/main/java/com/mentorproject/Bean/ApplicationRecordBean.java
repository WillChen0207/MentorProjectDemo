package com.mentorproject.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "apprec")
@Component
public class ApplicationRecordBean {

    private String studentId;

    private String firstApp;

    private String secondApp;

    private String thirdApp;

    private Integer isSelected;

}
