package it.academy.dao;

import it.academy.pojo.Country;

import javax.persistence.NoResultException;
import java.util.List;

public interface CountryDAo extends Dao<Country, Long> {

    Country getByCountryName(String countryName) throws NoResultException;

    List<Country> getAll(int page, int count);
}
