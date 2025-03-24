package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.UserValidation;
import utils.MyList;

import static view.RainbowConsole.prnt;

/*** Author: Roman Romashko Date: 18.03.2025 ***/

public class MainServiceImpl implements MainService {

    private final BookRepository bookRepository; // Хранилище книг
    private final UserRepository userRepository; // Хранилище пользователей

    private User activeUser = null; // По умолчанию текущего пользователя нет

    public MainServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String email, String password) { // Регистрация пользователя

        /*
        1. Валидация еmail / password (если не пройдено возвращаем null)
        2. Проверить уникальность email (что пользователя с таким email еще нет
        3. Создаю нового пользователя и сохраняю его в хранилище данных
        4. Возвращаем созданного пользователя в слой view
        */

        if (!UserValidation.isEmailValid(email)) { // Проверка email
            prnt("\n     Емейл не прошел проверку!",4);
            return null;
        }

        if (!UserValidation.isPasswordValid(password)) { // Проверка пароля
            prnt("\n     Пароль не прошел проверку!",4);
            return null;
        }

        if (userRepository.isEmailExist(email)) { // Проверка уникальности email
            prnt("     Пользователь уже есть, так как email уже существует!",4);
            return null;
        }

        User user = userRepository.addUser(email,password); // Добавление пользователя
        return user;
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
    public void logout() {// Выход пользователя из аккаунта
        activeUser = null; // Очищаем активного пользователя
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
        return bookRepository.getAllBooks(); // Заглушка
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

    public User getActiveUser() {
        return activeUser;  // Получение текущего пользователя
    }

    @Override
    public MyList<User> getAllUsers() {
      return userRepository.getAllUsers();
    }

    @Override
    public MyList<Book> searchByTitle(String title) {
        return null;
    }

    @Override
    public MyList<Book> searchByAuthor(String author) {
        return null;
    }

    @Override
    public MyList<Book> searchByTitleOrAuthor(String titleSearch) {
        return null;
    }
}