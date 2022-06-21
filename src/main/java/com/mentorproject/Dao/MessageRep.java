package com.mentorproject.Dao;

import com.mentorproject.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRep extends JpaRepository<Message,String> {
}
