package ru.job4j.early;

public class PasswordValidator {

    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Пароль не может быть null!");
        }
        if (password.length() < 8 || password.length() > 32) {
            return "Длина пароля должна находиться в диапазоне [8, 32]";
        }
        if (!stringHasUpperCaseChars(password)) {
            return "Пароль должен содержать хотя бы один символ в верхнем регистре";
        }
        if (!stringHasLowerCaseChars(password)) {
            return "Пароль должен содержать хотя бы один символ в нижнем регистре";
        }
        if (!stringHasDigits(password)) {
            return "Пароль должен содержать хотя бы одну цифру";
        }
        if (!stringHasSpecialChars(password)) {
            return "Пароль должен содержать хотя бы один специальный символ";
        }
        if (stringHasBanWords(password)) {
            return "Пароль не должен содержать запрещенных/небезопасных последовательностей символов";
        }
        return password;
    }

    private static boolean stringHasUpperCaseChars(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean stringHasLowerCaseChars(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean stringHasDigits(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean stringHasSpecialChars(String s) {
        String specialChars = "!@#$%^&*()_+-=`~[]{};:'\"|\\,.<>/?";
        for (int i = 0; i < s.length(); i++) {
            if (specialChars.contains(String.valueOf(s.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    private static boolean stringHasBanWords(String s) {
        String[] banWords = {
                "qwerty",
                "12345",
                "password",
                "admin",
                "user"
        };
        for (String banWord : banWords) {
            if (s.toLowerCase().contains(banWord)) {
                return true;
            }
        }
        return false;
    }
}
