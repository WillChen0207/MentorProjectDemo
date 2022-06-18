package com.mentorproject.Dao;

import com.mentorproject.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRep extends JpaRepository<Teacher,Integer> {
}
