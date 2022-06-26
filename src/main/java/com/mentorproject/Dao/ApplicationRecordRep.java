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
            "       join" +
            "           mentor.teacher       " +
            "where student_id=?1",
            nativeQuery = true)
    ApplicationRecord getApplicationRecordByStudentId(String student_id);

    /**查询志愿
     *
     * @param teacher_id
     * @return
     */
    @Query(value = "select" +
            "           tb.student_id, tb.first_app, tb.second_app, tb.third_app, tb.is_selected" +
            "       from" +
            "           (SELECT " +
            "               student_id, first_app, second_app, third_app,is_selected" +
            "           from" +
            "               mentor.application_record " +
            "           WHERE" +
            "               first_app = ?1" +
            "            or" +
            "               second_app = ?1" +
            "            or" +
            "               third_app = ?1) tb" +
            "       where" +
            "           tb.is_selected = 0",
            nativeQuery = true)
    List<ApplicationRecord> getApp(String teacher_id);

}
