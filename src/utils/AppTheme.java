package utils;

import javax.swing.*;
import java.awt.*;

public class AppTheme {
    public static boolean isDarkMode = false;

    public static void setupTheme() {
        try {
            // Using Cross Platform L&F ensures colors apply perfectly everywhere
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateColors();
    }

    public static void toggleDarkMode(JFrame frame) {
        isDarkMode = !isDarkMode;
        updateColors();
        SwingUtilities.updateComponentTreeUI(frame);
    }

    private static void updateColors() {
        if(isDarkMode) {
            UIManager.put("Panel.background", new Color(45, 45, 48));
            UIManager.put("Label.foreground", Color.WHITE);
            UIManager.put("Table.background", new Color(60, 63, 65));
            UIManager.put("Table.foreground", Color.WHITE);
            UIManager.put("OptionPane.background", new Color(45, 45, 48));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
        } else {
            UIManager.put("Panel.background", Color.WHITE);
            UIManager.put("Label.foreground", Color.BLACK);
            UIManager.put("Table.background", Color.WHITE);
            UIManager.put("Table.foreground", Color.BLACK);
            UIManager.put("OptionPane.background", Color.WHITE);
            UIManager.put("OptionPane.messageForeground", Color.BLACK);
        }
    }
}