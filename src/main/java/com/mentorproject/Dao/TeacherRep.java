package com.mentorproject.Dao;


import com.mentorproject.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRep extends JpaRepository<Teacher,String> {

    /**导师登录
     *
     * @param teacher_id
     * @param password
     * @return
     */
    @Query(value = "select" +
            "           teacher_id, teacher_name, gender, teacher_description, password" +
            "       from " +
            "           mentor.teacher " +
            "       where teacher_id=?1 and password=?2",
            nativeQuery = true)
    List<Teacher> logCheck(String teacher_id, String password);

    /**查看导师个人信息
     *
     * @param teacher_id
     * @return
     */
    @Query(value = "select " +
            "           teacher_id, teacher_name, gender, teacher_description, password" +
            "       from" +
            "           mentor.teacher" +
            "       where " +
            "           teacher_id = ?1",
            nativeQuery = true)
    Teacher getInfo(String teacher_id);

    /**查看私信
     *
     * @param receiver
     * @return
     */
    @Query(value = "select " +
            "           message,is_read" +
            "       from" +
            "           mentor.message" +
            "       where " +
            "           receiver = ?1" ,
            nativeQuery = true)
    List<Message> checkMessage(String receiver);

    /**查看双选结果
     *
     * @param teacher_id
     * @return
     */
    @Query(value = "select " +
            "           teacher_name,student_name" +
            "       from" +
            "           mentormatch" +
            "       join " +
            "           student" +
            "       on" +
            "           mentormatch.student_id = student.student_id" +
            "       join" +
            "           teacher" +
            "       on" +
            "           mentormatch.teacher_id = teacher.teacher_id" +
            "       where " +
            "           mentormatch.teacher_id = ?1",
            nativeQuery = true)
    List<Result> checkResult(String teacher_id);

    /**获取该老师当前匹配完成的个数
     *
     * @param teahcer_id
     * @return
     */
    @Query(value = "select " +
            "           COUNT(*)" +
            "        from " +
            "           mentor.mentormatch" +
            "        where " +
            "           teacher_id = ?1",
            nativeQuery = true)
    Integer getMatchCount(String teahcer_id);
}
