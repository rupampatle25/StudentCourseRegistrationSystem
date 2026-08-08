package app;

import ui.LoginFrame;
import utils.AppTheme;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Run GUI construction on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            AppTheme.setupTheme();
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}