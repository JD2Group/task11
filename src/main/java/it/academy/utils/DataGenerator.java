package it.academy.utils;

import it.academy.dao.CountryDAO;
import it.academy.dao.impl.CountryDAOImpl;
import it.academy.models.Address;
import it.academy.models.Country;
import it.academy.models.Student;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static it.academy.utils.Constants.RANDOM;

public class DataGenerator {

    private static final List<String> LIST_OF_NAMES_M = List.of(
            "Александр", "Дмитрий", "Максим", "Иван", "Артём",
            "Михаил", "Никита", "Сергей", "Егор", "Андрей",
            "Кирилл", "Илья", "Даниил", "Владимир", "Павел",
            "Роман", "Тимофей", "Григорий", "Олег", "Владислав",
            "James", "John", "Robert", "Michael", "William",
            "David", "Richard", "Joseph", "Charles", "Thomas");
    private static final List<String> LIST_OF_NAMES_W = List.of(
            "Анна", "Мария", "Елена", "Ольга", "Татьяна",
            "Ирина", "Наталья", "Светлана", "Екатерина", "Александра",
            "Виктория", "Алёна", "Маргарита", "Евгения", "Валентина",
            "Нина", "Любовь", "Вера", "Евдокия", "Анжелика",
            "Регина", "Инна", "Дарья", "Полина", "Лариса",
            "Оксана", "Кристина", "Василиса", "Марина", "Зоя",
            "Emma", "Olivia", "Ava", "Isabella", "Sophia",
            "Charlotte", "Mia", "Amelia", "Harper", "Evelyn",
            "Abigail", "Emily", "Elizabeth", "Mila", "Ella",
            "Avery", "Sofia", "Camila", "Aria", "Scarlett",
            "Victoria", "Madison", "Luna", "Grace", "Chloe",
            "Penelope", "Layla", "Riley", "Zoey", "Nora"
    );
    private static final List<String> LIST_OF_SURNAMES_M = List.of(
            "Иванов", "Смирнов", "Кузнецов", "Попов", "Васильев",
            "Петров", "Соколов", "Михайлов", "Новиков", "Федоров",
            "Морозов", "Волков", "Алексеев", "Лебедев", "Семенов",
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Miller", "Davis", "Garcia", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson");
    private static final List<String> LIST_OF_SURNAMES_W = List.of(
            "Иванова", "Смирнова", "Кузнецова", "Попова", "Васильева",
            "Петрова", "Соколова", "Михайлова", "Новикова", "Федорова",
            "Морозова", "Волкова", "Алексеева", "Лебедева", "Семенова",
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Miller", "Davis", "Garcia", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson");
    private static final Integer MIN_AGE_BOUND = 1;
    private static final Integer MAX_AGE_BOUND = 100;
    private static final Integer MIN_MARK_BOUND = 1;
    private static final Integer MAX_MARK_BOUND = 10;
    private static final Integer MIN_BUILDING_BOUND = 1;
    private static final Integer MAX_BUILDING_BOUND = 100;
    private static final List<String> LIST_OF_STREETS = List.of(
            "Main Street", "Park Avenue", "Broadway", "Elm Street", "Maple Avenue", "High Street",
            "Washington Street", "Church Street", "Oak Street", "Cedar Avenue", "Pine Street", "Elmwood Avenue",
            "Lake Street", "River Road", "Smith Street", "Johnson Avenue", "Park Street", "Center Street",
            "First Avenue", "Second Street"
    );
    private static final List<String> LIST_OF_CITIES = List.of(
            "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio",
            "San Diego", "Dallas", "San Jose"
    );
    private static final List<String> LIST_OF_COUNTRY_NAMES = List.of(
            "United States", "China", "India", "Indonesia", "Pakistan",
            "Brazil", "Nigeria", "Bangladesh", "Russia", "Mexico"
    );

    public static void generateCountries(){
        CountryDAO countryDAO = new CountryDAOImpl();
        TransactionHelper transactionHelper = TransactionHelper.getTransactionHelper();
        IntStream.range(0, LIST_OF_COUNTRY_NAMES.size())
                .forEach(i->{
                    Country country = Country.builder()
                            .name(LIST_OF_COUNTRY_NAMES.get(i))
                            .build();
                    transactionHelper.transaction(()-> countryDAO.create(country));
                });
    }


    public static Student generateStudent(){
        int gender = RANDOM.nextInt(2);
        CountryDAO countryDAO = new CountryDAOImpl();
        return Student.builder()
                .name((gender == 1) ?
                        LIST_OF_NAMES_M.get(RANDOM.nextInt(LIST_OF_NAMES_M.size())) :
                        LIST_OF_NAMES_W.get(RANDOM.nextInt(LIST_OF_NAMES_W.size())))
                .surname((gender == 1) ?
                        LIST_OF_SURNAMES_M.get(RANDOM.nextInt(LIST_OF_SURNAMES_M.size())) :
                        LIST_OF_SURNAMES_W.get(RANDOM.nextInt(LIST_OF_SURNAMES_W.size())))
                .age(RANDOM.nextInt(MAX_AGE_BOUND - MIN_AGE_BOUND) + MIN_AGE_BOUND + 1)
                .mark(RANDOM.nextInt(MAX_MARK_BOUND - MIN_MARK_BOUND) + MIN_MARK_BOUND + 1)
                .address(Address.builder()
                        .street(LIST_OF_STREETS.get(RANDOM.nextInt(LIST_OF_STREETS.size())))
                        .city(LIST_OF_CITIES.get(RANDOM.nextInt(LIST_OF_CITIES.size())))
                        .building(RANDOM.nextInt(MAX_BUILDING_BOUND - MIN_BUILDING_BOUND) + MIN_BUILDING_BOUND + 1)
                        .build())
                .country(countryDAO.readByName(LIST_OF_COUNTRY_NAMES.get(RANDOM.nextInt(LIST_OF_COUNTRY_NAMES.size()))))
                .build();

    }

}
