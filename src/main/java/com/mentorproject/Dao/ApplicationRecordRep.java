package com.mentorproject.Dao;

import com.mentorproject.Entity.ApplicationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRecordRep extends JpaRepository<ApplicationRecord,String> {

    @Query(value = "select " +
            "           student_id, first_app, second_app, third_app, is_selected" +
            "       from" +
            "           mentor.application" +
            "       where student_id=?1",
            nativeQuery = true)
    List<ApplicationRecord> getApplicationRecordByStudentId(String student_id);
}
