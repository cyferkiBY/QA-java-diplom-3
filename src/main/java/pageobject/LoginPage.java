package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    //selector field email
    @FindBy(className = "HeaderPage")
    private HeaderPage header;

    //selector field email
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement emailInput;

    //selector field password
    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private SelenideElement passwordInput;

    //selector button registration
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement loginButton;

    //selector header registration
    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement loginHeader;

    @Step("Fill value {email} in email field on Login page")
    public LoginPage fillEmailInput(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Fill value {password} in password field on Login page")
    public LoginPage fillPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Click login button on Login page")
    public LoginPage clickLoginButton() {
        loginButton.shouldBe(visible).click();
        return this;
    }

    @Step("Wait till Login page is left")
    public MainPage loginPageDisappear() {
        loginHeader.should(disappear);
        return page(MainPage.class);
    }

    @Step("Wait till Login page is loaded")
    public LoginPage loginPageLoaded() {
        loginHeader.shouldBe(visible);
        return this;
    }

    @Step("Get load status Login page")
    public boolean isLoginPageLoaded() {
        return loginHeader.isDisplayed();
    }
}