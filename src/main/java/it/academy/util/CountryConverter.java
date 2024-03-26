package it.academy.util;

import it.academy.dto.CountryDTO;
import it.academy.pojo.Country;

public class CountryConverter {

    private CountryConverter() {
    }

    public static Country convertToEntity(CountryDTO countryFrom) {

        if (countryFrom != null) {
            return Country.builder()
                       .id(countryFrom.getId())
                       .countryName(countryFrom.getCountryName())
                       .build();
        } else {
            return null;
        }
    }

    public static CountryDTO convertToDTO(Country countryFrom) {

        if (countryFrom != null) {
            return CountryDTO.builder()
                       .id(countryFrom.getId())
                       .countryName(countryFrom.getCountryName())
                       .build();
        } else {
            return null;
        }
    }
}
