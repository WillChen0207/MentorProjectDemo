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
            "           mentor.application_record" +
            "       where student_id=?1",
            nativeQuery = true)
    ApplicationRecord getApplicationRecordByStudentId(String student_id);

    @Query(value = "select " +
            "           student_id, first_app, teacher_name, is_selected" +
            "       from" +
            "           mentor.application_record" +
            "       inner join " +
            "           mentor.teacher " +
            "       on" +
            "           teacher.teacher_id = application_record.first_app" +
            "       where student_id=?1",
            nativeQuery = true)
    ApplicationRecord getFirstApp(String student_id);

    @Query(value = "select " +
            "           student_id,  second_app, teacher_name, is_selected" +
            "       from" +
            "           mentor.application_record" +
            "       inner join " +
            "           mentor.teacher " +
            "       on" +
            "           teacher.teacher_id = application_record.second_app" +
            "       where student_id=?1",
            nativeQuery = true)
    ApplicationRecord getSecondApp(String student_id);

    @Query(value = "select " +
            "           student_id, third_app, teacher_name, is_selected" +
            "       from" +
            "           mentor.application_record" +
            "       inner join " +
            "           mentor.teacher " +
            "       on" +
            "           teacher.teacher_id = application_record.third_app" +
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
