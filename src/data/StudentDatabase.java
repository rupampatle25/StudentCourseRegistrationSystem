package data;

import model.Student;
import java.util.HashMap;
import java.util.Map;

public class StudentDatabase {
    private static final Map<String, Student> students = new HashMap<>();

    static {
        // Sample Student credentials
        students.put("STU001", new Student("STU001", "John Doe", "1234", "john.doe@university.edu", "9876543210"));
        students.put("STU002", new Student("STU002", "Jane Smith", "pass", "jane.smith@university.edu", "9123456780"));
    }

    public static Student getStudent(String id) {
        return students.get(id);
    }

    // NEW METHOD: Allows us to save dynamically created students
    public static void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }
}