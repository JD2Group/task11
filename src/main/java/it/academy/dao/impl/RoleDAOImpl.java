package it.academy.dao.impl;

import it.academy.dao.RoleDAO;
import it.academy.models.Role;

public class RoleDAOImpl extends DAOImpl<Role, Long> implements RoleDAO {

    public RoleDAOImpl(){
        super(Role.class);
    }



}
