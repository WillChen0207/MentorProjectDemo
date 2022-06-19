package com.mentorproject.Dao;

import com.mentorproject.Entity.Student;
import com.mentorproject.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRep extends JpaRepository<Teacher,Integer> {

    @Query(value = "select" +
            "           teacher_id, teacher_name, gender, description, password" +
            "       from " +
            "           mentor.teacher " +
            "       where teacher_id=?1 and password=?2",
            nativeQuery = true)
    List<Teacher> logCheck(Integer teacher_id, String password);
}
