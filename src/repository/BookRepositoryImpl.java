package repository;

import model.User;
import model.Book;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

import static view.RainbowConsole.prnt;


public class BookRepositoryImpl implements BookRepository { // Класс-репозиторий для хранения книг

    private final MyList<Book> books; // Список книг в хранилище
    private final AtomicInteger currentId = new AtomicInteger(1); // Генератор уникальных ID

    public BookRepositoryImpl() { // Конструктор
        this.books = new MyArrayList<>();
        addStartBooks(); // Добавление стартовых книг
    }

    private void addStartBooks() { // Метод для добавления стартового набора книг
        books.addAll(

                new Book(currentId.getAndIncrement(), "1984", "Джордж Оруэлл"),
                new Book(currentId.getAndIncrement(), "1985", "Джордж Оруэлл"),
                new Book(currentId.getAndIncrement(), "1985", "Джордж Оруэлл"),
                new Book(currentId.getAndIncrement(), "1986", "Джордж Оруэлл"),
                new Book(currentId.getAndIncrement(), "1987", "Джордж Оруэлл"),
                new Book(currentId.getAndIncrement(), "1988", "Джордж Оруэлл"),
                new Book(currentId.getAndIncrement(), "1989", "Джордж Оруэлл"));


    }

    @Override
    public void addBook(String title, String author) { // Добавление новой книги
        Book book = new Book(currentId.getAndIncrement(), title, author);
        books.add(book);
    }

    // возвращает все книги
    @Override
    public void getAllBooks() {
        for (Book book : books) {
            if (books.isEmpty()) {
                prnt("Книг пока нет.", 3);
            } else {
                System.out.println("    " + book);
            }
        }

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
            if (takenBooks.isEmpty()) {
                prnt("Книг пока нет.", 3);
                break;
            }else if (book.isTaken()) {
                takenBooks.add(book);
            }
        }
        return takenBooks;
    }

    // помечает книгу как взятую, если она доступна
    @Override
    public void takeBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) book.setTaken(true);
        }
    }

    // помечает книгу как возвращенную, если она была взята
    @Override
    public void returnBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) book.setTaken(false);
        }
    }


    @Override
    public void editBook(int id, String newTitle, String newAuthor) { // Сохранение книги (можно расширить логику)
        for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);

            }
        }
    }

    @Override
    public void deleteById(int id) { // Удаление книги по ID (можно реализовать)
        for (Book book : books) {
            if (book.getId() == id) books.remove(book);
        }
    }
}