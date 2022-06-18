package com.mentorproject.Controller;


import com.mentorproject.Dao.TeacherRep;
import com.mentorproject.Entity.Student;
import com.mentorproject.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getStudentList(Model model){
        model.addAttribute("teacherList", teacherRep.findAll());
        return "teachershow";
    }

    /**
     * 添加一个导师
     * @param teacherName
     * @param gender
     * @param description
     * @param password
     **/
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addTeacher(@RequestParam("teacherName") String teacherName,
                             @RequestParam("gender") Integer gender,
                             @RequestParam("description") String description,
                             @RequestParam("password") String password,
                             Model model){
        Teacher teacher = new Teacher();
        teacher.setTeacherName(teacherName);
        teacher.setGender(gender);
        teacher.setDescription(description);
        teacher.setPassword(password);
        teacherRep.save(teacher);
        model.addAttribute("teacherList", teacherRep.findAll());
        return "teachershow";
    }
}
