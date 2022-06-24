package com.mentorproject.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FirstApplication {
    @Id
    private String student_id;

    private String firstapp;

    private Integer is_selected;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getFirstapp() {
        return firstapp;
    }

    public void setFirstapp(String firstapp) {
        this.firstapp = firstapp;
    }

    public Integer getIs_selected() {
        return is_selected;
    }

    public void setIs_selected(Integer is_selected) {
        this.is_selected = is_selected;
    }
}
