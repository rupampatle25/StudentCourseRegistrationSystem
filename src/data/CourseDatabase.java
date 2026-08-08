package data;

import model.Course;
import model.Schedule;
import java.util.ArrayList;
import java.util.List;

public class CourseDatabase {
    private static final List<Course> courses = new ArrayList<>();

    static {
        courses.add(new Course("CS101", "Java Programming", "Intro to Java", "Dr. Sharma", 4, 60, new Schedule("Mon-Wed", "10:00 AM"), "Fall 2026", "Computer Science"));
        courses.add(new Course("CS102", "Data Structures", "Core DSA", "Prof. Kumar", 4, 50, new Schedule("Tue-Thu", "11:00 AM"), "Fall 2026", "Computer Science"));
        courses.add(new Course("MTH201", "Calculus I", "Advanced Math", "Dr. Gupta", 3, 100, new Schedule("Mon-Wed-Fri", "09:00 AM"), "Fall 2026", "Mathematics"));
        courses.add(new Course("PHY101", "Physics Fundamentals", "Basic Mechanics", "Dr. Verma", 4, 40, new Schedule("Tue-Thu", "02:00 PM"), "Fall 2026", "Physics"));
        courses.add(new Course("CS201", "Operating Systems", "OS Concepts", "Prof. Singh", 4, 60, new Schedule("Mon-Wed", "01:00 PM"), "Fall 2026", "Computer Science"));
        courses.add(new Course("ENG101", "Technical Writing", "Communication", "Dr. Roy", 2, 30, new Schedule("Fri", "10:00 AM"), "Fall 2026", "English"));
        courses.add(new Course("EE101", "Basic Electronics", "Circuits", "Prof. Das", 3, 50, new Schedule("Tue-Thu", "09:00 AM"), "Fall 2026", "Electrical"));
        courses.add(new Course("CS301", "Database Systems", "SQL & NoSQL", "Dr. Sharma", 4, 60, new Schedule("Mon-Wed", "03:00 PM"), "Fall 2026", "Computer Science"));
        courses.add(new Course("CS401", "AI Basics", "Intro to AI", "Dr. Menon", 4, 30, new Schedule("Tue-Thu", "04:00 PM"), "Fall 2026", "Computer Science"));
        courses.add(new Course("ECO101", "Microeconomics", "Economy", "Prof. Jain", 3, 80, new Schedule("Mon-Wed", "11:00 AM"), "Fall 2026", "Economics"));
    }

    public static List<Course> getAllCourses() { return courses; }
}