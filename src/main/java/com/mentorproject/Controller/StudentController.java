package com.mentorproject.Controller;


import com.mentorproject.Dao.StudentRep;
import com.mentorproject.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @RequestMapping(value = "/getall",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getStudentList(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("studentList", studentRep.findAll());
        mav.setViewName("studentshow");
        return mav;
    }

    /**
    * 添加一个学生
     * @param studentId
     * @param studentName
     * @param gender
     * @param gpa
     * @param password
    **/
    @RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addStudent(@RequestParam("studentId") String studentId,
                                   @RequestParam("studentName") String studentName,
                                   @RequestParam("gender") Integer gender,
                                   @RequestParam("gpa") Double gpa,
                                   @RequestParam("password") String password){
        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentName(studentName);
        student.setGender(gender);
        student.setGpa(gpa);
        student.setPassword(password);
        studentRep.save(student);
        ModelAndView mav = new ModelAndView("redirect:/student/getall");
        return mav;
    }

    /**
     * 登录校验
     * @param student_id
     * @param password
     **/
    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView logCheck(@RequestParam("student_id") String student_id,
                                 @RequestParam("password") String password){
        List<Student> studentList = studentRep.logCheck(student_id,password);
        ModelAndView mav = new ModelAndView();
        if (studentList.isEmpty()) {
            mav.setViewName("errorpage");
        }else {
            mav.addObject("studentList",studentList);
            mav.setViewName("studentshow");
        }
        return mav;
    }

    /**
     * 登记志愿信息
     * @param student_id
     * @param FirstApp
     * @param SecondApp
     * @param ThirdApp
     * @param IsSelected
     **/
    @RequestMapping(value = "/fillapp",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView fillApp(@RequestParam("student_id") String student_id,
                                @RequestParam("FirstApp") Integer FirstApp,
                                @RequestParam("SecondApp") Integer SecondApp,
                                @RequestParam("ThirdApp") Integer ThirdApp,
                                @RequestParam("IsSelected") Integer IsSelected){
        studentRep.fillApplication(student_id, FirstApp, SecondApp, ThirdApp, IsSelected);
        ModelAndView mav = new ModelAndView("redirect:/application/getall");
        return mav;
    }

    /**
     * 查看当前学生志愿信息
     * @param student_id
     **/
    @RequestMapping(value = "/getapp",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getAppRecByStudentId(@RequestParam("student_id") String student_id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("student_id", student_id);
        mav.setViewName("redirect:/application/getStudent");
        return mav;
    }
}
