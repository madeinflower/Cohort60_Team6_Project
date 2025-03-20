package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;

/*** Author: Roman Romashko Date: 17.03.2025 ***/

public interface MainService {

    User registerUser(String email, String password);

    boolean loginUser(String email, String password);

    void logout();

    boolean takeBook(int bookId);

    Book addBook(String title, String author);

    MyList<Book> getAllBooks();

    MyList<Book> getBooksByTitle(String title);

    MyList<Book> getAvailableBooks();

    void deleteBook(int bookId);

    void logoutUser();

    User getActiveUser();
}
