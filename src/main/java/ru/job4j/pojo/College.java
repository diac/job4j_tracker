package ru.job4j.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class College {

    public static void main(String[] args) {
        Student student = new Student();
        student.setLastName("Иванов");
        student.setFirstName("Иван");
        student.setPatronymic("Иванович");
        student.setGroup("МОиАИС-143");
        student.setEnrollmentDate(LocalDate.now());
        System.out.println(
                student.getLastName() + " "
                        + student.getFirstName() + "  "
                        + student.getPatronymic() + ", "
                        + student.getGroup() + ", "
                        + student.getEnrollmentDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        );
    }
}
