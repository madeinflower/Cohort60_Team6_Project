package model;

public class Book {

    private String title;
    private String author;
    private boolean isTaken;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isTaken = false;
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

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isTaken=" + isTaken +
                '}';
    }

}
