package service;

import data.StudentDatabase;
import model.Student;

public class StudentService {

    public Student authenticate(String studentId, String password) {
        Student student = StudentDatabase.getStudent(studentId);

        // If the student already exists in the database, verify their password
        if (student != null) {
            if (student.getPassword().equals(password)) {
                return student;
            } else {
                return null; // Wrong password for an existing account
            }
        }

        // IF STUDENT DOES NOT EXIST:
        // Automatically create a new student account with the entered ID and Password
        String dynamicName = "Student " + studentId;
        String dynamicEmail = studentId.toLowerCase() + "@university.edu";

        Student newStudent = new Student(studentId, dynamicName, password, dynamicEmail, "N/A");

        // Save them to the database so their registered courses are saved for the session
        StudentDatabase.addStudent(newStudent);

        return newStudent;
    }
}