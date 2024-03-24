package ru.itmentor.spring.boot_security.demo.repository;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleRepo {

    Role getRoleByName(String name);

    List<Role> showAllRoles();

}
