package com.anxietyAPI.services;

import com.anxietyAPI.models.Counselor;
import com.anxietyAPI.repository.CounselorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounselorServices {

    @Autowired
    CounselorRepository counselorRepository;

    // check Counselor email service method
    public List<String> checkCounselorEmail(String email){
        return counselorRepository.checkCounselorEmail(email);
    }

    // check Counselor password service method
    public String checkCounselorPasswordByEmail(String password){
        return counselorRepository.checksCounselorPasswordUsingEmail(password);
    }

    // get Counselor details using email
    public Counselor getCounselorDetailsByEmail(String email){
        return counselorRepository.GetCounselorDetailsByEmail(email);
    }


}
