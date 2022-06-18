package com.mentorproject.Controller;


import com.mentorproject.Dao.StudentRep;
import com.mentorproject.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.ResultSet;
import java.util.List;

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
//    public ModelAndView getStudentList(){
//        List<Student> studentList= studentRep.findAll();
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("studentList", studentList);
//        mv.setViewName("studentshow.html");
//        return mv;
//    }

    /**
    * 添加一个学生
     * @param studentId
     * @param studentName
     * @param gender
     * @param gpa
     * @param password
    **/
    @RequestMapping(value = "/add",method = RequestMethod.GET)
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
