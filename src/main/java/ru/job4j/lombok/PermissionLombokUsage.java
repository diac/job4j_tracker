package ru.job4j.lombok;

import java.util.List;

public class PermissionLombokUsage {

    public static void main(String[] args) {
        Permission permission = Permission.of()
                .id(1)
                .name("editor")
                .rules(List.of("Edit post", "Submit for review"))
                .build();
        System.out.println(permission);
    }
}