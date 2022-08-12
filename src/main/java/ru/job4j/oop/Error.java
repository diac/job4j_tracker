package ru.job4j.oop;

public class Error {

    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Is active: " + active);
        System.out.println("Status: " + status);
        System.out.println("Message: " + message);
    }

    public static void main(String[] args) {
        Error unknownError = new Error();
        Error error404 = new Error(true, 404, "Not found");
        Error error403 = new Error(true, 403, "Forbidden");
        Error error400 = new Error(true, 400, "Bad Request");
        unknownError.printInfo();
        error404.printInfo();
        System.out.println("===");
        error403.printInfo();
        System.out.println("===");
        error400.printInfo();
    }
}
