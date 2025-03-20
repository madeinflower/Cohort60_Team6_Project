package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.UserValidation;
import utils.MyList;

/*** Author: Roman Romashko Date: 18.03.2025 ***/

public class MainServiceImpl implements MainService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private User activeUser;

    public MainServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String email, String password) {

        /*
        1. Валидация email / password (если не пройдено — возвращаем null)
        2. Проверить уникальность email (что пользователя с таким email еще нет)
        3. Создать нового пользователя и сохранить его в хранилище данных
        4. Вернуть созданного пользователя в слой view
        */

        if (!UserValidation.isEmailValid(email)) {
            System.out.println("Емейл не прошел проверку!");
            return null;
        }

        if (!UserValidation.isPasswordValid(password)) {
            System.out.println("Пароль не прошел проверку!");
            return null;
        }

        if (userRepository.isEmailExist(email)) {
            System.out.println("Пользователь уже есть, так как email уже существует!");
            return null;
        }

        return userRepository.addUser(email, password);
    }

    @Override
    public boolean loginUser(String email, String password) {

        /*
        1. Получить из хранилища пользователя с таким email
        2. Если пользователя нет — вернуть false
        3. Проверить, совпадает ли у пользователя пароль из БД с переданным паролем
        4. Если совпадает — пользователь залогинился
        */

        User user = userRepository.getUserByEmail(email);
        if (user == null) return false;

        if (user.getPassword().equals(password)) {
            activeUser = user;
            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        activeUser = null;
    }

    @Override
    public boolean takeBook(int bookId) {
        return false;
    }

    @Override
    public Book addBook(String title, String author) {
        return null;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return null;
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        return null;
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        return null;
    }

    @Override
    public void deleteBook(int bookId) {
    }

    @Override
    public void logoutUser() {
    }

    public User getActiveUser() {
        return activeUser;
    }
}