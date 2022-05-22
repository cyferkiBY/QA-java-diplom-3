package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //selector link login
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement loginLink;

    @Step("Click link login in Forgot password page")
    public LoginPage clickLoginLink() {
        loginLink.shouldBe(visible).click();
        return page(LoginPage.class);
    }

    @Step("Wait till Forgot password page is left")
    public ForgotPasswordPage mainPageDisappear() {
        loginLink.should(disappear);
        return this;
    }

    @Step("Wait till Forgot password page is loaded")
    public ForgotPasswordPage mainPageLoaded() {
        loginLink.shouldBe(visible);
        return this;
    }

}