package com.mentorproject.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FirstApplication {
    @Id
    private String student_id;

    private String first_app;

    private Integer is_selected;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getFirst_app() {
        return first_app;
    }

    public void setFirst_app(String first_app) {
        this.first_app = first_app;
    }

    public Integer getIs_selected() {
        return is_selected;
    }

    public void setIs_selected(Integer is_selected) {
        this.is_selected = is_selected;
    }
}
