import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private static final Faker FAKER = new Faker(new Locale("ru"));

    public static String randomcity() {
        String[] administrativeCenters = {"Москва",
                "Санкт-Петербург", "Махачкала", "Симферополь", "Екатеринбург", "Челябинск",
                "Новосибирск", "Красноярск", "Пермь", "Воронеж", "Волгоград", "Ростов-на-Дону", "Уфа", "Краснодар", "Самара", "Казань", "Нижний Новгород", "Омск", "Саратов",
                "Тюмень", "Владивосток", "Хабаровск", "Иркутск", "Ярославль", "Якутск", "Барнаул", "Владикавказ", "Иваново", "Ижевск", "Калининград", "Кемерово", "Киров", "Кострома", "Курган", "Курск", "Липецк", "Магадан", "Мурманск",
                "Нижний Тагил", "Новокузнецк", "Новороссийск", "Пенза", "Петрозаводск",
                "Псков", "Рязань", "Саранск", "Смоленск", "Ставрополь", "Тамбов", "Томск",
                "Тула", "Ульяновск", "Чебоксары", "Череповец", "Чита", "Астрахань", "Белгород", "Брянск", "Владимир", "Вологда",
                "Грозный", "Архангельск", "Севастополь", "Ханты-Мансийск", "Южно-Сахалинск", "Петропавловск-Камчатский", "Сыктывкар", "Великий Новгород", "Калуга", "Кировск", "Когалым", "Королев", "Краснокамск", "Краснотурьинск", "Кызыл", "Майкоп", "Мирный", "Надым", "Нарьян-Мар", "Новый Уренгой", "Новый Ургал", "Оренбург",
        };
        return administrativeCenters[new Random().nextInt(administrativeCenters.length)];
    }

    public static String generateDate(int daysToAdd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = LocalDate.now().plusDays(daysToAdd).format(formatter);
        return date;
    }


    public static String generateName() {
        return FAKER.name().fullName();
    }

    public static String generatePhone() {
        String phone = FAKER.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser() {

            return new UserInfo(randomcity(),generateName(),generatePhone());
        }
    }

    public static String generateNoValidPhone(){

        return FAKER.numerify("####");
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
