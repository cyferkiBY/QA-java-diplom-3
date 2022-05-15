package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;

public class RegistrationPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    //selector field name
    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    private SelenideElement nameInput;

    //selector field email
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement emailInput;

    //selector field password
    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private SelenideElement passwordInput;

    //selector button registration
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    //selector header registration
    @FindBy(how = How.XPATH, using = "//h2[text()='Регистрация']")
    private SelenideElement registrationHeader;

    //selector user already exist error message
    @FindBy(how = How.XPATH, using = "//p[text()='Такой пользователь уже существует']")
    private SelenideElement userAlreadyExistErrorMessage;

    //selector incorrect password error message
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordErrorMessage;

    //selector login link
    @FindBy(how = How.XPATH, using = "//a[@href='/login']")
    private SelenideElement loginLink;

    @Step("Fill value {name} in email field on Registration page")
    public RegistrationPage fillNameInput(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    @Step("Fill value {email} in email field on Registration page")
    public RegistrationPage fillEmailInput(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Fill value {password} in email field on Registration page")
    public RegistrationPage fillPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Click registration button on Registration page")
    public RegistrationPage clickRegistrationButton() {
        registerButton.shouldBe(visible).click();
        return this;
    }

    @Step("Click login link on Registration page")
    public RegistrationPage clickLoginLink() {
        loginLink.shouldBe(visible).click();
        return this;
    }

    @Step("Get displayed the error message UserAlreadyExist")
    public boolean isUserAlreadyExistErrorMessageDisplayed() {
        return userAlreadyExistErrorMessage.should(exist).isDisplayed();
    }

    @Step("Get displayed the error message IncorrectPassword")
    public boolean isIncorrectPasswordErrorMessageDisplayed() {
        return incorrectPasswordErrorMessage.should(exist).isDisplayed();
    }

    @Step("Wait till Registration page is left")
    public RegistrationPage registrationPageDisappear() {
        registrationHeader.should(disappear);
        return this;
    }

    @Step("Wait till Registration page is loaded")
    public RegistrationPage registrationPageLoaded() {
        registrationHeader.shouldBe(visible);
        return this;
    }
}