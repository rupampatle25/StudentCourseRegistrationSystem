package ui;

import model.Student;
import utils.AppTheme;
import utils.DialogUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardFrame extends JFrame {
    private final Student loggedInStudent;
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final JLabel lblStatus;

    // Panel Instances
    private final CoursePanel coursePanel;
    private final RegistrationPanel registrationPanel;
    private final MyCoursesPanel myCoursesPanel;
    private final SearchPanel searchPanel;
    private final ProfilePanel profilePanel;

    public DashboardFrame(Student student) {
        this.loggedInStudent = student;
        setTitle("Student Course Registration System - Dashboard");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        createMenuBar();

        // Sidebar Menu
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(UIConstants.PRIMARY_BLUE);
        sidebar.setPreferredSize(new Dimension(220, 0));

        JLabel lblWelcome = new JLabel("Welcome, " + student.getName().split(" ")[0]);
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        sidebar.add(lblWelcome);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize child panels
        coursePanel = new CoursePanel();
        registrationPanel = new RegistrationPanel(student, this);
        myCoursesPanel = new MyCoursesPanel(student);
        searchPanel = new SearchPanel();
        profilePanel = new ProfilePanel(student);

        // Add to Card Layout
        cardPanel.add(coursePanel, "COURSES");
        cardPanel.add(registrationPanel, "REGISTER");
        cardPanel.add(myCoursesPanel, "MY_COURSES");
        cardPanel.add(searchPanel, "SEARCH");
        cardPanel.add(profilePanel, "PROFILE");

        // Sidebar Navigation Buttons
        sidebar.add(createMenuButton("Available Courses", "COURSES"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("Register / Drop", "REGISTER"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("My Courses", "MY_COURSES"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("Search Course", "SEARCH"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("Profile", "PROFILE"));

        JButton btnLogout = createMenuButton("Logout", null);
        btnLogout.setBackground(new Color(220, 53, 69)); // Red color for logout
        btnLogout.addActionListener(e -> logout());
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnLogout);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        add(sidebar, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);

        // Status Bar
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        lblStatus = new JLabel(" Ready");
        statusBar.add(lblStatus, BorderLayout.WEST);

        JLabel lblTime = new JLabel();
        new Timer(1000, e -> {
            lblTime.setText(new SimpleDateFormat("EEE, MMM d, yyyy HH:mm:ss").format(new Date()) + " ");
        }).start();
        statusBar.add(lblTime, BorderLayout.EAST);
        add(statusBar, BorderLayout.SOUTH);

        setupKeyboardShortcuts();
    }

    private JButton createMenuButton(String text, String cardName) {
        JButton btn = new JButton(text);
        btn.setFont(UIConstants.BOLD_FONT);
        btn.setForeground(Color.WHITE);
        btn.setBackground(UIConstants.PRIMARY_BLUE.darker());
        btn.setFocusPainted(false);

        // FIX FOR WINDOWS RENDERING BUG
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);

        btn.setMaximumSize(new Dimension(200, 45));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (cardName != null) {
            btn.addActionListener(e -> {
                cardLayout.show(cardPanel, cardName);
                refreshPanels();
                setStatus("Navigated to " + text);
            });
        }
        return btn;
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(e -> logout());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(logoutItem);
        fileMenu.add(exitItem);

        JMenu viewMenu = new JMenu("View");
        JMenuItem darkToggle = new JMenuItem("Toggle Dark Mode");
        darkToggle.addActionListener(e -> AppTheme.toggleDarkMode(this));
        viewMenu.add(darkToggle);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Student Course Registration System\nVersion 1.0\nDeveloped for CodSoft Internship.",
                "About", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private void setupKeyboardShortcuts() {
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK), "register");
        am.put("register", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "REGISTER");
                setStatus("Shortcut activated: Register Course");
            }
        });
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK), "drop");
        am.put("drop", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "REGISTER");
                setStatus("Shortcut activated: Drop Course");
            }
        });
    }

    public void refreshPanels() {
        coursePanel.loadCourses();
        registrationPanel.loadTableData();
        myCoursesPanel.loadMyCourses();
    }

    public void setStatus(String msg) {
        lblStatus.setText(" " + msg);
    }

    private void logout() {
        if(DialogUtils.showConfirm(this, "Are you sure you want to logout?")) {
            new LoginFrame().setVisible(true);
            this.dispose();
        }
    }
}