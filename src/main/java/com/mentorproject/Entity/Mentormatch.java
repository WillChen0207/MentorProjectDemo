package com.mentorproject.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mentormatch {
    @Id
    private String student_id;

    private String teacher_id;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }
}
