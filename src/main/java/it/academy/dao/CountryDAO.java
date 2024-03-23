package it.academy.dao;

import it.academy.models.Country;

public interface CountryDAO extends DAO<Country, Long> {

    Country readByName(String name);
}
