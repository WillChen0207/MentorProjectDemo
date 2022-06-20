package com.mentorproject.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApplicationRecord {
    @Id
    private String studentId;
    private String first_app;
    private String second_app;
    private String third_app;
    private Integer is_selected;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirst_app() {
        return first_app;
    }

    public void setFirst_app(String first_app) {
        this.first_app = first_app;
    }

    public String getSecond_app() {
        return second_app;
    }

    public void setSecond_app(String second_app) {
        this.second_app = second_app;
    }

    public String getThird_app() {
        return third_app;
    }

    public void setThird_app(String third_app) {
        this.third_app = third_app;
    }

    public Integer getIs_selected() {
        return is_selected;
    }

    public void setIs_selected(Integer is_selected) {
        this.is_selected = is_selected;
    }
}
