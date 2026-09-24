package ui;

import service.CourseService;
import service.RegistrationService;
import model.Course;
import model.Student;
import utils.DialogUtils;
import utils.TableUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RegistrationPanel extends JPanel {
    private final Student student;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final CourseService courseService;
    private final RegistrationService regService;
    private final DashboardFrame parentFrame;

    public RegistrationPanel(Student student, DashboardFrame parentFrame) {
        this.student = student;
        this.parentFrame = parentFrame;
        courseService = new CourseService();
        regService = new RegistrationService();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("Register or Drop Courses");
        lblTitle.setFont(UIConstants.TITLE_FONT);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(lblTitle, BorderLayout.NORTH);

        String[] cols = {"Course Code", "Course Name", "Credits", "Available Seats", "Status"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        TableUtils.styleTable(table);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnRegister = new JButton("Register Course (CTRL+R)");
        JButton btnDrop = new JButton("Drop Course (CTRL+D)");

        btnRegister.setFont(UIConstants.BOLD_FONT);
        btnRegister.setBackground(UIConstants.PRIMARY_BLUE);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.setContentAreaFilled(false);
        btnRegister.setOpaque(true);
        btnRegister.setBorderPainted(false);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnDrop.setFont(UIConstants.BOLD_FONT);
        btnDrop.setBackground(new Color(220, 53, 69)); // Red color
        btnDrop.setForeground(Color.WHITE);
        btnDrop.setFocusPainted(false);
        btnDrop.setContentAreaFilled(false);
        btnDrop.setOpaque(true);
        btnDrop.setBorderPainted(false);
        btnDrop.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnPanel.add(btnRegister);
        btnPanel.add(btnDrop);
        add(btnPanel, BorderLayout.SOUTH);

        btnRegister.addActionListener(e -> handleRegister());
        btnDrop.addActionListener(e -> handleDrop());

        loadTableData();
    }

    public void loadTableData() {
        tableModel.setRowCount(0);
        List<Course> all = courseService.getAllCourses();
        List<Course> registered = student.getRegisteredCourses();
        for (Course c : all) {
            String status = registered.contains(c) ? "Registered" : "Not Registered";
            tableModel.addRow(new Object[]{
                    c.getCourseCode(),
                    c.getCourseName(),
                    c.getCredits(),
                    c.getAvailableSeats(),
                    status
            });
        }
    }

    private void handleRegister() {
        int row = table.getSelectedRow();
        if (row == -1) {
            DialogUtils.showError(this, "Please select a course to register.");
            return;
        }

        String code = tableModel.getValueAt(row, 0).toString();
        Course course = courseService.getAllCourses().stream()
                .filter(c -> c.getCourseCode().equals(code))
                .findFirst().orElse(null);

        if (course != null) {
            try {
                regService.registerCourse(student, course);
                parentFrame.setStatus("Successfully registered for " + course.getCourseName());
                DialogUtils.showSuccess(this, "Registration Successful!");
                parentFrame.refreshPanels();
            } catch (Exception ex) {
                DialogUtils.showError(this, ex.getMessage());
            }
        }
    }

    private void handleDrop() {
        int row = table.getSelectedRow();
        if (row == -1) {
            DialogUtils.showError(this, "Please select a course to drop.");
            return;
        }

        String code = tableModel.getValueAt(row, 0).toString();
        Course course = courseService.getAllCourses().stream()
                .filter(c -> c.getCourseCode().equals(code))
                .findFirst().orElse(null);

        if (course != null) {
            try {
                if(DialogUtils.showConfirm(this, "Are you sure you want to drop " + course.getCourseName() + "?")) {
                    regService.dropCourse(student, course);
                    parentFrame.setStatus("Dropped course: " + course.getCourseName());
                    DialogUtils.showSuccess(this, "Course Dropped Successfully!");
                    parentFrame.refreshPanels();
                }
            } catch (Exception ex) {
                DialogUtils.showError(this, ex.getMessage());
            }
        }
    }
}