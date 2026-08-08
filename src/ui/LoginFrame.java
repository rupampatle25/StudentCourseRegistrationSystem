package ui;

import service.StudentService;
import utils.DialogUtils;
import utils.InputValidator;
import model.Student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private final JTextField txtId;
    private final JPasswordField txtPassword;
    private final StudentService studentService;

    public LoginFrame() {
        studentService = new StudentService();
        setTitle("Student Course Registration System - Login");
        setSize(500, 450); // Slightly taller for better spacing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        // Wrapper Panel to act as a margin/padding
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBackground(Color.WHITE);
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        JLabel lblTitle = new JLabel("STUDENT LOGIN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(UIConstants.PRIMARY_BLUE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        wrapperPanel.add(lblTitle, gbc);

        // Separator Line
        JSeparator separator = new JSeparator();
        separator.setForeground(UIConstants.PRIMARY_BLUE);
        gbc.gridy = 1;
        wrapperPanel.add(separator, gbc);

        // ID Label
        gbc.gridwidth = 1; gbc.gridy = 2;
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setFont(UIConstants.BOLD_FONT);
        wrapperPanel.add(idLabel, gbc);

        // ID TextField
        txtId = new JTextField(15);
        txtId.setFont(UIConstants.NORMAL_FONT);
        txtId.setPreferredSize(new Dimension(200, 35));
        gbc.gridx = 1;
        wrapperPanel.add(txtId, gbc);

        // Password Label
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(UIConstants.BOLD_FONT);
        wrapperPanel.add(passLabel, gbc);

        // Password TextField
        txtPassword = new JPasswordField(15);
        txtPassword.setFont(UIConstants.NORMAL_FONT);
        txtPassword.setPreferredSize(new Dimension(200, 35));
        gbc.gridx = 1;
        wrapperPanel.add(txtPassword, gbc);

        // Buttons Panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnPanel.setBackground(Color.WHITE);

        JButton btnLogin = createButton("Login", UIConstants.PRIMARY_BLUE);
        JButton btnReset = createButton("Reset", Color.GRAY);
        JButton btnExit = createButton("Exit", new Color(220, 53, 69)); // Red

        btnPanel.add(btnLogin);
        btnPanel.add(btnReset);
        btnPanel.add(btnExit);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        wrapperPanel.add(btnPanel, gbc);

        add(wrapperPanel, BorderLayout.CENTER);

        // Action Listeners
        btnLogin.addActionListener(e -> login());
        btnReset.addActionListener(e -> resetForm());
        btnExit.addActionListener(e -> System.exit(0));

        // Keyboard Support (ENTER to login, ESC to exit)
        getRootPane().setDefaultButton(btnLogin);
        wrapperPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exit");
        wrapperPanel.getActionMap().put("exit", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { System.exit(0); }
        });
    }

    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(UIConstants.BOLD_FONT);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        // THESE TWO LINES FIX THE WINDOWS BUTTON RENDER BUG
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(100, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void login() {
        String id = txtId.getText();
        String pass = new String(txtPassword.getPassword());

        if (InputValidator.validateLogin(id, pass)) {
            Student student = studentService.authenticate(id, pass);
            if (student != null) {
                new DashboardFrame(student).setVisible(true);
                this.dispose();
            } else {
                DialogUtils.showError(this, "Invalid Student ID or Password.");
            }
        }
    }

    private void resetForm() {
        txtId.setText("");
        txtPassword.setText("");
    }
}