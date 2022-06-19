package com.mentorproject.Controller;


import com.mentorproject.Dao.TeacherRep;
import com.mentorproject.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRep teacherRep;

    /**
     *查询所有导师
     * @return
     **/
    @RequestMapping(value = "/getall",method = RequestMethod.GET)
    public ModelAndView getStudentList(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("teacherList", teacherRep.findAll());
        mav.setViewName("teachershow");
        return mav;
    }

    /**
     * 添加一个导师
     * @param teacherName
     * @param gender
     * @param description
     * @param password
     **/
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView addTeacher(@RequestParam("teacherName") String teacherName,
                             @RequestParam("gender") Integer gender,
                             @RequestParam("description") String description,
                             @RequestParam("password") String password){
        Teacher teacher = new Teacher();
        teacher.setTeacherName(teacherName);
        teacher.setGender(gender);
        teacher.setDescription(description);
        teacher.setPassword(password);
        teacherRep.save(teacher);
        ModelAndView mav = new ModelAndView("redirect:/teacher/getall");
        return mav;
    }
}
