package it.academy.utils;

import com.github.javafaker.Faker;
import it.academy.dao.CountryDAO;
import it.academy.dao.impl.CountryDAOImpl;
import it.academy.models.Address;
import it.academy.models.Country;
import it.academy.models.Student;

import java.util.List;
import java.util.stream.IntStream;

import static it.academy.utils.Constants.RANDOM;

public class DataGenerator {

    private static final Faker faker = new Faker();
    private static final List<String> LIST_OF_COUNTRY_NAMES = List.of(
            "United States", "China", "India", "Indonesia", "Pakistan",
            "Brazil", "Nigeria", "Bangladesh", "Russia", "Mexico"
    );

    public static void generateCountries() {
        CountryDAO countryDAO = new CountryDAOImpl();
        TransactionHelper transactionHelper = TransactionHelper.getTransactionHelper();
        IntStream.range(0, LIST_OF_COUNTRY_NAMES.size())
                .forEach(i -> {
                    Country country = Country.builder()
                            .name(LIST_OF_COUNTRY_NAMES.get(i))
                            .build();
                    transactionHelper.transaction(() -> countryDAO.create(country));
                });
    }


    public static Student generateStudent() {
        CountryDAO countryDAO = new CountryDAOImpl();
        return Student.builder()
                .name(faker.name().firstName())
                .surname(faker.name().lastName())
                .age(faker.number().numberBetween(1, 100))
                .mark(faker.number().numberBetween(0, 10))
                .address(Address.builder()
                        .street(faker.address().streetName())
                        .city(faker.address().city())
                        .building(faker.number().numberBetween(1, 1000))
                        .build())
                .country(countryDAO.readByName(LIST_OF_COUNTRY_NAMES.get(RANDOM.nextInt(LIST_OF_COUNTRY_NAMES.size()))))
                .build();

    }

}
