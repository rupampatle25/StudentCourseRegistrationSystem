package utils;

import data.CourseDatabase;
import data.StudentDatabase;
import model.Course;
import model.Student;
import ui.DashboardFrame;
import ui.LoginFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class ScreenshotGenerator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                generateScreenshots();
                System.out.println("Screenshots generated successfully!");
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

    private static void saveComponent(Component comp, String filename) {
        try {
            BufferedImage image = new BufferedImage(comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            comp.paint(g2);
            g2.dispose();

            File out = new File("screenshots", filename);
            ImageIO.write(image, "PNG", out);
            System.out.println("Saved: " + out.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateScreenshots() throws Exception {
        File dir = new File("screenshots");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        AppTheme.setupTheme();

        // 1. Login Frame
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        Thread.sleep(500);
        saveComponent(loginFrame, "01_login_screen.png");
        loginFrame.dispose();

        // Set up demo student with courses
        Student student = StudentDatabase.getStudent("STU001");
        List<Course> allCourses = CourseDatabase.getAllCourses();
        if (student.getRegisteredCourses().isEmpty()) {
            student.addCourse(allCourses.get(0)); // CS101
            student.addCourse(allCourses.get(1)); // CS102
            student.addCourse(allCourses.get(7)); // CS301
        }

        // 2. Dashboard - Available Courses
        DashboardFrame dash = new DashboardFrame(student);
        dash.setVisible(true);
        Thread.sleep(500);

        dash.showCard("COURSES");
        Thread.sleep(300);
        saveComponent(dash, "02_available_courses.png");

        // 3. Dashboard - Course Registration & Drop
        dash.showCard("REGISTER");
        Thread.sleep(300);
        saveComponent(dash, "03_course_registration.png");

        // 4. Dashboard - My Courses & Credit Tracking
        dash.showCard("MY_COURSES");
        Thread.sleep(300);
        saveComponent(dash, "04_my_courses.png");

        // 5. Dashboard - Search Course
        dash.showCard("SEARCH");
        dash.getSearchPanel().setSearchQuery("CS");
        Thread.sleep(300);
        saveComponent(dash, "05_search_course.png");

        // 6. Dashboard - Student Profile
        dash.showCard("PROFILE");
        Thread.sleep(300);
        saveComponent(dash, "06_student_profile.png");

        dash.dispose();
    }
}
