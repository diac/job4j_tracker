package ru.job4j.pojo;

public class Book {

    private String name;
    private int pagesNumber;

    public Book() {
    }

    public Book(String name, int pagesNumber) {
        this.name = name;
        this.pagesNumber = pagesNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }
}
