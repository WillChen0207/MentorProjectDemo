package com.mentorproject.Dao;

import com.mentorproject.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRep extends JpaRepository<Student,String> {

    @Query(value = "select" +
            "           student_id, student_name, gender, gpa, password" +
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
            "           student_id, student_name, gender, gpa, password" +
            "       from" +
            "           mentor.student" +
            "       where " +
            "           student_id = ?1",
            nativeQuery = true)
    List<Student> getInfo(String student_id);

    /**修改学生密码
     *
     * @param password
     * @param student_id
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update " +
            "           mentor.student" +
            "       set" +
            "           (password)" +
            "       value" +
            "           (?1)" +
            "       where student_id = ?2",
            nativeQuery = true)
    Integer updatePassword(String password,String student_id);
    //这能不能返回Integer值？
}
