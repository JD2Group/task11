package it.academy.dao.impl;

import it.academy.dao.CountryDAO;
import it.academy.models.Country;
import javax.persistence.TypedQuery;

import static it.academy.utils.Constants.SELECT_COUNTRY_BY_NAME;

public class CountryDAOImpl extends DAOImpl<Country, Long> implements CountryDAO {

    public CountryDAOImpl() {
        super(Country.class);
    }


    @Override
    public Country readByName(String name) {
        TypedQuery<Country> find = transactionHelper.entityManager().createQuery(SELECT_COUNTRY_BY_NAME, Country.class);
        find.setParameter("name", name);
        return find.getSingleResult();
    }
}
