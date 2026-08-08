package ui;

import service.CourseService;
import model.Course;
import utils.TableUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CoursePanel extends JPanel {
    private final JTable courseTable;
    private final DefaultTableModel tableModel;
    private final CourseService courseService;

    public CoursePanel() {
        courseService = new CourseService();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("Available Courses");
        lblTitle.setFont(UIConstants.TITLE_FONT);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(lblTitle, BorderLayout.NORTH);

        String[] columns = {"Course Code", "Course Name", "Faculty", "Credits", "Total Capacity", "Available Seats", "Schedule"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        courseTable = new JTable(tableModel);
        TableUtils.styleTable(courseTable);
        add(new JScrollPane(courseTable), BorderLayout.CENTER);

        loadCourses();
    }

    public void loadCourses() {
        tableModel.setRowCount(0);
        List<Course> courses = courseService.getAllCourses();
        for (Course c : courses) {
            tableModel.addRow(new Object[]{
                    c.getCourseCode(),
                    c.getCourseName(),
                    c.getFacultyName(),
                    c.getCredits(),
                    c.getCapacity(),
                    c.getAvailableSeats(),
                    c.getSchedule().toString()
            });
        }
    }
}