import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {
    private final List<User> userListForDelete = new ArrayList<>();

    @Test
    @DisplayName("Check new user is registered with valid date")
    public void checkNewUserIsRegisteredWithValidData() {
        User user = User.getRandomUserValidData();
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(user.getName())
                .fillEmailInput(user.getEmail())
                .fillPasswordInput(user.getPassword())
                .clickRegistrationButton()
                .registrationPageDisappear();
        userListForDelete.add(user);
        String currentURL = webdriver().driver().url();
        assertEquals(LoginPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check new user is not registered with repeated email")
    public void checkUserIsNotRegisteredWithRepeatedEmail() {
        User userValid = User.getRandomUserValidData();
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(userValid.getName())
                .fillEmailInput(userValid.getEmail())
                .fillPasswordInput(userValid.getPassword())
                .clickRegistrationButton();
        userListForDelete.add(userValid);

        User userNotValid = User.getRandomUserValidData();
        userNotValid.setEmail(userValid.getEmail());
        boolean isUserAlreadyExistErrorMessageDisplayed =
                open(RegistrationPage.URL, RegistrationPage.class)
                        .fillNameInput(userNotValid.getName())
                        .fillEmailInput(userNotValid.getEmail())
                        .fillPasswordInput(userNotValid.getPassword())
                        .clickRegistrationButton()
                        .isUserAlreadyExistErrorMessageDisplayed();
        userListForDelete.add(userNotValid);
        assertTrue("Не отобразилось сообщение об ошибке", isUserAlreadyExistErrorMessageDisplayed);
    }

    @Test
    @DisplayName("Check new user is not registered without email")
    public void checkNewUserIsNotRegisteredWithoutEmail() {
        User userNotValid = User.getRandomUserValidData();
        userNotValid.setEmail("");
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(userNotValid.getName())
                .fillEmailInput(userNotValid.getEmail())
                .fillPasswordInput(userNotValid.getPassword())
                .clickRegistrationButton();
        userListForDelete.add(userNotValid);
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
        User userNotValid = User.getRandomUserValidData();
        userNotValid.setName("");
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(userNotValid.getName())
                .fillEmailInput(userNotValid.getEmail())
                .fillPasswordInput(userNotValid.getEmail())
                .clickRegistrationButton();
        userListForDelete.add(userNotValid);
        sleep(1000);
        String currentURL = webdriver().driver().url();
        assertEquals(RegistrationPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check new user is not registered without password")
    public void checkNewUserIsNotRegisteredWithoutPassword() {
        User userNotValid = User.getRandomUserValidData();
        userNotValid.setPassword("");
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(User.getRandomValidName())
                .fillEmailInput(User.getRandomValidEmail())
                .fillPasswordInput("")
                .clickRegistrationButton();
        userListForDelete.add(userNotValid);
        sleep(1000);
        String currentURL = webdriver().driver().url();
        assertEquals(RegistrationPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check new user is not registered with too short password")
    public void checkNewUserIsNotRegisteredWithTooShortPassword() {
        User userNotValid = User.getRandomUserValidData();
        userNotValid.setPassword(User.getRandomTooShotPassword());
        boolean isIncorrectPasswordErrorMessageDisplayed =
                open(RegistrationPage.URL, RegistrationPage.class)
                        .fillNameInput(userNotValid.getName())
                        .fillEmailInput(userNotValid.getEmail())
                        .fillPasswordInput(userNotValid.getPassword())
                        .clickRegistrationButton()
                        .isIncorrectPasswordErrorMessageDisplayed();
        userListForDelete.add(userNotValid);
        assertTrue("Не отобразилось сообщение об ошибке", isIncorrectPasswordErrorMessageDisplayed);
    }

    @After
    @DisplayName("Delete user and clear cookies")
    public void cleanDate() {
        for (User user : userListForDelete) {
            if (user != null) {
                user.deleteUserUsingAPI();
            }
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}