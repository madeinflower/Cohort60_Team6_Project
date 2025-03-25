package tests;

import model.Book;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainService;
import service.MainServiceImpl;
import utils.MyList;

import static org.junit.jupiter.api.Assertions.*;


public class MainServiceImplTest {

    private MainService service;

    @BeforeEach
    void setUp() {

    // Создаем все необходимые репозитории для книг, пользователей и запускаем сервис для его тестирования
        BookRepository bookRepo = new BookRepositoryImpl();
        UserRepository userRepo = new UserRepositoryImpl();

    // Запускаем сервис
        service = new MainServiceImpl(bookRepo,userRepo);

    }

    @Test
    public void registerUser() {

    // Регистрируем пользователя с тестовым емейлом и паролем
        User user = service.registerUser("test@uuu.com", "Password1!");
        assertNotNull(user, "Пользователь зарегистрирован!");

        //User user2 = service.registerUser("3333@uuu.com", "Password1!");
       // assertNull(user2, "Пользователь не зарегистрирован!");

    }

    @Test
    public void loginUser() {
    }

    @Test
    public void logout() {
    }

    @Test
    public void addBook() {

        // Получаем количество книг
        int num1 = service.getAllBooks().size();

        // Добавляем книгу
        service.addBook("Title", "Author");

        // Получаем количество книг
        int num2 = service.getAllBooks().size();

        // Проверяем, что новый размер увеличился на 1
        assertEquals(num1 + 1, num2, "Количество книг должно увеличиться на 1");

    }

    @Test
    public void getAllBooks() {

        // Получаем список книг
        MyList<Book> books = service.getAllBooks();

        // Проверяем, что не null
        assertNotNull(books, "Список книг не должен быть null");

    }

    @Test
    public void getBooksByTitle() {
    }

    @Test
    public void getAvailableBooks() {
    }

    @Test
    public void getTakenBooks() {
    }

    @Test
    public void deleteBook() {
    }

    @Test
    public void takeBook() {
    }

    @Test
    public void returnBook() {
    }

    @Test
    public void editBook() {

        // Редактируем книгу
        service.editBook(1, "New Title", "New Author");

        // Получаем список книг
        MyList<Book> books = service.getAllBooks();

        Book edited = null; // создаем переменную для сравнения

        for (Book book : books){

            if (book.getId() == 1){

                edited = book; // передаем книгу
                break;
            }

        }

        // Если теперь не null - значит книга с номером 1 найдена
        assertNotNull(edited, "Найдена ли книга с id 1");

        // Проверяем нового автора
        assertEquals("New Title", edited.getTitle());

        // Проверяем новое название
        assertEquals("New Author", edited.getAuthor());

    }

    @Test
    public void logoutUser() {
    }

    @Test
    public void getActiveUser() {
    }

    @Test
    public void getAllUsers() {
    }

    @Test
    public void searchByTitle() {
    }

    @Test
    public void searchByAuthor() {
    }

    @Test
    public void searchByTitleOrAuthor() {
    }

    @Test
    public void getUserByEmail() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void updatePassword() {
    }

    @Test
    public void getSortedBooks() {
    }
}