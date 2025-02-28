package com.anxietyAPI.rest_controllers;

import com.anxietyAPI.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/anxiety")
public class RegistorApiController {

    @GetMapping("/student")
    public String testStudent() {
        return "Test complete";
    }

    @Autowired
    StudentServices studentServices;

    @PostMapping("/student/register")
    public ResponseEntity registerNewStudent(@RequestParam("email") String email,
                                             @RequestParam("faculty") String faculty,
                                             @RequestParam("name") String name,
                                             @RequestParam("password") String password,
                                             @RequestParam("phone_number") String phone_number,
                                             @RequestParam("student_id") String student_id) {

        if (email.isEmpty() || faculty.isEmpty() || name.isEmpty() || password.isEmpty() || phone_number.isEmpty() || student_id.isEmpty()) {
            return new ResponseEntity<>("complete all the fields", HttpStatus.BAD_REQUEST);
        }

        // encrypt the password / hash password
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        // register new user
        int result = studentServices.registerNewStudentMethod(email, faculty, name, hashed_password, phone_number, student_id);

        if (result != 1) {
            return new ResponseEntity<>("Register failed", HttpStatus.BAD_REQUEST);

        } else {
            return new ResponseEntity<>("Register Success", HttpStatus.OK);
        }

    }

//    @PostMapping("/student/updateMarks")
//    public ResponseEntity<String> updateStudentMark(@RequestParam("marks") int marks) {
//        int result = studentServices.updateMarksWhereNull(marks);
//
//        if (result == 0) {
//            return new ResponseEntity<>("No rows with null marks to update", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Update Successfully", HttpStatus.OK);
//        }
//    }

    @PostMapping("/student/updateMarks/{id}")
    public ResponseEntity<String> updateMarksById(@PathVariable("id") int id, @RequestParam("marks") int marks) {
        int result = studentServices.updateMarksById(id, marks);

        if (result == 0) {
            return new ResponseEntity<>("No student found with the given ID", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
    }

    // create API end point to store the depression marks
    @PostMapping("/student/updateDepressionMarks/{id}")
    public ResponseEntity<String> updateDepressionMarks(@PathVariable("id") int id, @RequestParam("dep_marks") int dep_marks) {
        int result = studentServices.updateDepressionMarks(id, dep_marks);

        if (result == 0) {
            return new ResponseEntity<>("No student found with the given ID", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
    }


    @PostMapping("/student/updateUserSay/{id}")
    public ResponseEntity<String> updateStudentSay(@PathVariable("id") int id, @RequestParam("user_say") String user_say) {
        int result = studentServices.updateUserSayById(id, user_say);

        if (result == 0) {
            return new ResponseEntity<>("No student found with the given ID", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
    }

    //update the usersay of depression test
    @PostMapping("/student/updateDepressionUserSay/{id}")
    public ResponseEntity<String> updateDepressionUserSayById(@PathVariable("id") int id, @RequestParam("dep_userSay") String dep_userSay) {
        int result = studentServices.updateDepressionUserSayById(id, dep_userSay);

        if (result == 0) {
            return new ResponseEntity<>("No student found with the given ID", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
    }


    // get the consent
    @PostMapping("/student/updateConsent/{id}")
    public ResponseEntity<String> updateStudentConsent(@PathVariable("id") int id, @RequestParam("consent") String consent) {
        int result = studentServices.updateConsentById(id, consent);

        if (result == 0) {
            return new ResponseEntity<>("No student found with the given ID", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
    }

    // reset the password
    @PostMapping("/student/reset/password")
    public ResponseEntity<String> resetPassword(@RequestParam("email") String email,
                                                @RequestParam("password") String password) {
        // Basic validation
        if (email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Email and new password must not be empty", HttpStatus.BAD_REQUEST);
        }

        // Hash the new password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Update the password
        int result = studentServices.updatePasswordByEmail(email, hashedPassword);

        if (result == 0) {
            return new ResponseEntity<>("No student found with the given email", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Password reset successful", HttpStatus.OK);
        }
    }


}

