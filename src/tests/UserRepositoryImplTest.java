package tests;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepositoryImpl;
import utils.MyList;

import static org.junit.jupiter.api.Assertions.*;
public class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    public void getAllUsers() {
        MyList<User> users = userRepository.getAllUsers();
        userRepository.addUser("aaaa@library.com", "Qwerty123!");
        assertEquals(users, userRepository.getAllUsers());
    }

    @Test
    public void addUser() {
        int initialSize = userRepository.getAllUsers().size();
        userRepository.addUser("aaaa@library.com", "Qwerty123!");
        assertEquals(initialSize + 1, userRepository.getAllUsers().size());
    }

    @Test
    public void isEmailExist() {
        String initialEmail = "max@gmail.com";
        boolean initEmail = userRepository.isEmailExist(initialEmail);
        assertEquals(initEmail, userRepository.isEmailExist(initialEmail));
    }

    @Test
    public void getUserByEmail() {
        String initialEmail = "max@gmail.com";
        User getEmail = userRepository.getUserByEmail(initialEmail);
        assertEquals(getEmail, userRepository.getUserByEmail(initialEmail));
    }

    @Test
    public void updatePassword() {
        User user = userRepository.addUser("max@gmail.com", "Qwerty123!");
        userRepository.updatePassword("max@gmail.com", "Password123!");
        assertEquals(user.getPassword(), userRepository.getUserByEmail("max@gmail.com").getPassword());
    }

    @Test
    public void deleteUser() {
        User user = new User("max@gmail.com", "Qwerty123!");
        userRepository.addUser("max@gmail.com", "Qwerty123!");
        assertEquals(user.getEmail(), userRepository.getUserByEmail("max@gmail.com").getEmail());
    }
}