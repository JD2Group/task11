package it.academy.dao.impl;

import it.academy.dao.AddressDAO;
import it.academy.entities.Address;
import it.academy.entities.Student;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AddressDAOImpl extends DAOImpl<Address, Long> implements AddressDAO {

    @Override
    protected Class<Address> getClazz() {
        return Address.class;
    }

    @Override
    public Address getByStudentId(Long id) {
//        CriteriaQuery<Address> getByStudentId = criteriaBuilder().createQuery(Address.class);
//        Root<Address> root = getByStudentId.from(Address.class);
//        Root<Student> studentRoot = getByStudentId.from(Student.class);
//
//        getByStudentId.select(root)
//                .where(root.get(""))
        return null;
    }
}
