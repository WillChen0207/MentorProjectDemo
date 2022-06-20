package com.mentorproject.Dao;

import com.mentorproject.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRep extends JpaRepository<Student,String> {

    @Query(value = "select" +
            "           student_id, student_name, gender, gpa, password" +
            "       from " +
            "           mentor.student " +
            "       where student_id=?1 and password=?2",
            nativeQuery = true)
    List<Student> logCheck(String student_id, String password);
}
