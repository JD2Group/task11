package it.academy.dao.impl;

import it.academy.dao.RoleDAO;
import it.academy.models.Role;
import it.academy.utils.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleDAOImpl extends DAOImpl<Role, Long> implements RoleDAO {

    public RoleDAOImpl(){
        super(Role.class);
    }

    @Override
    public Role getRoleByName(String name) {
        try {
            return transactionHelper.entityManager()
                    .createQuery(String.format(Constants.SELECT_ALL_FROM_ROLE_BY_NAME, name), Role.class).getSingleResult();
        } catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
