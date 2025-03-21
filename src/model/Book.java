package model;

import java.util.Objects;

public class Book {
    private final int id;
    private String title;
    private String author;
    private boolean isTaken;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isTaken = false;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return id == book.id && isTaken == book.isTaken && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isTaken);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isTaken=" + isTaken +
                '}';
    }
}
