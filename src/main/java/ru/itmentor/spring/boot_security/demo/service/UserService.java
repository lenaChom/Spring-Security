package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void updateUser(User user);
    List<User> showAllUsers();

    public User getUser(long id);

    public void deleteUser(long id);

   User getUserByUsername(String username);

    void mergeUser(User user);
}
