package tests;

import model.Book;
import org.junit.Before;
import org.junit.Test;
import repository.BookRepositoryImpl;
import utils.MyList;

import static org.junit.Assert.*;

public class BookRepositoryImplTest {

    private BookRepositoryImpl bookRepository;

    @Before
    public void setUp() {
        bookRepository = new BookRepositoryImpl();
    }

    @Test
    public void addBook() {
        int initialSize = bookRepository.getAllBooks().size();
        bookRepository.addBook("New book", "Some Author");
        assertEquals(initialSize + 1, bookRepository.getAllBooks().size());
    }

    @Test
    public void testGetAllBooks() {
        MyList<Book> books = bookRepository.getAllBooks();
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test
    public void testSearchByTitle() {
        MyList<Book> result = bookRepository.searchByTitle("Чистый код");
        assertFalse(result.isEmpty());
        assertEquals("Чистый код. Создание, анализ и рефакторинг", result.get(0).getTitle());
    }

    @Test
    public void testSearchByAuthor() {
        MyList<Book> result = bookRepository.searchByAuthor("Роберт Мартин");
        assertFalse(result.isEmpty());
        assertEquals("Чистый код. Создание, анализ и рефакторинг", result.get(0).getTitle());
    }

    @Test
    public void testSearchByTitleOrAuthor() {
        MyList<Book> result = bookRepository.searchByTitleOrAuthor("Паттерны");
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetAvailableBooks() {
        MyList<Book> availableBooks = bookRepository.getAvailableBooks();
        assertNotNull(availableBooks);
    }

    @Test
    public void testTakeBook() {
        Book book = bookRepository.getAllBooks().get(0);
        bookRepository.takeBook(book.getId());
        assertTrue(book.isTaken());
    }

    @Test
    public void testReturnBook() {
        Book book = bookRepository.getAllBooks().get(0);
        bookRepository.takeBook(book.getId());
        bookRepository.returnBook(book.getId());
        assertFalse(book.isTaken());
    }

    @Test
    public void testEditBook() {
        Book book = bookRepository.getAllBooks().get(0);
        bookRepository.editBook(book.getId(), "Новое название", "Новый автор");
        assertEquals("Новое название", book.getTitle());
        assertEquals("Новый автор", book.getAuthor());
    }

    @Test
    public void testDeleteById() {
        Book book = bookRepository.getAllBooks().get(0);
        int initialSize = bookRepository.getAllBooks().size();
        bookRepository.deleteById(book.getId());
        assertEquals(initialSize - 1, bookRepository.getAllBooks().size());
    }

    @Test
    public void testGetSortedBooksByTitle() {
        MyList<Book> sortedBooks = bookRepository.getSortedBooks("title");
        assertTrue(sortedBooks.get(0).getTitle().compareTo(sortedBooks.get(1).getTitle()) <= 0);
    }

    @Test
    public void testGetSortedBooksByAuthor() {
        MyList<Book> sortedBooks = bookRepository.getSortedBooks("author");
        assertTrue(sortedBooks.get(0).getAuthor().compareTo(sortedBooks.get(1).getAuthor()) <= 0);
    }
}