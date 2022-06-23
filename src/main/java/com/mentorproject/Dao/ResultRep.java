package com.mentorproject.Dao;

import com.mentorproject.Entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRep extends JpaRepository<Result,String> {

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
