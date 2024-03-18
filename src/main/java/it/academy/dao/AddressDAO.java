package it.academy.dao;

import it.academy.entities.Address;

public interface AddressDAO extends DAO<Address, Long> {

    Address getByStudentId(Long id);

}
