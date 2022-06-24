package com.mentorproject.Controller;


import com.mentorproject.Dao.ApplicationRecordRep;
import com.mentorproject.Dao.MessageRep;
import com.mentorproject.Dao.ResultRep;
import com.mentorproject.Dao.TeacherRep;
import com.mentorproject.Entity.ApplicationRecord;
import com.mentorproject.Entity.Message;
import com.mentorproject.Entity.Result;
import com.mentorproject.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRep teacherRep;
    private MessageRep messageRep;
    private ResultRep resultRep;
    private ApplicationRecordRep applicationRecordRep;

    public TeacherController(MessageRep messageRep){
        this.messageRep = messageRep;
    }

    /**
     *查询所有导师
     * @return
     **/
    @ResponseBody
    @RequestMapping(value = "/getall",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Teacher> getTeacherList(){
       return teacherRep.findAll();
    }

    /**
     * 查询导师个人信息
     * @param teacher_id
     * @return
     */
    @RequestMapping(value = "/getinfo",method = {RequestMethod.GET,RequestMethod.POST})
    public Teacher getTeacherInfo (@RequestParam("teacher_id") String teacher_id){
        return teacherRep.getInfo(teacher_id);
    }

    /**
     * 添加一个导师
     * @param teacherId
     * @param teacherName
     * @param gender
     * @param teacherDescription
     * @param password
     **/
    @RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addTeacher(@RequestParam("teacherId") String teacherId,
                                   @RequestParam("teacherName") String teacherName,
                                   @RequestParam("gender") Integer gender,
                                   @RequestParam("teacherDescription") String teacherDescription,
                                   @RequestParam("password") String password){
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(teacherName);
        teacher.setGender(gender);
        teacher.setTeacherDescription(teacherDescription);
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
    /**
     * 修改导师个人介绍
     * @param teacher_id
     * @param teacher_description
     * @return
     */
    @RequestMapping(value = "/updateDescription",method = {RequestMethod.GET,RequestMethod.POST})
    public Teacher updateDescription(@RequestParam("teacher_id") String teacher_id,
                                       @RequestParam("teacher_description") String teacher_description) {
        Optional<Teacher> op = teacherRep.findById(teacher_id);
        op.ifPresent(teacher -> {
            teacher.setTeacherDescription(teacher_description);
            teacherRep.save(teacher);
        });
        return teacherRep.getInfo(teacher_id);
    }


    /**
     * 修改密码
     * @param teacher_id
     * @param password
     * @return
     */
    @RequestMapping(value = "/updatePassword",method = {RequestMethod.GET,RequestMethod.POST})
    public Teacher updatePassword(@RequestParam("teacher_id") String teacher_id,
                                       @RequestParam("password") String password) {
        Optional<Teacher> op = teacherRep.findById(teacher_id);
        op.ifPresent(teacher -> {
            teacher.setPassword(password);
            teacherRep.save(teacher);
        });
        return teacherRep.getInfo(teacher_id);
    }


    /**查看私信
     *
     * @param teacher_id
     * @return
     */
    @RequestMapping(value = "/checkMessage",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Message> checkMessage(@RequestParam("teacher_id") String teacher_id){

        return messageRep.checkMessage(teacher_id);
    }

    /**向指定学生发送私信
     *
     * @param teacher_id
     * @param student_id
     * @param messageinfo
     */
    @RequestMapping(value = "/sendMessage",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView sendMessage(@RequestParam("teacher_id") String teacher_id,
                                    @RequestParam("student_id") String student_id,
                                    @RequestParam("messageinfo") String messageinfo){
        ModelAndView mav = new ModelAndView();
        Message messageRec = new Message();
        messageRec.setSender(teacher_id);
        messageRec.setReceiver(student_id);
        messageRec.setMessage(messageinfo);
        messageRec.setIsRead(0);
        messageRep.save(messageRec);
        mav.addObject("seccessmessage","发送成功");
        mav.setViewName("errorpage");
        return mav;
    }


    /**查询双选结果
     *
     * @param teacher_id
     */
    @RequestMapping(value = "/checkResult",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Result> checkResult(@RequestParam("teacher_id") String teacher_id){
        return resultRep.checkResult(teacher_id);
    }

    /**
     * 查看可以选择的学生
     * @param teacher_id
     */
    @RequestMapping(value = "/selectStudent",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Result> selectStudent(@RequestParam("teacher_id") String teacher_id){
        return resultRep.selectStudent(teacher_id);
    }

    /**
     * 进行选择
     * @param student_id
     * @param is_selected
     * */
    @RequestMapping(value = "/updateSelect",method = {RequestMethod.GET,RequestMethod.POST})
    public ApplicationRecord updateSelect(@RequestParam("student_id") String student_id,
                                     @RequestParam("is_selected") Integer is_selected) {
        Optional<ApplicationRecord> op = applicationRecordRep.findById(student_id);
        op.ifPresent(applicationRecord -> {
            applicationRecord.setIs_selected(is_selected);
            applicationRecordRep.save(applicationRecord);
        });
        return applicationRecordRep.getApplicationRecordByStudentId(student_id);
    }


}
