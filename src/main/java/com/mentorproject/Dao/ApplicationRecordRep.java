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
            "           mentor.teacher" +
            "       where student_id=?1",
            nativeQuery = true)
    ApplicationRecord getApplicationRecordByStudentId(String student_id);

    /**查询第一志愿
     *
     * @param teacher_id
     * @return
     */
    @Query(value = "select" +
            "           student_id, first_app, second_app, third_app, is_selected" +
            "       from" +
            "           mentor.application_record " +
            "       WHERE" +
            "           first_app = ?1" +
            "       and" +
            "           is_selected = 0",
            nativeQuery = true)
    List<ApplicationRecord> getFirstApp(String teacher_id);

    /**查询第二志愿
     *
     * @param teacher_id
     * @return
     */
    @Query(value = "select" +
            "           student_id, first_app, second_app, third_app, is_selected" +
            "       from" +
            "           mentor.application_record " +
            "       WHERE" +
            "           second_app = ?1" +
            "       and" +
            "           is_selected = 0",
            nativeQuery = true)
    List<ApplicationRecord> getSecondApp(String teacher_id);
    /**查询第三志愿
     *
     * @param teacher_id
     * @return
     */
    @Query(value = "select" +
            "           student_id, first_app, second_app, third_app, is_selected" +
            "       from" +
            "           mentor.application_record " +
            "       WHERE" +
            "           third_app = ?1" +
            "       and" +
            "           is_selected = 0",
            nativeQuery = true)
    List<ApplicationRecord> getThirdApp(String teacher_id);
}
