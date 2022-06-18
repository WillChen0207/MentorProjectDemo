package com.mentorproject.Controller;


import com.mentorproject.Dao.StudentRep;
import com.mentorproject.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRep studentRep;

    /**
     *查询所有学生
     * @return
     **/
    @RequestMapping(value = "/getall",method = RequestMethod.GET)
    public String getStudentList(Model model){
        model.addAttribute("studentList", studentRep.findAll());
        return "studentshow";
    }

    /**
    * 添加一个学生
     * @param studentName
     * @param gender
     * @param gpa
     * @param password
    **/
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addStudent(@RequestParam("studentName") String studentName,
                              @RequestParam("gender") Integer gender,
                              @RequestParam("gpa") Double gpa,
                              @RequestParam("password") String password,
                              Model model){
        Student student = new Student();
        student.setStudentName(studentName);
        student.setGender(gender);
        student.setGpa(gpa);
        student.setPassword(password);
        studentRep.save(student);
        model.addAttribute("studentList", studentRep.findAll());
        return "studentshow";
    }
}
