package com.mentorproject.Dao;

import com.mentorproject.Entity.Message;
import com.mentorproject.Entity.Result;
import com.mentorproject.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface StudentRep extends JpaRepository<Student,String> {

    /**学生登录
     *
     * @param student_id
     * @param password
     * @return
     */
    @Query(value = "select" +
            "           student_id, student_name, gender, gpa, password, student_description" +
            "       from " +
            "           mentor.student " +
            "       where " +
            "           student_id=?1 and password=?2",
            nativeQuery = true)
    List<Student> logCheck(String student_id, String password);

    /**查看学生个人信息
     *
     * @param student_id
     * @return
     */
    @Query(value = "select " +
            "           student_id, student_name, gender, gpa, password, student_description" +
            "       from" +
            "           mentor.student" +
            "       where " +
            "           student_id = ?1",
            nativeQuery = true)
    Student getInfo(String student_id);
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
     * @param student_id
     * @return
     */
    @Query(value = "select " +
            "           student_name,teacher_name" +
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
            "           mentormatch.student_id = ?1",
            nativeQuery = true)
    List<Result> checkResult(String student_id);
}
