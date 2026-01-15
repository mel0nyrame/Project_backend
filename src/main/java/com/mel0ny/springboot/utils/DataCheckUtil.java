package com.mel0ny.springboot.utils;

import com.mel0ny.springboot.pojo.Course;
import com.mel0ny.springboot.pojo.Score;
import com.mel0ny.springboot.pojo.Student;
import org.springframework.stereotype.Component;

@Component
public class DataCheckUtil {

    private boolean hasText(String str) {
        return str != null && !str.trim().isEmpty();
    }

    private boolean checkName(String name) {
        return name.matches("[\\u4e00-\\u9fa5]{2,10}");
    }

    private boolean checkPhone(Long phone) {
        return phone.toString().matches("^1[3-9]\\d{9}$");
    }

    private boolean checkBirthday(String birthday) {
        return birthday.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    private boolean checkEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private boolean checkScore(Double score) {
        return (score < 100.00 && score > 0);
    }

    public String studentChecker(Student student) {
        if (hasText(student.getStudentName()) && !checkName(student.getStudentName())) {
            return "姓名格式错误:需2-10个中文字符";
        }
        if (student.getStudentPhone() != null && !checkPhone(student.getStudentPhone())) {
            return "手机号格式错误:需11位数字，1开头，第二位3-9";
        }
        if (hasText(student.getStudentBirthday()) && !checkBirthday(student.getStudentBirthday())) {
            return "生日格式错误:需YYYY-MM-DD格式";
        }
        if (hasText(student.getStudentEmail()) && !checkEmail(student.getStudentEmail())) {
            return "邮箱格式错误";
        }
        return null;
    }

    public String courseChecker(Course course) {
        if (hasText(course.getCourseName()) && !checkName(course.getCourseName())) {
            return "课程名字格式错误:需2-10个中文字符";
        }
        return null;
    }

    public String scoreChecker(Score score) {
        if (score.getScore() != null && !checkScore(score.getScore())) {
            return "分数输入错误";
        }
        return null;
    }

}