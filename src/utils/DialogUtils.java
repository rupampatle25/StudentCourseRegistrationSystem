package utils;

import javax.swing.*;
import java.awt.*;

public class DialogUtils {
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean showConfirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Confirmation", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
}