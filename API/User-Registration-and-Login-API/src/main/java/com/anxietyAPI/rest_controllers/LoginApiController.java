package com.anxietyAPI.rest_controllers;

import com.anxietyAPI.models.LoginStudent;
import com.anxietyAPI.models.Students;
import com.anxietyAPI.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/anxiety/student/login")
public class LoginApiController {

    @Autowired
    StudentServices studentServices;

    @PostMapping
    public ResponseEntity validatedStudent(@RequestBody LoginStudent loginStudent){

        // get student email
        List<String> studentEmail = studentServices.checkStudentEmail(loginStudent.getEmail());

        // check if email is empty
        if(studentEmail.isEmpty() || studentEmail == null){
            return new ResponseEntity("Email does not exists", HttpStatus.NOT_FOUND);

        }

        // get hashed student password
        String hashed_password = studentServices.checkStudentPasswordByEmail(loginStudent.getEmail());

        // validated student password
        if (!BCrypt.checkpw(loginStudent.getPassword(), hashed_password)){
            return new ResponseEntity("Incorrect username or password", HttpStatus.BAD_REQUEST);

        }

        // set student object
        Students students = studentServices.getStudentDetailsByEmail(loginStudent.getEmail());

        return new ResponseEntity(students, HttpStatus.OK);
    }
}
