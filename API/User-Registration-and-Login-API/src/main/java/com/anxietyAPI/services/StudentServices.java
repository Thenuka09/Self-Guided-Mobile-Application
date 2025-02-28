package com.anxietyAPI.services;

import com.anxietyAPI.models.Students;
import com.anxietyAPI.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServices {

    @Autowired
    StudentRepository studentRepository;

    public int registerNewStudentMethod(String email, String faculty, String name, String password, String phone_number, String student_id){
        return studentRepository.registerStudent(email, faculty, name, password, phone_number, student_id);
    }

//    public int updateMarksWhereNull(int marks) {
//        return studentRepository.updateMarksWhereNull(marks);
//    }

    public int updateMarksById(int id, int marks) {
        return studentRepository.updateMarksById(id, marks);
    }

    public int updateConsentById(int id, String consent) {
        return studentRepository.updateConsentById(id, consent);
    }


//    public int updateUserSayWhereNull(String user_say) {
//        return studentRepository.updateUserSayWhereNull(user_say);
//    }

    public int updateUserSayById(int id, String user_say) {
        return studentRepository.updateUserSayById(id, user_say);
    }

    // update the depression marks of the students
    public int updateDepressionMarks(int id, int dep_marks) {
        return studentRepository.updateDepressionMarks(id, dep_marks);
    }

    //update the user say of Depression test
    public int updateDepressionUserSayById(int id, String dep_userSay) {
        return studentRepository.updateDepressionUserSayById(id, dep_userSay);
    }


    // check student email service method
    public List<String> checkStudentEmail(String email){
        return studentRepository.checkStudentEmail(email);
    }

    // check student password service method
    public String checkStudentPasswordByEmail(String password){
        return studentRepository.checksStudentPasswordUsingEmail(password);
    }

    // get user details using email
    public Students getStudentDetailsByEmail(String email){
        return studentRepository.GetStudentDetailsByEmail(email);
    }

    // reset the password
    public int updatePasswordByEmail(String email, String password) {
        return studentRepository.updatePasswordByEmail(email, password);
    }

}
