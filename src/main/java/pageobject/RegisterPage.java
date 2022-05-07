package pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;

public class RegisterPage {
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

    //selector field password
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

    public RegisterPage fillNameInput(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    public RegisterPage fillEmailInput(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public RegisterPage fillPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public RegisterPage clickRegisterButton() {
        registerButton.click();
        return this;
    }

    public boolean isUserAlreadyExistErrorMessageDisplayed() {
        return userAlreadyExistErrorMessage.should(exist).isDisplayed();
    }

    public boolean isIncorrectPasswordErrorMessageDisplayed() {
        return incorrectPasswordErrorMessage.should(exist).isDisplayed();
    }

    //для ожидание перехода со страницы
    public SelenideElement registrationHeaderDisappear() {
        return registrationHeader.should(disappear);
    }

}
