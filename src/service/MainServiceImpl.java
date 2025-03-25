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
            System.out.println("\n    Емейл не прошел проверку!");
            return null;
        }

        if (!UserValidation.isPasswordValid(password)) { // Проверка пароля
            System.out.println("\n    Пароль не прошел проверку!");
            return null;
        }

        if (userRepository.isEmailExist(email)) { // Проверка уникальности email
            System.out.println("\n    Пользователь уже есть, так как email уже существует!");
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
    public void addBook(String title, String author) {
        bookRepository.addBook(title, author);
    }

    @Override
    public MyList<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        return null; // Заглушка
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        return bookRepository.getAvailableBooks();
    }

    @Override
    public MyList<Book> getTakenBooks() {
       return bookRepository.getTakenBooks();
    }

    @Override
    public void deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public void takeBook(int id) {
        bookRepository.takeBook(id);

    }

    @Override
    public void returnBook(int id) {
        bookRepository.returnBook(id);
    }

    @Override
    public void editBook(int id, String newTitle, String newAuthor) {
        bookRepository.editBook(id, newTitle, newAuthor);
    }

    @Override
    public void logoutUser() {
        // Заглушка
    }

    public User getActiveUser() {
        return activeUser; // Получение текущего пользователя
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
    public MyList<Book> searchByTitleOrAuthor(String query) {
        return bookRepository.searchByTitleOrAuthor(query);
    }

    public User getUserByEmail(String email) { // получение пользователя по емейл
        if (email == null || email.trim().isEmpty()) { // проверка на пустую строку и нул
            return null;
        }
        return userRepository.getUserByEmail(email);
    }

    @Override
    public boolean deleteUser(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        if (!user.getUserBooks().isEmpty()) {
            return false;
        }
        return userRepository.deleteUser(email);
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        // Проверяем, что вообще за пароль
        if (!UserValidation.isPasswordValid(newPassword)) {
            return false; // пароль не прошел валидацию
        }
        return userRepository.updatePassword(email, newPassword); // обновление пароля
    }
    @Override
    public MyList<Book> getSortedBooks(String sortField) {
        return bookRepository.getSortedBooks(sortField);
    }
}