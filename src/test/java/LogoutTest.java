import api.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.HeaderPage;
import pageobject.LoginPage;
import pageobject.ProfilePage;
import pageobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogoutTest extends BaseTest {
    private User validUserData;

    @Before
    @DisplayName("Create random user, login and navigate to profile")
    public void setUp() {
        validUserData = User.getRandomUserValidData();

        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(validUserData.getName())
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickRegistrationButton()
                .registrationPageDisappear();

        open(LoginPage.URL, LoginPage.class)
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickLoginButton()
                .loginPageDisappear();

        page(HeaderPage.class).clickHeaderAccountButton();
    }

    @Test
    @Description("Check logout button")
    public void checkLogoutFromProfile() {
        page(ProfilePage.class)
                .profilePageLoaded()
                .clickLogoutButton()
                .profilePageDisappear();
        boolean isLoginPageLoaded = page(LoginPage.class).isLoginPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", LoginPage.URL, currentURL);
        assertTrue("Главная страница не загружена", isLoginPageLoaded);
    }

    @After
    @DisplayName("Delete user and clear cookies")
    public void cleanDate() {
        if (validUserData != null) {
            validUserData.deleteUserUsingAPI();
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}