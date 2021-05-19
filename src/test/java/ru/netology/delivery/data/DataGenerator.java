package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int plusDays) {
        LocalDate today = LocalDate.now();
        LocalDate date = today.plusDays(Long.parseLong(String.valueOf(plusDays)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return generateDate(Integer.parseInt(formatter.format(date)));
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        return generateCity(faker.address().city());
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        return generateName(faker.name().lastName() + (" ") + faker.name().firstName());
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        return generatePhone(faker.phoneNumber().phoneNumber());
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(generateCity("ru"), generateName("ru"), generatePhone("ru"));
            return user;
        }

        @Value
        public static class UserInfo {
            String city;
            String name;
            String phone;
        }
    }
}


