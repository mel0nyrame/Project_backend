package com.mel0ny.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Student {
    private int studentId;
    private String studentName;
    private String studentGender;
    private String studentBirthday;
    private int studentPhone;
    private String studentEmail;
    private String studentMajor;
}
