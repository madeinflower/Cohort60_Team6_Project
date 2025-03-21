package view;

import model.Book;
import model.Role;
import model.User;
import service.MainService;
import utils.MyList;
import java.util.Scanner;


public class Menu {

    private final MainService service; // Основной сервис
    private final Scanner scanner = new Scanner(System.in); // Сканер для ввода данных

    public Menu(MainService service) {
        this.service = service; // Инициализация сервиса
    }

    public void start() {
        showMenu(); // Запуск главного меню
    }

    private void showMenu() {
        while (true) { // Бесконечный цикл для повторного отображения меню
            System.out.println("Добро пожаловать в меню");
            System.out.println("1. Меню книг");
            System.out.println("2. Меню пользователей");
            System.out.println("3. Меню админа");
            System.out.println("0. Выход");

            int choice = scanner.nextInt(); // Читаем выбор пользователя
            scanner.nextLine(); // Очищаем ввод

            if (choice == 0) { // Если выбрана опция выхода
                System.out.println("До свидания!");
                System.exit(0); // Завершаем программу
            }

            showSubMenu(choice); // Вызываем соответствующее подменю
        }
    }

    private void showSubMenu(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Меню книг:");
                //getAllBooks();
                waitRead(); // Ожидание нажатия Enter
                break;
            case 2:
                showUserMenu(); // Открываем меню пользователей
                break;
            case 3:
                User active = service.getActiveUser();
                if (active == null) {

                    System.out.println("Авторизуйтесь в системе");

                    waitRead();

                    break;
                }

                if (active.getRole() != Role.ADMIN) {

                    System.out.println("Доступ только для администраторов!");

                    waitRead();

                    break;

                }

                System.out.println("Здесь будет метод, который выводит меню админа");
                //waitRead();
                break;
            default:
                System.out.println("Сделайте корректный выбор");
                waitRead();
        }
    }

    private void showUserMenu() {
        while (true) {
            System.out.println("Меню пользователя:");
            System.out.println("1. Войти (Login)");
            System.out.println("2. Регистрация нового пользователя");
            System.out.println("3. Выйти из аккаунта (LogOut)");
            System.out.println("0. Вернуться в предыдущее меню");
            System.out.println("\nВыберите пункт меню:");

            int input = scanner.nextInt(); // Читаем ввод пользователя
            scanner.nextLine(); // Очищаем ввод

            if (input == 0) break; // Выход из меню пользователя

            handleUserMenuInput(input); // Обрабатываем ввод пользователя
        }
    }

    private void handleUserMenuInput(int input) {
        switch (input) {
            case 1:
                System.out.println("Вход в систему");
                System.out.println("Введите email:");
                String loginEmail = scanner.nextLine(); // Читаем email
                System.out.println("Введите пароль:");
                String loginPassword = scanner.nextLine(); // Читаем пароль

                boolean loggedIn = service.loginUser(loginEmail, loginPassword); // Проверяем логин
                if (loggedIn) {
                    System.out.println("Вы успешно вошли в систему!");
                } else {
                    System.out.println("Ошибка входа! Проверьте email и пароль.");
                }
                waitRead();
                break;

            case 2:
                System.out.println("Регистрация нового пользователя");
                System.out.println("Введите email:");
                String email = scanner.nextLine(); // Читаем email
                System.out.println("Введите пароль:");
                String password = scanner.nextLine(); // Читаем пароль

                User user = service.registerUser(email, password); // Регистрируем пользователя
                if (user == null) {
                    System.out.println("Регистрация провалена");
                } else {
                    System.out.println("Вы успешно зарегистрировались!");
                }
                waitRead();
                break;

            case 3:
                if (service.getActiveUser()==null) {
                    System.out.println("Нет авторизированных сейчас!");
                    waitRead();
                    break;
                }
                System.out.println("Вы вышли из системы.");
                service.logout(); // Вызываем метод выхода из системы
                waitRead();
                break;

            default:
                System.out.println("Некорректный ввод. Попробуйте снова.");
                waitRead();
        }
    }

    public void logoutUser() {
        System.out.println("Пользователь вышел из системы.");
        // Здесь можно очистить данные о текущем пользователе, если они хранятся
    }

    private void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter...");
        scanner.nextLine(); // Ждем нажатия Enter
    }

    private void showBookList(MyList<Book> books){
        for (Book book: books){

            System.out.printf("%d. %s - %s \n",book.getId(),book.getTitle(),book.getAuthor())
            ;
        }


    }
}