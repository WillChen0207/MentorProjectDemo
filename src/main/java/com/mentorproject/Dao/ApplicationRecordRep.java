package com.mentorproject.Dao;

import com.mentorproject.Entity.ApplicationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ApplicationRecordRep extends JpaRepository<ApplicationRecord,String> {

    @Query(value = "select " +
            "           student_id, first_app, second_app, third_app, is_selected" +
            "       from" +
            "           mentor.application_record" +
            "       where student_id=?1",
            nativeQuery = true)
    ApplicationRecord getApplicationRecordByStudentId(String student_id);

    @Query(value = "select " +
            "           student_id, first_app, is_selected" +
            "       from" +
            "           mentor.application_record" +
            "       where student_id=?1",
            nativeQuery = true)
    ApplicationRecord getFirstApp(String student_id);

    @Query(value = "select " +
            "           student_id,  second_app, is_selected" +
            "       from" +
            "           mentor.application_record" +
            "       where student_id=?1",
            nativeQuery = true)
    ApplicationRecord getSecondApp(String student_id);

    @Query(value = "select " +
            "           student_id, third_app, is_selected" +
            "       from" +
            "           mentor.application_record" +
            "       where student_id=?1",
            nativeQuery = true)
    ApplicationRecord getThirdApp(String student_id);

    @Query(value = "select " +
            "           student_id, first_app, second_app, third_app, is_selected" +
            "       from" +
            "           mentor.application_record" +
            "       where student_id=?1",
            nativeQuery = true)
    ApplicationRecord select(String student_id);
}
