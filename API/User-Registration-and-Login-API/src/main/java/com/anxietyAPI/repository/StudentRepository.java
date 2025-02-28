package com.anxietyAPI.repository;

import com.anxietyAPI.models.Students;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Students, Integer> {

    // login validation
    @Query(value = "SELECT email FROM Students WHERE email = :email", nativeQuery = true)
    List<String> checkStudentEmail(@Param("email")String email);

    @Query(value = "SELECT password FROM Students WHERE email = :email", nativeQuery = true)
    String checksStudentPasswordUsingEmail(@Param("email")String email);

    @Query(value = "SELECT * FROM Students WHERE email = :email", nativeQuery = true)
    Students GetStudentDetailsByEmail(@Param("email") String email);

    // end the login validation

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO STUDENTS (email, faculty, name, password, phone_number, student_id,marks, is_deleted_anxiety, is_deleted_depression, is_deleted_student, is_deleted_student_from_mainCounselor, add_counselor) VALUES (:email, :faculty, :name, :password, :phone_number, :student_id,0, 0, 0, 0, 0, 0)", nativeQuery = true)

    int registerStudent(@Param("email")String email,
                        @Param("faculty")String faculty,
                        @Param("name")String name,
                        @Param("password")String password,
                        @Param("phone_number")String phone_number,
                        @Param("student_id")String student_id);

//    @Transactional
//    @Modifying
//    @Query("UPDATE Students s SET s.marks = :marks WHERE s.marks IS NULL")
//    int updateMarksWhereNull(@Param("marks") int marks);

    @Transactional
    @Modifying
    @Query("UPDATE Students s SET s.marks = :marks WHERE s.id = :id")
    int updateMarksById(@Param("id") int id, @Param("marks") int marks);

    // store the depression marks of the students
    @Transactional
    @Modifying
    @Query("UPDATE Students s SET s.dep_marks = :dep_marks WHERE s.id = :id")
    int updateDepressionMarks(@Param("id") int id, @Param("dep_marks") int dep_marks);


//    @Transactional
//    @Modifying
//    @Query("UPDATE Students s SET s.user_say = :user_say WHERE s.user_say IS NULL")
//    int updateUserSayWhereNull(@Param("user_say") String user_say);

    @Transactional
    @Modifying
    @Query("UPDATE Students s SET s.user_say = :user_say WHERE s.id = :id")
    int updateUserSayById(@Param("id") int id, @Param("user_say") String user_say);

    // update the usersay of depression test
    @Transactional
    @Modifying
    @Query("UPDATE Students s SET s.dep_userSay = :dep_userSay WHERE s.id = :id")
    int updateDepressionUserSayById(@Param("id") int id, @Param("dep_userSay") String dep_userSay);

    // consent update
    @Transactional
    @Modifying
    @Query("UPDATE Students s SET s.consent = :consent WHERE s.id = :id")
    int updateConsentById(@Param("id") int id, @Param("consent") String consent);


    // password reset
    @Transactional
    @Modifying
    @Query(value = "UPDATE STUDENTS SET password = :password WHERE email = :email", nativeQuery = true)
    int updatePasswordByEmail(@Param("email") String email, @Param("password") String password);


}
