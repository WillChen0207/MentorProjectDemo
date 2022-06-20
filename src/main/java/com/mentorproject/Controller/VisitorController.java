package com.mentorproject.Controller;

import com.mentorproject.Dao.TeacherRep;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/visitor")
//显示所有的导师信息
public class VisitorController {
    private TeacherRep teacherRep;
    @RequestMapping(value = "/getall",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getTeacherInfo(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/teacher/getall");
        return mav;
    }
}
