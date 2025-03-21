package model;

import utils.MyArrayList;
import utils.MyList;

import java.util.Objects;

public class User {
    private String email;
    private String password;
    private Role role;
    private final int MAX_BOOKS = 10;

    private final MyList<Book> userBooks;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Role.USER;
        userBooks = new MyArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password) && role == user.role && Objects.equals(userBooks, user.userBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, role, userBooks);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean takeBook() {
        return role == Role.USER;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}