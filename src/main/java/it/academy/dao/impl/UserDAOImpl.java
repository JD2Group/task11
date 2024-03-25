package it.academy.dao.impl;

import it.academy.dao.UserDAO;
import it.academy.models.User;

public class UserDAOImpl extends DAOImpl<User, Long> implements UserDAO {
    public UserDAOImpl() {
        super(User.class);
    }


}
