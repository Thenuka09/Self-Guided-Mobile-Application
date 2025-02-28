package com.anxietyAPI.repository;

import com.anxietyAPI.models.Counselor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounselorRepository extends CrudRepository<Counselor, Integer> {

    @Query(value = "SELECT email FROM Counselor WHERE email = :email", nativeQuery = true)
    List<String> checkCounselorEmail(@Param("email") String email);

    @Query(value = "SELECT password FROM Counselor WHERE email = :email", nativeQuery = true)
    String checksCounselorPasswordUsingEmail(@Param("email")String email);

    @Query(value = "SELECT * FROM Counselor WHERE email = :email", nativeQuery = true)
    Counselor GetCounselorDetailsByEmail(@Param("email") String email);
}
