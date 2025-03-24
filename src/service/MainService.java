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

    Book addBook(String title, String author); // Добавить новую книгу

    MyList<Book> getAllBooks(); // Получить список всех книг

    MyList<Book> getBooksByTitle(String title); // Найти книги по названию

    MyList<Book> getAvailableBooks(); // Получить список доступных книг

    void deleteBook(int bookId); // Удалить книгу по её ID

    void takeBook(int id); // Взять книгу

    void returnBook(int id); // Отдать книгу

    void editBook(int id, String newTitle, String newAuthor); // Редактировать книгу

    //void logoutUser(); // Разлогинивание пользователя

    void logoutUser();

    User getActiveUser(); // Получить текущего авторизованного пользователя

    MyList<User> getAllUsers(); // Вывод всех пользователей через слой сервис
}