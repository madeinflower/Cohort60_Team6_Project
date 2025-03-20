package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;

/*** Author: Roman Romashko Date: 17.03.2025 ***/

public interface MainService {

    User registerUser(String email, String password); // Регистрация нового пользователя

    boolean loginUser(String email, String password); // Авторизация пользователя

    void logout(); // Выход пользователя из системы

    boolean takeBook(int bookId); // Взять книгу по её ID

    Book addBook(String title, String author); // Добавить новую книгу

    MyList<Book> getAllBooks(); // Получить список всех книг

    MyList<Book> getBooksByTitle(String title); // Найти книги по названию

    MyList<Book> getAvailableBooks(); // Получить список доступных книг

    void deleteBook(int bookId); // Удалить книгу по её ID

    void logoutUser(); // Разлогинивание пользователя

    User getActiveUser(); // Получить текущего авторизованного пользователя
}