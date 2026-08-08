package service;

import data.CourseDatabase;
import model.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseService {

    public List<Course> getAllCourses() {
        return CourseDatabase.getAllCourses();
    }

    public List<Course> searchCourse(String query) {
        List<Course> result = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        for (Course c : CourseDatabase.getAllCourses()) {
            if (c.getCourseCode().toLowerCase().contains(lowerQuery) ||
                    c.getCourseName().toLowerCase().contains(lowerQuery) ||
                    c.getFacultyName().toLowerCase().contains(lowerQuery) ||
                    c.getDepartment().toLowerCase().contains(lowerQuery)) {
                result.add(c);
            }
        }
        return result;
    }
}