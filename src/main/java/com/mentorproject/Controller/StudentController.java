package com.mentorproject.Controller;


import com.mentorproject.Dao.MessageRep;
import com.mentorproject.Dao.ResultRep;
import com.mentorproject.Dao.StudentRep;
import com.mentorproject.Entity.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRep studentRep;
    @Autowired
    private MessageRep messageRep;
    @Autowired
    private ResultRep resultRep;

    public StudentController(MessageRep messageRep) {
        this.messageRep = messageRep;
    }

    /**
     *查询所有学生
     * @return
     **/
    @ResponseBody
    @RequestMapping(value = "/getall",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Student> getStudentList(){
        return studentRep.findAll();
    }

    /**查询学生个人信息
     *
     * @param student_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getinfo",method = {RequestMethod.GET,RequestMethod.POST})
    public Student getStudentInfo (@RequestParam("student_id") String student_id){
       return studentRep.getInfo(student_id);
    }

    /**
     * 查看当前学生志愿信息
     * @param student_id
     * @return
     **/
    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/getapp",method = {RequestMethod.GET,RequestMethod.POST})
    public List<ApplicationRecord> getAppRecByStudentId(@RequestParam("student_id") String student_id,
                                                        HttpServletRequest request,
                                                        HttpServletResponse response){
        request.setAttribute("student_id", student_id);
        request.getRequestDispatcher("/application/getStudent").forward(request,response);
        List<ApplicationRecord> appRecList = new ArrayList<>();
        ApplicationRecord appRec = (ApplicationRecord) request.getAttribute("appRec");
        appRecList.add(appRec);
        return appRecList;
    }

    /**
    * 添加一个学生
     * @param student_id
     * @param student_name
     * @param gender
     * @param gpa
     * @param password
     * @param student_description
    **/
    @RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addStudent(@RequestParam("student_id") String student_id,
                                   @RequestParam("student_name") String student_name,
                                   @RequestParam("gender") Integer gender,
                                   @RequestParam("gpa") Double gpa,
                                   @RequestParam("password") String password,
                                   @RequestParam("student_description") String student_description){
        Student student = new Student();
        student.setStudentId(student_id);
        student.setStudentName(student_name);
        student.setGender(gender);
        student.setGpa(gpa);
        student.setPassword(password);
        student.setStudentDescription(student_description);
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
            mav.addObject("errmessage","输入的学号或密码有误");
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
     * @param first_app
     * @param second_app
     * @param third_app
     * @param is_selected
     **/
    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/fillapp",method = {RequestMethod.GET,RequestMethod.POST})
    public List<ApplicationRecord> fillApp(@RequestParam("student_id") String student_id,
                                           @RequestParam("first_app") String first_app,
                                           @RequestParam("second_app") String second_app,
                                           @RequestParam("third_app") String third_app,
                                           @RequestParam("is_selected") Integer is_selected,
                                           HttpServletRequest request,
                                           HttpServletResponse response){
        List<ApplicationRecord> fillApp = new ArrayList<>();
        ApplicationRecord appRec = new ApplicationRecord();
        appRec.setStudentId(student_id);
        appRec.setFirst_app(first_app);
        appRec.setSecond_app(second_app);
        appRec.setThird_app(third_app);
        appRec.setIs_selected(is_selected);
        fillApp.add(appRec);
        request.setAttribute("appRec", appRec);
        request.getRequestDispatcher("/application/add").forward(request,response);
        return fillApp;
    }

    /**查看导师信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getteacherinfo",method = {RequestMethod.GET,RequestMethod.POST})
    public void getTeacherInfo(HttpServletRequest request){
        request.getRequestDispatcher("/teacher/getall");
    }

    /**修改个人密码
     *
     * @param student_id
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePassword",method = {RequestMethod.GET,RequestMethod.POST})
    public Student updatePassword(@RequestParam("student_id") String student_id,
                                       @RequestParam("password") String password) {
        Optional<Student> op = studentRep.findById(student_id);
        op.ifPresent(student -> {
            student.setPassword(password);
            studentRep.save(student);
        });
        return studentRep.getInfo(student_id);
    }

    /**修改个人简介
     *
     * @param student_id
     * @param student_description
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public Student updateInfo(@RequestParam("student_id") String student_id,
                              @RequestParam("student_description") String student_description){
        Optional<Student> op = studentRep.findById(student_id);
        op.ifPresent(student -> {
            student.setStudentDescription(student_description);
            studentRep.save(student);
        });
        return studentRep.getInfo(student_id);
    }

    /**查看私信
     *
     * @param student_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkMessage",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Message> checkMessage(@RequestParam("student_id") String student_id){
        return messageRep.checkMessage(student_id);
    }

    /**向指定导师发送私信
     *
     * @param student_id
     * @param teacher_id
     * @param messageinfo
     */
    @ResponseBody
    @RequestMapping(value = "/sendMessage",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView sendMessage(@RequestParam("student_id") String student_id,
                                     @RequestParam("teacher_id") String teacher_id,
                                     @RequestParam("messageinfo") String messageinfo){
        ModelAndView mav = new ModelAndView();
        Message messageRec = new Message();
        messageRec.setSender(student_id);
        messageRec.setReceiver(teacher_id);
        messageRec.setMessage(messageinfo);
        messageRec.setIsRead(0);
        messageRep.save(messageRec);
        mav.addObject("successmessage","发送成功");
        mav.setViewName("errorpage");
        return mav;
    }

    /**查询双选结果
     *
     * @param student_id
     */
    @ResponseBody
    @RequestMapping(value = "/checkResult",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Result> checkResult(@RequestParam("student_id") String student_id){
        return resultRep.checkResult(student_id);
    }
}