package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.UserRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public void saveUser(User user) {userRepo.saveUser(user);}

    @Override
    public void updateUser(User user) {userRepo.updateUser(user);}
    @Override
    public List<User> showAllUsers() {
        return userRepo.showAllUsers();
    }

    @Override
    public User getUser(long id) {
        return userRepo.getUser(id);
    }

    @Override
    public void deleteUser(long id) {userRepo.deleteUser(id);}

    @Override
    public User getUserByUsername(String username) {
        return  userRepo.getUserByUsername(username);
    }

    @Override
    public void mergeUser(User user) {
        userRepo.mergeUser(user);
    }

}
