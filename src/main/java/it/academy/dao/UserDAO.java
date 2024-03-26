package it.academy.dao;

import it.academy.models.User;

public interface UserDAO extends DAO<User, Long> {
    User getUserByEmail(String email);
}
