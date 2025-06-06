package repository;

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

                new Book(currentId.getAndIncrement(), "Чистый код. Создание, анализ и рефакторинг", "Роберт Мартин"),
                new Book(currentId.getAndIncrement(), "Совершенный программист. Путь к мастерству", "Эндрю Хант, Дэвид Томас"),
                new Book(currentId.getAndIncrement(), "Паттерны проектирования. Решения для повторяющихся проблем", "Эрих Гамма, Ричард Хелм"),
                new Book(currentId.getAndIncrement(), "Python. Краткий курс", "Эрик Мэттес"),
                new Book(currentId.getAndIncrement(), "Вы не знаете JS", "Кайл Симпсон"),
                new Book(currentId.getAndIncrement(), "Эффективный JavaScript", "Дэвид Херман"),
                new Book(currentId.getAndIncrement(), "Fullstack React", "Энтони Аккомадзо, Натан Мюррей"),
                new Book(currentId.getAndIncrement(), "Node.js. Шаблоны проектирования", "Марио Каччаро"),
                new Book(currentId.getAndIncrement(), "Изучаем React", "Алекс Бэнкс, Ив Порцелло"),
                new Book(currentId.getAndIncrement(), "Python. Краткий курс", "Эрик Мэттес"),
                new Book(currentId.getAndIncrement(), "Head First. Паттерны проектирования", "Эрик Фриман, Элизабет Робсон"));

    }

    @Override
    public void addBook(String title, String author) { // Добавление новой книги
        Book book = new Book(currentId.getAndIncrement(), title, author);
        books.add(book);
    }

    // возвращает все книги
    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    // поиск по названию
    public MyList<Book> searchByTitle(String title) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // поиск по автору
    public MyList<Book> searchByAuthor(String author) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
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

    @Override
    public MyList<Book> getSortedBooks(String sortField) {

        MyList<Book> allBooks = getAllBooks(); // Получаем все книги
        if (allBooks == null || allBooks.isEmpty()) {
            return allBooks;
        }

        // Преобразуем список в массив
        Book[] booksArray = allBooks.toArray();

        // Простейшая сортировка выбором по выбранному полю
        for (int i = 0; i < booksArray.length - 1; i++) {
            for (int j = i + 1; j < booksArray.length; j++) {
                String fieldI = "";
                String fieldJ = "";
                if ("author".equalsIgnoreCase(sortField)) {
                    fieldI = booksArray[i].getAuthor().toLowerCase(); // сравниваем строки по автору
                    fieldJ = booksArray[j].getAuthor().toLowerCase();  // сравниваем строки по автору
                } else if ("title".equalsIgnoreCase(sortField)) {
                    fieldI = booksArray[i].getTitle().toLowerCase();  // сравниваем строки по названию
                    fieldJ = booksArray[j].getTitle().toLowerCase(); // сравниваем строки по названию
                }
                if (fieldI.compareTo(fieldJ) > 0) { // если больше нуля, значит fieldI идет лексикографически позже fieldJ
                    Book temp = booksArray[i]; // сохраняем по временный объект
                    booksArray[i] = booksArray[j];
                    booksArray[j] = temp;
                }
            }
        }
        // Собираем отсортированный массив обратно в MyList<Book>
        MyList<Book> sortedBooks = new MyArrayList<>();
        for (Book book : booksArray) {
            sortedBooks.add(book);
        }
        return sortedBooks;
    }

}