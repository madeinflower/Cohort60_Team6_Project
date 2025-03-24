package view;

import model.Book;
import model.Role;
import model.User;
import service.MainService;
import utils.MyList;

import java.util.Scanner;

import static view.RainbowConsole.*;

public class Menu {

    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(MainService service) {
        this.service = service;
    }

    public void start() {
        while (true) {
            User activeUser = service.getActiveUser(); // получаем активного пользователя
            Role role = (activeUser != null) ? activeUser.getRole() : Role.GUEST; // определяем его роль
            showMenu(role, activeUser);  // передаем role и activeUser

            // проверка строки ввода на целое число, защита от ошибки
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера ввода
                handleMenuChoice(choice, role);
            } else {
                // Если ввели не число, выводим предупреждение и очищаем ввод
                prnt("\n   [!] Некорректный ввод. Пожалуйста, введите число из списка меню!", 4);
                scanner.nextLine();
                waitRead();
            }
        }
    }

    private void showMenu(Role role, User activeUser) {
        prnt("== 📚 Библиотека \"Знания Века\" ===", 1);
        prnt("1. Все книги", 0);
        prnt("2. Доступные книги", 0);
        prnt("3. Книги по автору", 0);
        prnt("4. Книги по названию", 0);
        prnt("5. Поиск по названию", 0);
        prnt("6. Поиск по автору", 0);

        if (role == Role.USER || role == Role.ADMIN) {
            prnt("-----------------------------", 1);
            prnt("7. Взять книгу", 0);
            prnt("8. Вернуть книгу", 0);
            prnt("9. " + ACCENT + "❤" + RESET + " Мои книги", 0);
        }

        if (role == Role.ADMIN) {
            prnt("--------------------------------", 1);
            prnt("10. Добавить книгу", 1);
            prnt("11. Редактировать книгу", 1);
            prnt("12. Удалить книгу", 1);
            prnt("13. Книги на руках", 1);
            prnt("14. Управление пользователями", 2);
        }

        if (role == Role.GUEST) {
            prnt("15. Регистрация", 2);
            prnt("16. Авторизация", 2);
        }

        if (role == Role.USER || role == Role.ADMIN) {
            String username = activeUser.getEmail();
            String roleName = activeUser.getRole().name();
            prnt("--------------------------------", 1);
            prnt("17. Выйти (" + username + ")", 3);
        }

        prnt("0. Exit", 3);
        prnt("==============================", 1);
        System.out.print("  👉 Выберите пункт меню: ");
    }

    private void handleMenuChoice(int choice, Role role) {
        if (choice == 1) {
            /*
            1. Получаем список всех книг
            2. Если книг нет - выводим сообщение - список книг пуст.
            3. Если книги есть - выводим список книг
            4. Ставим задержку - даем просмотреть список
            */
            MyList<Book> books = service.getAllBooks();
            System.out.println();
            if (books.isEmpty()) {
                prnt("Книг пока нет.", 3);
            } else {
                for (Book book : books) {
                    System.out.println("   " + book);
                }
            }
            waitRead();

        } else if (choice == 2) {
            service.getAvailableBooks();
        } else if (choice == 3) {
            prnt("Сортировка по автору пока не реализована.", 3);
            waitRead();
        } else if (choice == 4) {
            prnt("Сортировка по названию пока не реализована.", 3);
            waitRead();
        } else if (choice == 5) {
            // service.getBooksByTitle();
        } else if (choice == 6) {
            // service.getBooksByAuthor();
        } else if (choice == 7 && (role == Role.USER || role == Role.ADMIN)) {
            // service.rentBook();
        } else if (choice == 8 && (role == Role.USER || role == Role.ADMIN)) {
            // service.returnBook();
        } else if (choice == 9 && (role == Role.USER || role == Role.ADMIN)) {
            // service.showUserBooks();
        } else if (choice == 10 && role == Role.ADMIN) {
            // service.addBook();
        } else if (choice == 11 && role == Role.ADMIN) {
            // service.editBook();
        } else if (choice == 12 && role == Role.ADMIN) {
            // service.removeBook();
        } else if (choice == 13 && role == Role.ADMIN) {
            // service.showBookHolders();
        } else if (choice == 14 && role == Role.ADMIN) {
            showUserManagementMenu();
        } else if (choice == 15) {
            registerUser();
        } else if (choice == 16) {
            loginUser();
        } else if (choice == 17 && (role == Role.USER || role == Role.ADMIN)) {
            service.logout();
            prnt("\n   Вы вышли из аккаунта!", 2);
            waitRead();
        } else if (choice == 0) {
            prnt("\n   До свидания! Приходите еще!", 2);
            System.exit(0);
        } else {
            prnt("\n  Некорректный ввод. Попробуйте снова.", 4);
            waitRead();
        }
    }

    private void showUserManagementMenu() {
        while (true) {
            prnt("=== Управление пользователями ===", 1);
            prnt("1. Просмотреть список пользователей", 2);
            prnt("2. Найти пользователя по имени", 2);
            prnt("3. Добавить пользователя", 2);
            prnt("4. Изменить данные пользователя", 2);
            prnt("5. Удалить пользователя", 2);
            prnt("0. Назад", 3);
            prnt("==============================", 1);
            System.out.print("  👉 Выберите пункт: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера ввода

            if (choice == 1) {
                /*
                1. Получаем список всех пользователей
                2. Если пользователей нет - выводим сообщение - список пуст
                3. Если есть - выводим список
                4. Ставим паузу
                */
                MyList<User> users = service.getAllUsers();

                if (users.isEmpty()) {
                    prnt("Пользователей пока нет.", 3);
                } else {
                    for (User user : users) {
                        System.out.println("   " + user);
                    }
                }
                waitRead();

            } else if (choice == 0) {
                break;
            } else {
                prnt("\n  Некорректный ввод. Попробуйте снова.", 4);
                waitRead();
            }
        }
    }

    private void waitRead() {
        System.out.println("\n   Для продолжения нажмите Enter...");
        scanner.nextLine(); // Ждем нажатия Enter
    }

    private void loginUser() {
        prnt("\n   = Авторизация пользователя ===", 1);
        System.out.print("     Введите email: ");
        String email = scanner.nextLine();

        System.out.print("     Введите пароль: ");
        String password = scanner.nextLine();

        boolean loggedIn = service.loginUser(email, password);

        if (loggedIn) {
            prnt("\n   [+] Добро пожаловать, "+ email + "!", 2);
            waitRead();
        } else {
            prnt("\n  [!] Ошибка авторизации! Неверный email или пароль.", 4);
            waitRead();
        }
    }

    private void registerUser() {

        // Регистрация
        /*
         1. Запросить у пользователя email и пароль
         2. Передать полученные данные в СЕРВИСНЫЙ слой
         3. Получить ответ от сервисного слоя -
         4. Сообщить результат пользователю
         */

        prnt("\n   = Регистрация пользователя ===", 1);
        System.out.print("     Введите email: ");
        String email = scanner.nextLine();

        System.out.print("     Введите пароль: ");
        String password = scanner.nextLine();

        User user = service.registerUser(email, password);

        if (user == null) {
            prnt("  [!] Регистрация провалена", 4);
        } else {
            prnt("\n   [+] Вы успешно зарегистрировались в системе!", 2);
        }

        waitRead();

    }

}
