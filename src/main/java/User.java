import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.Builder;
import lombok.Data;

import java.util.Locale;

@Data
@Builder
public class User {
    static private final Faker FAKER_RU = new Faker(new Locale("ru-RU"));
    static private final Faker FAKER_EN = new Faker(new Locale("en-GB"));
    private String name;
    private String email;
    private String password;

    public static User getRandomUserValidData() {
        final String name = getRandomValidName();
        final String email = getRandomValidEmail();
        final String password = getRandomValidPassword();
        return new User(name, email, password);
    }

    @Step("Get valid random name")
    public static String getRandomValidName() {
        return FAKER_RU.name().fullName();
    }

    @Step("Get valid random email")
    public static String getRandomValidEmail() {
        return FAKER_EN.internet().emailAddress();
    }

    @Step("Get valid random password")
    public static String getRandomValidPassword() {
        int minSizePassword = 6;
        int maxSizePassword = 20;
        return FAKER_EN.internet().password(minSizePassword, maxSizePassword);
    }

    @Step("Get too short random password")
    public static String getRandomTooShotPassword() {
        int minSizePassword = 1;
        int maxSizePassword = 6;
        return FAKER_EN.internet().password(minSizePassword, maxSizePassword);
    }
}