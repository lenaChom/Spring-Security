package ru.itmentor.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleRepoImpl implements RoleRepo{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        Role role = null;
        try {role = entityManager.createQuery("SELECT r FROM Role r WHERE r.name=:name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Роли с таким именем не существует!");}
        return role;}
    @Override
    public List<Role> showAllRoles() {return entityManager.createQuery("select r from Role r", Role.class).getResultList();}
}
