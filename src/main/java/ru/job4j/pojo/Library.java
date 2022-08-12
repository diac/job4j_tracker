package ru.job4j.pojo;

public class Library {

    public static void main(String[] args) {
        Book[] books = new Book[4];
        books[0] = new Book("Design Patterns and Refactoring", 800);
        books[1] = new Book("Clean Code", 400);
        books[2] = new Book("Database Design", 1200);
        books[3] = new Book("1001 Dumpling Recipe", 1001);
        System.out.println("All books: ");
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName() + " (" + books[i].getPagesNumber() + ")");
        }
        System.out.println();
        System.out.println("Swap books 0 and 3");
        Book tempBook = books[3];
        books[3] = books[0];
        books[0] = tempBook;
        System.out.println("books[0]: " + books[0].getName() + " (" + books[0].getPagesNumber() + ")");
        System.out.println("books[3]: " + books[3].getName() + " (" + books[3].getPagesNumber() + ")");
        System.out.println();
        System.out.println("Find \"Clean Code\"");
        for (int i = 0; i < books.length; i++) {
            if (books[i].getName().equals("Clean Code")) {
                System.out.println(books[i].getName() + " (" + books[i].getPagesNumber() + ")");
            }
        }
    }
}
