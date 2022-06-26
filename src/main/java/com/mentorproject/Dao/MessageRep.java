package com.mentorproject.Dao;

import com.mentorproject.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRep extends JpaRepository<Message,Integer> {
    /**查看私信
     *
     * @param receiver
     * @return
     */
    @Query(value = "select " +
            "           rec_id,sender,receiver,message,is_read" +
            "       from" +
            "           mentor.message" +
            "       where " +
            "           receiver = ?1" +
            "       and " +
            "           sender = ?2" ,
            nativeQuery = true)
    List<Message> checkMessage(String receiver,String sender);
}
