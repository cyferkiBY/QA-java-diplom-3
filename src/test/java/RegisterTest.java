import com.github.javafaker.Faker;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.RegisterPage;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;

public class RegisterTest extends BaseTest{
    final String EXPECTED_URL = LoginPage.URL;

    @Test
    public void checkNewUserRegistrationWithValidData() {
        Faker fakerRu = new Faker(new Locale("ru-RU"));
        Faker fakerEn = new Faker(new Locale("en-GB"));
        int minSizePassword = 6;
        int maxSizePassword = 20;

        open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput(fakerRu.name().fullName())
                .fillEmailInput(fakerEn.internet().emailAddress())
                .fillPasswordInput(fakerEn.internet().password(minSizePassword, maxSizePassword))
                .clickRegisterButton()
                .registrationHeaderDisappear();

        String currentURL = webdriver().driver().url();
        assertEquals(currentURL, EXPECTED_URL);
    }

    @Test
    public void checkUserIsNotRegisteredWithRepeatedEmail() {
        Faker fakerRu = new Faker(new Locale("ru-RU"));
        Faker fakerEn = new Faker(new Locale("en-GB"));
        int minSizePassword = 6;
        int maxSizePassword = 20;

        String emailAddress = fakerEn.internet().emailAddress();
        open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput(fakerRu.name().fullName())
                .fillEmailInput(emailAddress)
                .fillPasswordInput(fakerEn.internet().password(minSizePassword,maxSizePassword))
                .clickRegisterButton();

        boolean isUserAlreadyExistErrorMessageDisplayed = open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput(fakerRu.name().fullName())
                .fillEmailInput(emailAddress)
                .fillPasswordInput(fakerEn.internet().password(minSizePassword,maxSizePassword))
                .clickRegisterButton()
                .isUserAlreadyExistErrorMessageDisplayed();

        assertTrue("Не отобразилось сообщение об ошибке", isUserAlreadyExistErrorMessageDisplayed);
    }

    @Test
    public void checkNewUserNotRegistrationWithoutEmail() {
        Faker fakerRu = new Faker(new Locale("ru-RU"));
        Faker fakerEn = new Faker(new Locale("en-GB"));
        int minSizePassword = 6;
        int maxSizePassword = 20;

        open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput(fakerRu.name().fullName())
                .fillEmailInput("")
                .fillPasswordInput(fakerEn.internet().password(minSizePassword,maxSizePassword))
                .clickRegisterButton();

        String currentURL = webdriver().driver().url();
        assertEquals(currentURL, RegisterPage.URL);
    }

    @Test
    public void checkNewUserNotRegistrationWithoutName() {
        Faker fakerEn = new Faker(new Locale("en-GB"));
        int minSizePassword = 6;
        int maxSizePassword = 20;

        open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput("")
                .fillEmailInput(fakerEn.internet().emailAddress())
                .fillPasswordInput(fakerEn.internet().password(minSizePassword,maxSizePassword))
                .clickRegisterButton();

        String currentURL = webdriver().driver().url();
        assertEquals(currentURL, RegisterPage.URL);
    }

    @Test
    public void checkNewUserNotRegistrationWithoutPassword() {
        Faker fakerRu = new Faker(new Locale("ru-RU"));
        Faker fakerEn = new Faker(new Locale("en-GB"));

        open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput(fakerRu.name().fullName())
                .fillEmailInput(fakerEn.internet().emailAddress())
                .fillPasswordInput("")
                .clickRegisterButton();

        String currentURL = webdriver().driver().url();
        assertEquals(currentURL, RegisterPage.URL);
    }

    @Test
    public void checkNewUserNotRegistrationWithTooShortPassword() {
        Faker fakerRu = new Faker(new Locale("ru-RU"));
        Faker fakerEn = new Faker(new Locale("en-GB"));
        int minSizePassword = 1;
        int maxSizePassword = 6;

        boolean isIncorrectPasswordErrorMessageDisplayed = open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput(fakerRu.name().fullName())
                .fillEmailInput(fakerEn.internet().emailAddress())
                .fillPasswordInput(fakerEn.internet().password(minSizePassword,maxSizePassword))
                .clickRegisterButton()
                .isIncorrectPasswordErrorMessageDisplayed();

        assertTrue("Не отобразилось сообщение об ошибке", isIncorrectPasswordErrorMessageDisplayed);
    }
}