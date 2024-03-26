package it.academy.dao;

import it.academy.models.Role;

public interface RoleDAO extends DAO<Role, Long> {
    Role getRoleByName(String name);
}
