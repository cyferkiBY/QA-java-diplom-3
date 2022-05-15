import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {
    @Test
    @DisplayName("Check new user is registered with valid date")
    public void checkNewUserIsRegisteredWithValidData() {
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(User.getRandomValidName())
                .fillEmailInput(User.getRandomValidEmail())
                .fillPasswordInput(User.getRandomValidPassword())
                .clickRegistrationButton()
                .registrationPageDisappear();

        String currentURL = webdriver().driver().url();
        assertEquals(LoginPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check new user is not registered with repeated email")
    public void checkUserIsNotRegisteredWithRepeatedEmail() {
        String validEmail = User.getRandomValidEmail();

        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(User.getRandomValidName())
                .fillEmailInput(validEmail)
                .fillPasswordInput(User.getRandomValidPassword())
                .clickRegistrationButton();

        boolean isUserAlreadyExistErrorMessageDisplayed =
                open(RegistrationPage.URL, RegistrationPage.class)
                        .fillNameInput(User.getRandomValidName())
                        .fillEmailInput(validEmail)
                        .fillPasswordInput(User.getRandomValidPassword())
                        .clickRegistrationButton()
                        .isUserAlreadyExistErrorMessageDisplayed();

        assertTrue("Не отобразилось сообщение об ошибке", isUserAlreadyExistErrorMessageDisplayed);
    }

    @Test
    @DisplayName("Check new user is not registered without email")
    public void checkNewUserIsNotRegisteredWithoutEmail() {
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(User.getRandomValidName())
                .fillEmailInput("")
                .fillPasswordInput(User.getRandomValidPassword())
                .clickRegistrationButton();
        //Тут без sleep не обойтись, иначе словим ложно положительные срабатывания.
        //Явными ожиданиями не обойтись, т.к. мы должны ожидать того что останемся на странице.
        //При этом, возможно, что отслеживание того, что есть элемент на странице,
        //при помощи shouldBe (и соответсвенно проверка текущего URL) сработает раньше,
        //чем ошибочный переход  на следующий страницу.
        //Поэтому здесь и в аналогичных случаях использую sleep.
        sleep(1000);

        String currentURL = webdriver().driver().url();
        assertEquals(RegistrationPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check new user is not registered without name")
    public void checkNewUserIsNotRegisteredWithoutName() {
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput("")
                .fillEmailInput(User.getRandomValidEmail())
                .fillPasswordInput(User.getRandomValidPassword())
                .clickRegistrationButton();
        sleep(1000);

        String currentURL = webdriver().driver().url();
        assertEquals(RegistrationPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check new user is not registered without password")
    public void checkNewUserIsNotRegisteredWithoutPassword() {
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(User.getRandomValidName())
                .fillEmailInput(User.getRandomValidEmail())
                .fillPasswordInput("")
                .clickRegistrationButton();
        sleep(1000);

        String currentURL = webdriver().driver().url();
        assertEquals(RegistrationPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check new user is not registered with too short password")
    public void checkNewUserIsNotRegisteredWithTooShortPassword() {
        boolean isIncorrectPasswordErrorMessageDisplayed =
                open(RegistrationPage.URL, RegistrationPage.class)
                        .fillNameInput(User.getRandomValidName())
                        .fillEmailInput(User.getRandomValidEmail())
                        .fillPasswordInput(User.getRandomTooShotPassword())
                        .clickRegistrationButton()
                        .isIncorrectPasswordErrorMessageDisplayed();

        assertTrue("Не отобразилось сообщение об ошибке", isIncorrectPasswordErrorMessageDisplayed);
    }
}