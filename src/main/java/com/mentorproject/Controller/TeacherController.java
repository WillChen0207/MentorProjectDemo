package com.mentorproject.Controller;


import com.mentorproject.Dao.TeacherRep;
import com.mentorproject.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRep teacherRep;

    /**
     *查询所有导师
     * @return
     **/
    @RequestMapping(value = "/getall",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getStudentList(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("teacherList", teacherRep.findAll());
        mav.setViewName("teachershow");
        return mav;
    }

    /**
     * 添加一个导师
     * @param teacherId
     * @param teacherName
     * @param gender
     * @param description
     * @param password
     **/
    @RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addTeacher(@RequestParam("teacherId") String teacherId,
                                   @RequestParam("teacherName") String teacherName,
                                   @RequestParam("gender") Integer gender,
                                   @RequestParam("description") String description,
                                   @RequestParam("password") String password){
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(teacherName);
        teacher.setGender(gender);
        teacher.setDescription(description);
        teacher.setPassword(password);
        teacherRep.save(teacher);
        ModelAndView mav = new ModelAndView("redirect:/teacher/getall");
        return mav;
    }

    /**
     * 登录校验
     * @param teacher_id
     * @param password
     **/
    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView logCheck(@RequestParam("teacher_id") String teacher_id,
                                 @RequestParam("password") String password){
        List<Teacher> teacherList = teacherRep.logCheck(teacher_id,password);
        ModelAndView mav = new ModelAndView();
        if (teacherList.isEmpty()) {
            mav.setViewName("errorpage");
        }else {
            mav.addObject("teacherList",teacherList);
            mav.setViewName("teachershow");
        }
        return mav;
    }
}
