package it.academy.dao.impl;

import it.academy.dao.UserDAO;
import it.academy.models.User;
import it.academy.utils.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDAOImpl extends DAOImpl<User, Long> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return transactionHelper.entityManager()
                    .createQuery(String.format(Constants.SELECT_ALL_FROM_USER_BY_EMAIL, email), User.class).getSingleResult();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
