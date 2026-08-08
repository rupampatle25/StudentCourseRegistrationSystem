package utils;

public class InputValidator {
    public static boolean validateLogin(String id, String password) {
        if (id == null || id.trim().isEmpty()) {
            DialogUtils.showError(null, "Student ID cannot be empty.");
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            DialogUtils.showError(null, "Password cannot be empty.");
            return false;
        }
        return true;
    }
}