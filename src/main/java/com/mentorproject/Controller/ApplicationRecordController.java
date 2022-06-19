package com.mentorproject.Controller;

import com.mentorproject.Dao.ApplicationRecordRep;
import com.mentorproject.Entity.ApplicationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/application")
public class ApplicationRecordController {

    @Autowired
    private ApplicationRecordRep appRecRep;

    /**
     * 查询所有志愿提交记录信息
     * @return
     **/
    @RequestMapping(value = "/getall", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getAppRecList(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("appRecList", appRecRep.findAll());
        mav.setViewName("applicationshow");
        return mav;
    }

    /**
     * 查询自己填写的志愿信息
     * @param student_id
     **/
    @RequestMapping(value = "/getStudent",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getAppRecByStudentId(@RequestParam("student_id") String student_id){
        List<ApplicationRecord> appRecList = appRecRep.getApplicationRecordByStudentId(student_id);
        ModelAndView mav = new ModelAndView();
        if (appRecList.isEmpty()){
            mav.setViewName("errorpage");
        }else {
            mav.addObject("appRecList", appRecList);
            mav.setViewName("applicationshow");
        }
        return mav;
    }
}
