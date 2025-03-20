package repository;

public class BookImpl implements BookRepository {
    private final MyList<Book> books = new MyList<>();

    // добавляет книги в список
    @Override
    public Book addBook(String title, String author) {
        Book book = new Book(title, author);
        books.add(book);
        return book;
    }

    // возвращает все книги
    @Override
    public MyList<Book> getAllBooks() {
        return new MyList<>(books);
    }

    // поиск по названию или автору
    @Override
    public MyList<Book> searchByTitleOrAuthor(String query) {
        MyList<Book> result = new MyList<>();
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
        MyList<Book> availableBooks = new MyList<>();
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
        MyList<Book> takenBooks = new MyList<>();
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

