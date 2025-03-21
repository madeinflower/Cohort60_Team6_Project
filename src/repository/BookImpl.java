package repository;

import model.Book;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class BookImpl implements BookRepository {
    private final MyList<Book> books = new MyArrayList<>( );

    private final AtomicInteger currenId = new AtomicInteger(1);

    // добавляет книги в список
    @Override
    public Book addBook(int regNr, String title, String author) {
        Book book = new Book(regNr, title, author);
        books.add(book);
        return book;
    }

    @Override
    public void deleteBook(int bookId) {
        books.remove(bookId);
    }

    // возвращает все книги
    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    public void addStartBooks() {
        books.addAll(
                new Book(currenId.getAndIncrement(), "1","2")
        );
    }

    // поиск по названию или автору
    @Override
    public MyList<Book> searchByTitleOrAuthor(String query) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // возвращает книги, которые не взяты
    @Override
    public MyList<Book> getAvailableBooks() {
        MyList<Book> availableBooks = new MyArrayList<>();
        for (Book book : books) {
            if (!book.isTaken()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    // возвращает книги, которые уже взяты.
    @Override
    public MyList<Book> getTakenBooks() {
        MyList<Book> takenBooks = new MyArrayList<>();
        for (Book book : books) {
            if (book.isTaken()) {
                takenBooks.add(book);
            }
        }
        return takenBooks;
    }

    // помечает книгу как взятую, если она доступна
    @Override
    public void takeBook(Book book) {
        if (books.contains(book) && !book.isTaken()) {
            book.setTaken(true);
        }
    }

    // помечает книгу как возвращенную, если она была взята
    @Override
    public void returnBook(Book book) {
        if (books.contains(book) && book.isTaken()) {
            book.setTaken(false);
        }
    }
}

