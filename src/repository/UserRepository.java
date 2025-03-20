package repository;

import model.User;

public interface UserRepository {

    User addUser(String email, String password);

    User addReadUser(User user, String book);

    boolean isEmailExist(String email);

    User getUserByEmail(String email);

    boolean updatePassword(String email, String newPassword);






}
