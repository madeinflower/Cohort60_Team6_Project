package repository;

public interface BookRepository {

    Book addBook(String title, String author);

    MyList<Book> getAllBooks();
    MyList<Book> searchByTitleOrAuthor(String query);
    MyList<Book> getAvailableBooks();
    MyList<Book> getTakenBooks();

    void takeBook(Book book);
    void returnBook(Book book);
}
