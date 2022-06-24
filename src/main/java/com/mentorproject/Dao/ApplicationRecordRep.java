package com.mentorproject.Dao;

import com.mentorproject.Entity.ApplicationRecord;
import com.mentorproject.Entity.FirstApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ApplicationRecordRep extends JpaRepository<ApplicationRecord,String> {


    @Query(value = "select " +
            "           student_id, first_app, second_app, third_app, is_selected" +
            "       from" +
            "           mentor.application_record" +
            "       join" +
            "           mentor.teacher       " +
            "where student_id=?1",
            nativeQuery = true)
    ApplicationRecord getApplicationRecordByStudentId(String student_id);

    @Query(value = "select " +
            "           student_id,teacher_name AS first_app,is_selected" +
            "       from" +
            "           mentor.application_record" +
            "       join" +
            "           mentor.teacher" +
            "       on" +
            "           application_record.first_app = teacher.teacher_id" +
            "       where " +
            "           student_id=?1",
            nativeQuery = true)
    FirstApplication getFirstApp(String student_id);

    @Query(value = "select " +
            "           student_id,teacher_name AS second_app" +
            "       from" +
            "           mentor.application_record" +
            "       join" +
            "           mentor.teacher" +
            "       on" +
            "           application_record.second_app = teacher.teacher_id" +
            "       where " +
            "           student_id=?1",
            nativeQuery = true)
    ApplicationRecord getSecondApp(String student_id);

    @Query(value = "select " +
            "           student_id,teacher_name AS third_app" +
            "       from" +
            "           mentor.application_record" +
            "       join" +
            "           mentor.teacher" +
            "       on" +
            "           application_record.third_app = teacher.teacher_id" +
            "       where " +
            "           student_id=?1",
            nativeQuery = true)
    ApplicationRecord getThirdApp(String student_id);

}
