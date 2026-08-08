package service;

import model.Course;
import model.Student;

public class ValidationService {
    public static final int MAX_COURSES = 5;

    public void validateRegistration(Student student, Course course) throws Exception {
        if (student.getRegisteredCourses().size() >= MAX_COURSES) {
            throw new Exception("Maximum course limit (" + MAX_COURSES + ") reached.");
        }
        for (Course c : student.getRegisteredCourses()) {
            if (c.getCourseCode().equals(course.getCourseCode())) {
                throw new Exception("You are already registered for this course.");
            }
        }
        if (course.getAvailableSeats() <= 0) {
            throw new Exception("Course is full. No seats available.");
        }
    }
}