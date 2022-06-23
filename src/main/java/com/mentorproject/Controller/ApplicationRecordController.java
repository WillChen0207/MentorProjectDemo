package com.mentorproject.Controller;

import com.mentorproject.Dao.ApplicationRecordRep;
import com.mentorproject.Entity.ApplicationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @ResponseBody
    @RequestMapping(value = "/getall", method = {RequestMethod.GET,RequestMethod.POST})
    public List<ApplicationRecord> getAppRecList(){
        return appRecRep.findAll();
    }

    /**
     * 添加一条志愿提交记录
     * @return
     **/
    @ResponseBody
    @RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public ApplicationRecord addAppRec(HttpServletRequest request,
                                       HttpServletResponse response){
        ApplicationRecord appRec = (ApplicationRecord) request.getAttribute("appRec");
        appRecRep.save(appRec);
        return appRec;
    }


    /**
     * 查询自己填写的志愿信息
     **/
    @ResponseBody
    @RequestMapping(value = "/getStudent",method = {RequestMethod.GET,RequestMethod.POST})
    public List<ApplicationRecord> getAppRecByStudentId(HttpServletRequest request,
                                                  HttpServletResponse response){
        String student_id = (String) request.getAttribute("student_id");
        List<ApplicationRecord> appRec = appRecRep.getApplicationRecordByStudentId(student_id);
        request.setAttribute("appRec", appRec);
        return appRec;
    }

    /**
     * 查询第一志愿信息
     **/
    @ResponseBody
    @RequestMapping(value = "/getStudent",method = {RequestMethod.GET,RequestMethod.POST})
    public List<ApplicationRecord> getFirstApp(HttpServletRequest request,
                                                        HttpServletResponse response){
        String student_id = (String) request.getAttribute("student_id");
        List<ApplicationRecord> appRec = appRecRep.getFirstApp(student_id);
        request.setAttribute("appRec", appRec);
        return appRec;
    }

    /**
     * 查询第二志愿信息
     **/
    @ResponseBody
    @RequestMapping(value = "/getStudent",method = {RequestMethod.GET,RequestMethod.POST})
    public List<ApplicationRecord> getSecondApp(HttpServletRequest request,
                                               HttpServletResponse response){
        String student_id = (String) request.getAttribute("student_id");
        List<ApplicationRecord> appRec = appRecRep.getSecondApp(student_id);
        request.setAttribute("appRec", appRec);
        return appRec;
    }

    /**
     * 查询第三志愿信息
     **/
    @ResponseBody
    @RequestMapping(value = "/getStudent",method = {RequestMethod.GET,RequestMethod.POST})
    public List<ApplicationRecord> getThirdApp(HttpServletRequest request,
                                               HttpServletResponse response){
        String student_id = (String) request.getAttribute("student_id");
        List<ApplicationRecord> appRec = appRecRep.getThirdApp(student_id);
        request.setAttribute("appRec", appRec);
        return appRec;
    }
}
