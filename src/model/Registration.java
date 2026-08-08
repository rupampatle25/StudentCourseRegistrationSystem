package model;

import java.util.Date;

public class Registration {
    private String studentId;
    private String courseCode;
    private Date registrationDate;

    public Registration(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.registrationDate = new Date();
    }

    public String getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }
    public Date getRegistrationDate() { return registrationDate; }
}