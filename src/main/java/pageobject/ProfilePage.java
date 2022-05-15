package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;

public class ProfilePage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //selector login input
    @FindBy(how = How.XPATH, using = "//label[text()='Логин']/following-sibling::input")
    private SelenideElement loginInput;

    //selector logout button
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement logoutButton;

    @Step("Get login input on Profile page")
    public String getLoginInput() {
        return loginInput.shouldBe(visible).getValue();
    }

    @Step("Click logout button on Profile page")
    public ProfilePage clickLogoutButton() {
        logoutButton.shouldBe(visible).click();
        return this;
    }

    @Step("Wait till Profile page is loaded")
    public ProfilePage profilePageLoaded() {
        logoutButton.shouldBe(visible);
        return this;
    }

    @Step("Wait till Profile page is left")
    public ProfilePage profilePageDisappear() {
        logoutButton.should(disappear);
        return this;
    }
}
