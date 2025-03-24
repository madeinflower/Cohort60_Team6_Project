package model;

public class User {
    private String email;
    private String password;
    private Role role;
    private final int MAX_BOOKS = 10;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Role.USER; // Дефолтная роль - Гость
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