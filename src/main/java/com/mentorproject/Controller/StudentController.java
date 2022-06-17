package com.mentorproject.Controller;


import com.mentorproject.Dao.StudentRep;
import com.mentorproject.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRep studentRep;

    /**
     *查询所有学生
     * @return
     **/
    @RequestMapping(value = "/student/getall",method = RequestMethod.GET)
    public List<Student> getStudentList(){
        return studentRep.findAll();
    }

    /**
    * 添加一个学生
     * @param studentId
     * @param studentName
     * @param gender
     * @param gpa
     * @param password
    **/
    @RequestMapping(value = "/student/add",method = RequestMethod.GET)
    public Student addStudent(@RequestParam("studentId") Integer studentId,
                              @RequestParam("stduentName") String studentName,
                              @RequestParam("gender") Integer gender,
                              @RequestParam("gpa") Double gpa,
                              @RequestParam("password") String password){
        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentName(studentName);
        student.setGender(gender);
        student.setGpa(gpa);
        student.setPassword(password);
        return studentRep.save(student);
    }
}
