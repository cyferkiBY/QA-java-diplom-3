import com.github.javafaker.Faker;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.RegisterPage;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;

public class RegisterTest extends BaseTest{
    private final static String EXPECTED_URL = LoginPage.URL;
    private final Faker fakerRu = new Faker(new Locale("ru-RU"));
    private final Faker fakerEn = new Faker(new Locale("en-GB"));

    @Test
    public void checkNewUserRegistrationWithValidData() {
        int minSizePassword = 6;
        int maxSizePassword = 20;

        open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput(fakerRu.name().fullName())
                .fillEmailInput(fakerEn.internet().emailAddress())
                .fillPasswordInput(fakerEn.internet().password(minSizePassword, maxSizePassword))
                .clickRegisterButton()
                .registrationHeaderDisappear();

        String currentURL = webdriver().driver().url();
        assertEquals(EXPECTED_URL, currentURL);
    }

    @Test
    public void checkUserIsNotRegisteredWithRepeatedEmail() {
        int minSizePassword = 6;
        int maxSizePassword = 20;

        String emailAddress = fakerEn.internet().emailAddress();
        open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput(fakerRu.name().fullName())
                .fillEmailInput(emailAddress)
                .fillPasswordInput(fakerEn.internet().password(minSizePassword,maxSizePassword))
                .clickRegisterButton();

        boolean isUserAlreadyExistErrorMessageDisplayed =
                open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput(fakerRu.name().fullName())
                .fillEmailInput(emailAddress)
                .fillPasswordInput(fakerEn.internet().password(minSizePassword,maxSizePassword))
                .clickRegisterButton()
                .isUserAlreadyExistErrorMessageDisplayed();

        assertTrue("Не отобразилось сообщение об ошибке", isUserAlreadyExistErrorMessageDisplayed);
    }

    @Test
    public void checkNewUserNotRegistrationWithoutEmail() {
        int minSizePassword = 6;
        int maxSizePassword = 20;

        open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput(fakerRu.name().fullName())
                .fillEmailInput("")
                .fillPasswordInput(fakerEn.internet().password(minSizePassword,maxSizePassword))
                .clickRegisterButton();
        sleep(1000);

        String currentURL = webdriver().driver().url();
        assertEquals(RegisterPage.URL, currentURL);
    }

    @Test
    public void checkNewUserNotRegistrationWithoutName() {
        int minSizePassword = 6;
        int maxSizePassword = 20;

        open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput("")
                .fillEmailInput(fakerEn.internet().emailAddress())
                .fillPasswordInput(fakerEn.internet().password(minSizePassword,maxSizePassword))
                .clickRegisterButton();
        sleep(1000);

        String currentURL = webdriver().driver().url();
        assertEquals(RegisterPage.URL, currentURL);
    }

    @Test
    public void checkNewUserNotRegistrationWithoutPassword() {
        open(RegisterPage.URL, RegisterPage.class)
                .fillNameInput(fakerRu.name().fullName())
                .fillEmailInput(fakerEn.internet().emailAddress())
                .fillPasswordInput("")
                .clickRegisterButton();
        sleep(1000);

        String currentURL = webdriver().driver().url();
        assertEquals(RegisterPage.URL, currentURL);
    }

    @Test
    public void checkNewUserNotRegistrationWithTooShortPassword() {
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