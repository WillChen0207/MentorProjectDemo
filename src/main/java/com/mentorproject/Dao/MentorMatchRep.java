package com.mentorproject.Dao;

import com.mentorproject.Entity.Mentormatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorMatchRep extends JpaRepository<Mentormatch,String> {
}
