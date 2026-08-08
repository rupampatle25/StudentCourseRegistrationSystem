package model;

public class Course {
    private String courseCode;
    private String courseName;
    private String description;
    private String facultyName;
    private int credits;
    private int capacity;
    private int availableSeats;
    private Schedule schedule;
    private String semester;
    private String department;

    public Course(String code, String name, String desc, String faculty, int credits, int capacity, Schedule schedule, String semester, String dept) {
        this.courseCode = code;
        this.courseName = name;
        this.description = desc;
        this.facultyName = faculty;
        this.credits = credits;
        this.capacity = capacity;
        this.availableSeats = capacity; // Initially, available equals total capacity
        this.schedule = schedule;
        this.semester = semester;
        this.department = dept;
    }

    // Getters
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public String getDescription() { return description; }
    public String getFacultyName() { return facultyName; }
    public int getCredits() { return credits; }
    public int getCapacity() { return capacity; }
    public int getAvailableSeats() { return availableSeats; }
    public Schedule getSchedule() { return schedule; }
    public String getSemester() { return semester; }
    public String getDepartment() { return department; }

    // Logic to manage seats safely
    public void decrementSeats() {
        if (availableSeats > 0) availableSeats--;
    }
    public void incrementSeats() {
        if (availableSeats < capacity) availableSeats++;
    }
}