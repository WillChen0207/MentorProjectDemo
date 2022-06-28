package com.mentorproject.Controller;


import com.mentorproject.Dao.*;
import com.mentorproject.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRep teacherRep;
    @Autowired
    private MessageRep messageRep;
    @Autowired
    private MentorMatchRep mentorMatchRep;
    @Autowired
    private ResultRep resultRep;
    @Autowired
    private ApplicationRecordRep appRecRep;

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
    @ResponseBody
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
    @ResponseBody
    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public Integer logCheck(@RequestParam("teacher_id") String teacher_id,
                            @RequestParam("password") String password,
                            HttpServletRequest request) throws Exception{
        String psw;
        psw = getShaPassword(password);
        List<Teacher> teacherList = teacherRep.logCheck(teacher_id,psw);
        if (teacherList.isEmpty()) {
            return 0;
        }else {
            return 1;
        }
    }
    /**
     * 修改导师个人介绍
     * @param teacher_id
     * @param teacher_description
     * @return
     */
    @ResponseBody
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
    @ResponseBody
    @RequestMapping(value = "/updatePassword",method = {RequestMethod.GET,RequestMethod.POST})
    public Teacher updatePassword(@RequestParam("teacher_id") String teacher_id,
                                       @RequestParam("password") String password)throws Exception {
        String psw;
        psw = getShaPassword(password);
        Optional<Teacher> op = teacherRep.findById(teacher_id);
        op.ifPresent(teacher -> {
            teacher.setPassword(psw);
            teacherRep.save(teacher);
        });
        return teacherRep.getInfo(teacher_id);
    }


    /**查看私信
     *
     * @param teacher_id
     * @param student_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkMessage",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Message> checkMessage(@RequestParam("teacher_id") String teacher_id,
                                      @RequestParam("student_id") String student_id){

        return messageRep.checkMessage(teacher_id,student_id);
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
    @ResponseBody
    @RequestMapping(value = "/checkResult",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Result> checkResult(@RequestParam("teacher_id") String teacher_id){
        return resultRep.checkTeacherResult(teacher_id);
    }

    /**
     * 查看可以选择的学生
     */
    @ResponseBody
    @RequestMapping(value = "/selectStudentInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Result> selectStudent(){
        return resultRep.selectStudent();
    }

    /**
     * 进行选择
     * @param student_id
     * @param teacher_id
     * */
    @ResponseBody
    @RequestMapping(value = "/selectStudent",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Result> updateSelect(@RequestParam("student_id") String student_id,
                                     @RequestParam("teacher_id") String teacher_id) {
        Mentormatch mentorMatch = new Mentormatch();
        mentorMatch.setStudent_id(student_id);
        mentorMatch.setTeacher_id(teacher_id);
        mentorMatchRep.save(mentorMatch);
        return resultRep.checkTeacherResult(teacher_id);
    }

    /**查询第一志愿
     *
     * @param teacher_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFirstApp", method = {RequestMethod.GET,RequestMethod.POST})
    public List<ApplicationRecord> getAppFirstRecList(@RequestParam("teacher_id") String teacher_id){
        return appRecRep.getFirstApp(teacher_id);
    }

    /**查询第二志愿
     *
     * @param teacher_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSecondApp", method = {RequestMethod.GET,RequestMethod.POST})
    public List<ApplicationRecord> getAppSecondRecList(@RequestParam("teacher_id") String teacher_id){
        return appRecRep.getSecondApp(teacher_id);
    }

    /**查询第三志愿
     *
     * @param teacher_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getThirdApp", method = {RequestMethod.GET,RequestMethod.POST})
    public List<ApplicationRecord> getAppThirdRecList(@RequestParam("teacher_id") String teacher_id){
        return appRecRep.getThirdApp(teacher_id);
    }

    /**密码进行SHA加密
     *
     * @param password
     * @return
     * @throws Exception
     */
    public String getShaPassword(String password)throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] res = md.digest();
        String pswoutput;
        pswoutput = new BigInteger(1,res).toString(16);
        return pswoutput;
    }

    /**获取该老师当前匹配完成的个数
     *
     * @param teacher_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMatchCount", method = {RequestMethod.GET,RequestMethod.POST})
    public Integer getMatchCount(@RequestParam("teacher_id") String teacher_id){
        return teacherRep.getMatchCount(teacher_id);
    }
}
