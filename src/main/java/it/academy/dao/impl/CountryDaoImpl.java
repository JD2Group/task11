package it.academy.dao.impl;

import it.academy.dao.CountryDAo;
import it.academy.pojo.Country;

import javax.persistence.NoResultException;
import java.util.List;

import static it.academy.util.Constants.SELECT_C_FROM_COUNTRY_C_ORDER_BY_C_COUNTRY_NAME_ASC;
import static it.academy.util.Constants.SELECT_FROM_COUNTRY_ORDER_BY_COUNTRYNAME_ASC;

public class CountryDaoImpl extends DaoImpl<Country, Long> implements CountryDAo {

    private static CountryDaoImpl countryDaoImpl;

    private CountryDaoImpl() {

        super(Country.class);
    }

    public static CountryDaoImpl getInstance() {

        if (countryDaoImpl == null) {
            countryDaoImpl = new CountryDaoImpl();
        }
        return countryDaoImpl;
    }

    @Override
    public Country getByCountryName(String countryName) throws NoResultException {

        String JPQLquery = String.format("SELECT c FROM Country c WHERE c.countryName LIKE '%s'", countryName);
        return getEm().createQuery(JPQLquery, Country.class).getSingleResult();
    }

    @Override
    public List<Country> getAll(int page, int count) {

        return getEm().createQuery(SELECT_C_FROM_COUNTRY_C_ORDER_BY_C_COUNTRY_NAME_ASC, Country.class)
                   .setMaxResults(count)
                   .setFirstResult((page - 1) * count)
                   .getResultList();
    }

    @Override
    protected String getAllQuery() {

        return SELECT_FROM_COUNTRY_ORDER_BY_COUNTRYNAME_ASC;
    }
}
