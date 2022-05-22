import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.FeedOrdersPage;
import pageobject.HeaderPage;
import pageobject.LoginPage;
import pageobject.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class HeaderWithoutLoginTest extends BaseTest {
    @Test
    @DisplayName("Check click header account button navigate to LoginPage page for NOT login user")
    public void checkHeaderAccountButtonClickWithoutLogin() {
        open(MainPage.URL, HeaderPage.class).clickHeaderAccountButton();
        page(LoginPage.class).loginPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", LoginPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check click header constructor button navigate to MainPage page for NOT login user")
    public void checkHeaderConstructorButtonClickWithoutLogin() {
        open(MainPage.URL, HeaderPage.class).clickHeaderConstructorButton();
        page(MainPage.class).mainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check click header feed orders button navigate to Feed orders page for NOT login user")
    public void checkHeaderFeedButtonClickWithoutLogin() {
        open(MainPage.URL, HeaderPage.class).clickHeaderFeedButton();
        page(FeedOrdersPage.class).feedOrderPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", FeedOrdersPage.URL, currentURL);
    }

    @Test
    @DisplayName("Check click header logo navigate to MainPage page for NOT login user")
    public void checkHeaderLogoClickWithoutLogin() {
        open(MainPage.URL, HeaderPage.class).clickHeaderLogoButton();
        page(MainPage.class).mainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
    }
}