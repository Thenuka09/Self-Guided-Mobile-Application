package com.anxietyAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Students {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment the ID
    private int id;
    private String name;
    private String faculty;
    private String student_id;
    private String phone_number;
    private String email;
    private String password;
    private String user_say;
    private Integer marks;
    private String consent;
    private int is_deleted_anxiety;

    private int is_deleted_depression;
    private int is_deleted_student;
    private int is_deleted_student_from_mainCounselor;
    private int add_counselor;

    public int getIs_deleted_student() {
        return is_deleted_student;
    }

    public void setIs_deleted_student(int is_deleted_student) {
        this.is_deleted_student = is_deleted_student;
    }

    public int getIs_deleted_student_from_mainCounselor() {
        return is_deleted_student_from_mainCounselor;
    }

    public void setIs_deleted_student_from_mainCounselor(int is_deleted_student_from_mainCounselor) {
        this.is_deleted_student_from_mainCounselor = is_deleted_student_from_mainCounselor;
    }

    public int getAdd_counselor() {
        return add_counselor;
    }

    public void setAdd_counselor(int add_counselor) {
        this.add_counselor = add_counselor;
    }

    public int getIs_deleted_depression() {
        return is_deleted_depression;
    }

    public void setIs_deleted_depression(int is_deleted_depression) {
        this.is_deleted_depression = is_deleted_depression;
    }

    private  Integer dep_marks;
    private String dep_userSay;

    public int getIs_deleted_anxiety() {
        return is_deleted_anxiety;
    }

    public void setIs_deleted_anxiety(int is_deleted_anxiety) {
        this.is_deleted_anxiety = is_deleted_anxiety;
    }

    public Integer getDep_marks() {
        return dep_marks;
    }

    public void setDep_marks(Integer dep_marks) {
        this.dep_marks = dep_marks;
    }

    public String getDep_userSay() {
        return dep_userSay;
    }

    public void setDep_userSay(String dep_userSay) {
        this.dep_userSay = dep_userSay;
    }

    public String getConsent() {
        return consent;
    }

    public void setConsent(String consent) {
        this.consent = consent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_say() {
        return user_say;
    }

    public void setUser_say(String user_say) {
        this.user_say = user_say;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
