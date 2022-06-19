package com.mentorproject.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApplicationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String studentId;
    private String firstApp;
    private String secondApp;
    private String thirdApp;
    private Integer isSelected;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstApp() {
        return firstApp;
    }

    public void setFirstApp(String firstApp) {
        this.firstApp = firstApp;
    }

    public String getSecondApp() {
        return secondApp;
    }

    public void setSecondApp(String secondApp) {
        this.secondApp = secondApp;
    }

    public String getThirdApp() {
        return thirdApp;
    }

    public void setThirdApp(String thirdApp) {
        this.thirdApp = thirdApp;
    }

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }
}
