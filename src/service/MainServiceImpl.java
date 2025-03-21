package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.UserValidation;
import utils.MyList;

/*** Author: Roman Romashko Date: 18.03.2025 ***/

public class MainServiceImpl implements MainService {

    private final BookRepository bookRepository; // Хранилище книг
    private final UserRepository userRepository; // Хранилище пользователей

    private User activeUser; // Текущий авторизованный пользователь

    public MainServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String email, String password) { // Регистрация пользователя

        if (!UserValidation.isEmailValid(email)) { // Проверка email
            System.out.println("Email не прошел проверку!");
            return null;
        }

        if (!UserValidation.isPasswordValid(password)) { // Проверка пароля
            System.out.println("Пароль не прошел проверку!");
            return null;
        }

        if (userRepository.isEmailExist(email)) { // Проверка уникальности email
            System.out.println("Пользователь уже есть, так как email уже существует!");
            return null;
        }

        return userRepository.addUser(email, password); // Добавление пользователя
    }

    @Override
    public boolean loginUser(String email, String password) { // Авторизация пользователя

        User user = userRepository.getUserByEmail(email); // Получение пользователя
        if (user == null) return false; // Если пользователя нет — false

        if (user.getPassword().equals(password)) { // Проверка пароля
            activeUser = user; // Установка активного пользователя
            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        activeUser = null; // Выход пользователя
    }

    @Override
    public boolean takeBook(int bookId) {
        return false; // Заглушка
    }

    @Override
    public Book addBook(String title, String author) {
        return null; // Заглушка
    }

    @Override
    public MyList<Book> getAllBooks() {
        return null; // Заглушка
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        return null; // Заглушка
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        return null; // Заглушка
    }

    @Override
    public void deleteBook(int bookId) {
        // Заглушка
    }

    @Override
    public void logoutUser() {
        // Заглушка
    }

    public User getActiveUser() {
        return activeUser; // Получение текущего пользователя
    }
}