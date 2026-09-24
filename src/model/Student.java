package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String name;
    private String password;
    private String email;
    private String phone;
    private List<Course> registeredCourses;

    public Student(String id, String name, String password, String email, String phone) {
        this.studentId = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<Course> getRegisteredCourses() { return registeredCourses; }

    public void addCourse(Course c) { registeredCourses.add(c); }
    public void removeCourse(Course c) { registeredCourses.remove(c); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return java.util.Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(studentId);
    }
}