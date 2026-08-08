package ui;

import model.Student;
import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {

    public ProfilePanel(Student student) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("Student Profile Overview");
        title.setFont(UIConstants.TITLE_FONT);
        title.setForeground(UIConstants.PRIMARY_BLUE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;

        gbc.gridy = 1; gbc.gridx = 0;
        add(createLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        add(createValueLabel(student.getStudentId()), gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        add(createLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        add(createValueLabel(student.getName()), gbc);

        gbc.gridy = 3; gbc.gridx = 0;
        add(createLabel("Email Address:"), gbc);
        gbc.gridx = 1;
        add(createValueLabel(student.getEmail()), gbc);

        gbc.gridy = 4; gbc.gridx = 0;
        add(createLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        add(createValueLabel(student.getPhone()), gbc);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UIConstants.BOLD_FONT);
        return label;
    }

    private JLabel createValueLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UIConstants.NORMAL_FONT);
        return label;
    }
}