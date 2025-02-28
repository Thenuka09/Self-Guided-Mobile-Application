package com.anxietyAPI.rest_controllers;

import com.anxietyAPI.models.Counselor;
import com.anxietyAPI.models.CounselorLogin;
import com.anxietyAPI.services.CounselorServices;
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
@RequestMapping("/api/anxiety/counselor/login")
public class CounselorLoginApiController {

    @Autowired
    CounselorServices counselorServices;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody CounselorLogin counselorLogin){

        // get student email
        List<String> counselorEmail = counselorServices.checkCounselorEmail(counselorLogin.getEmail());

        // check if email is empty
        if(counselorEmail.isEmpty() || counselorEmail == null){
            return new ResponseEntity("Email does not exists", HttpStatus.NOT_FOUND);

        }

        // get hashed Counselor password
        String hashed_password = counselorServices.checkCounselorPasswordByEmail(counselorLogin.getEmail());

        // validated Counselor password
//        if (!counselorLogin.getPassword().equals(password)){
//            return new ResponseEntity("Incorrect username or password", HttpStatus.BAD_REQUEST);
//
//        }

        // validated student password
        if (!BCrypt.checkpw(counselorLogin.getPassword(), hashed_password)){
            return new ResponseEntity("Incorrect username or password", HttpStatus.BAD_REQUEST);

        }

        // set Counselor object
        Counselor counselor = counselorServices.getCounselorDetailsByEmail(counselorLogin.getEmail());
        return new ResponseEntity(counselor, HttpStatus.OK);
    }
}
