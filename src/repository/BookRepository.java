package repository;

import model.Book;
import utils.MyList;

public interface BookRepository {

    Book addBook(String title, String author);

    MyList<Book> getAllBooks();
    MyList<Book> searchByTitle(String query);
    MyList<Book> searchByAuthor(String query);
    MyList<Book> searchByTitleOrAuthor(String query);
    MyList<Book> getAvailableBooks();
    MyList<Book> getTakenBooks();

    void takeBook(Book book);
    void returnBook(Book book);
    void deleteById(int id);
    void saveBook(Book book);


}
