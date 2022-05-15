import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class LoginTest extends BaseTest {
    private User validUserData;

    @Before
    @DisplayName("Create random user")
    public void setUp() {
        validUserData = User.getRandomUserValidData();

        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(validUserData.getName())
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickRegistrationButton()
                .registrationPageDisappear();
    }

    @Test
    @DisplayName("Check can login user with valid date for click header account button")
    public void checkHeaderAccountButtonLoginWithValidData() {
        open(MainPage.URL, HeaderPage.class).clickHeaderAccountButton();
        checkLoginUserWithValidData();
    }

    @Test
    @DisplayName("Check can login user with valid date for click sign in to account button on main page")
    public void checkSignInToAccountButtonLoginWithValidData() {
        open(MainPage.URL, MainPage.class).clickSignInToAccountButton();
        checkLoginUserWithValidData();
    }

    @Test
    @DisplayName("Check can login user with valid date for click link login in registration page")
    public void checkLinkLoginInRegistrationPageWithValidData() {
        open(RegistrationPage.URL, RegistrationPage.class).clickLoginLink();
        checkLoginUserWithValidData();
    }

    @Test
    @DisplayName("Check can login user with valid date for click link login in forgot password page")
    public void checkLinkLoginInForgotPasswordPageWithValidData() {
        open(ForgotPasswordPage.URL, ForgotPasswordPage.class).clickLoginLink();
        checkLoginUserWithValidData();
    }

    @After
    @DisplayName("Navigate to Profile and logout, delete user, clear cookies")
    public void cleanDate() {
        page(HeaderPage.class).clickHeaderAccountButton();
        page(ProfilePage.class)
                .profilePageLoaded()
                .clickLogoutButton()
                .profilePageDisappear();
        if (validUserData != null) {
            validUserData.deleteUserUsingAPI();
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    private void checkLoginUserWithValidData() {
        page(LoginPage.class)
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickLoginButton()
                .loginPageDisappear();
        page(MainPage.class).mainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Залогиниться не удалось", MainPage.URL, currentURL);

        page(HeaderPage.class).clickHeaderAccountButton();
        String actualLogin = page(ProfilePage.class).getLoginInput();
        assertEquals("Логин не совпадает", validUserData.getEmail(), actualLogin);
    }
}