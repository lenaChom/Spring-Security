package ru.itmentor.spring.boot_security.demo.repository;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepo  {

    void saveUser(User user);

    void updateUser(User user);

    List<User> showAllUsers();

    User getUser(long id);

    void deleteUser(long id);

    User getUserByUsername(String username);
}
