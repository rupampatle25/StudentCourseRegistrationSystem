package service;

import model.Course;
import model.Student;

public class RegistrationService {
    private final ValidationService validationService;

    public RegistrationService() {
        this.validationService = new ValidationService();
    }

    public void registerCourse(Student student, Course course) throws Exception {
        validationService.validateRegistration(student, course);
        course.decrementSeats();
        student.addCourse(course);
    }

    public void dropCourse(Student student, Course course) throws Exception {
        if (!student.getRegisteredCourses().contains(course)) {
            throw new Exception("You are not registered for this course.");
        }
        course.incrementSeats();
        student.removeCourse(course);
    }

    public int calculateTotalCredits(Student student) {
        return student.getRegisteredCourses().stream().mapToInt(Course::getCredits).sum();
    }
}