import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class HeaderWithLoginTest extends BaseTest {
    private User validUserData;

    @Before
    @DisplayName("Create random user and login")
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
        page(MainPage.class).mainPageLoaded();
    }

    @Test
    @DisplayName("Check click header account button navigate to Profile page for login user")
    public void checkHeaderAccountButtonClickWithLogin() {
        page(HeaderPage.class).clickHeaderAccountButton();
        page(ProfilePage.class).profilePageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", ProfilePage.URL, currentURL);
    }

    @Test
    @DisplayName("Check click header constructor button navigate to Main page for login user")
    public void checkHeaderConstructorButtonClickWithLogin() {
        page(HeaderPage.class).clickHeaderConstructorButton();
        page(MainPage.class).mainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check click header feed orders button navigate to Feed orders page for login user")
    public void checkHeaderFeedButtonClickWithLogin() {
        page(HeaderPage.class).clickHeaderFeedButton();
        page(FeedOrdersPage.class).feedOrderPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", FeedOrdersPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check click header logo navigate to Main page for login user")
    public void checkHeaderLogoClickWithLogin() {
        page(HeaderPage.class).clickHeaderLogoButton();
        page(MainPage.class).mainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
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
}