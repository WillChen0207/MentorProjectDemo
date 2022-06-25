package com.mentorproject.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "message")
@Component
public class MessageBean {

    private Integer rec_id;

    private String sender;

    private String receiver;

    private String message;

    private  Integer isRead;

}
