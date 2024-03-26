package ru.itmentor.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserRepoImpl implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveUser(User user) {entityManager.persist(user);}

    @Override
    public void updateUser(User user) {entityManager.merge(user);}

    @Override
    public List<User> showAllUsers() {return entityManager.createQuery("select u from User u order by id", User.class).getResultList();}

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        try {User userToDelete = entityManager.find(User.class, id);
            if (userToDelete != null) {
                entityManager.remove(userToDelete);}
        } catch (NullPointerException e) {
            System.out.println("Пользователь с указанным id не существует!");}
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();}
}


