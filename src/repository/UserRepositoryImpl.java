package repository;

import model.User;
import utils.MyArrayList;
import utils.MyList;

import javax.management.relation.Role;

public class UserRepositoryImpl implements UserRepository {

    private final MyList<User> users;

    public UserRepositoryImpl() {
        users = new MyArrayList<>();
        addUsers();
    }

    private void addUsers() {
        User admin = new User("admin@library.net", "qazxsw@12");
        admin.setRole(Role.ADMIN);

        User user = new User("test@test.com", "qwerty!Q34");
        user.setRole(Role.USER);
        users.addAll(admin, user);

    }

    @Override
    public User addUser(String email, String password) {
        User user = new User(email, password);
        users.add(user);
        return user;
    }

    @Override
    public User addReadUser(User user, String book) {
        if (user.takeBook()) {
            user = user.setRole(Role.READER);
            return user;
        }

        return null;
    }

    @Override
    public boolean isEmailExist(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setPassword(newPassword);
                System.out.println("Пароль успешно изменён!");
                return true;
            }
        }
        System.out.println("Пользователь не найден!");
        return false;
    }
}
