package ui;

import model.Course;
import service.CourseService;
import utils.TableUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchPanel extends JPanel {
    private final JTextField txtSearch;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final CourseService courseService;

    public SearchPanel() {
        courseService = new CourseService();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel lblSearch = new JLabel("Search (Code / Name / Faculty / Dept): ");
        lblSearch.setFont(UIConstants.BOLD_FONT);
        topPanel.add(lblSearch);

        txtSearch = new JTextField(25);
        txtSearch.setFont(UIConstants.NORMAL_FONT);
        topPanel.add(txtSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(UIConstants.BOLD_FONT);
        btnSearch.setBackground(UIConstants.PRIMARY_BLUE);
        btnSearch.setForeground(Color.WHITE);
        topPanel.add(btnSearch);

        add(topPanel, BorderLayout.NORTH);

        String[] columns = {"Course Code", "Course Name", "Department", "Faculty Name", "Available Seats"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        TableUtils.styleTable(table);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Action Listeners
        btnSearch.addActionListener(e -> performSearch());
        txtSearch.addActionListener(e -> performSearch()); // Allows pressing Enter to search
    }

    private void performSearch() {
        tableModel.setRowCount(0);
        String query = txtSearch.getText();
        List<Course> results = courseService.searchCourse(query);
        for(Course c : results) {
            tableModel.addRow(new Object[]{
                    c.getCourseCode(),
                    c.getCourseName(),
                    c.getDepartment(),
                    c.getFacultyName(),
                    c.getAvailableSeats()
            });
        }
    }
}