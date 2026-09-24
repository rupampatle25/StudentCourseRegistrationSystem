package ui;

import model.Course;
import model.Student;
import service.RegistrationService;
import utils.DialogUtils;
import utils.TableUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;

public class MyCoursesPanel extends JPanel {
    private final Student student;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final RegistrationService regService;
    private final JLabel lblTotalCredits;

    public MyCoursesPanel(Student student) {
        this.student = student;
        regService = new RegistrationService();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("My Registered Courses");
        lblTitle.setFont(UIConstants.TITLE_FONT);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(lblTitle, BorderLayout.NORTH);

        String[] cols = {"Course Code", "Course Name", "Credits", "Faculty", "Schedule"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        TableUtils.styleTable(table);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        lblTotalCredits = new JLabel("Total Credits: 0");
        lblTotalCredits.setFont(UIConstants.TITLE_FONT);
        lblTotalCredits.setForeground(UIConstants.PRIMARY_BLUE);
        bottomPanel.add(lblTotalCredits, BorderLayout.WEST);

        JButton btnExport = new JButton("Export to Text File");
        btnExport.setFont(UIConstants.BOLD_FONT);
        btnExport.setBackground(UIConstants.PRIMARY_BLUE);
        btnExport.setForeground(Color.WHITE);
        btnExport.setFocusPainted(false);
        btnExport.setContentAreaFilled(false);
        btnExport.setOpaque(true);
        btnExport.setBorderPainted(false);
        btnExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExport.addActionListener(e -> exportToFile());
        bottomPanel.add(btnExport, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
        loadMyCourses();
    }

    public void loadMyCourses() {
        tableModel.setRowCount(0);
        for(Course c : student.getRegisteredCourses()) {
            tableModel.addRow(new Object[]{
                    c.getCourseCode(),
                    c.getCourseName(),
                    c.getCredits(),
                    c.getFacultyName(),
                    c.getSchedule().toString()
            });
        }
        lblTotalCredits.setText("Total Credits: " + regService.calculateTotalCredits(student));
    }

    private void exportToFile() {
        if(student.getRegisteredCourses().isEmpty()) {
            DialogUtils.showError(this, "No courses registered to export.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Registered Courses");
        fileChooser.setSelectedFile(new File(student.getStudentId() + "_Courses.txt"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println("=================================================");
                writer.println("           STUDENT COURSE REGISTRATION           ");
                writer.println("=================================================");
                writer.println("Student ID: " + student.getStudentId());
                writer.println("Name: " + student.getName());
                writer.println("Email: " + student.getEmail());
                writer.println("-------------------------------------------------");
                for(Course c : student.getRegisteredCourses()) {
                    writer.println(c.getCourseCode() + " - " + c.getCourseName() + " (" + c.getCredits() + " Credits)");
                    writer.println("Faculty : " + c.getFacultyName());
                    writer.println("Schedule: " + c.getSchedule().toString());
                    writer.println("-------------------------------------------------");
                }
                writer.println("Total Credits Enrolled: " + regService.calculateTotalCredits(student));
                writer.println("=================================================");

                DialogUtils.showSuccess(this, "Registered courses exported successfully!");
            } catch (Exception ex) {
                DialogUtils.showError(this, "Error exporting file: " + ex.getMessage());
            }
        }
    }
}