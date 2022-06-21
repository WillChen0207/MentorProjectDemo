package com.mentorproject.Dao;

import com.mentorproject.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRep extends JpaRepository<Message,String> {
}
