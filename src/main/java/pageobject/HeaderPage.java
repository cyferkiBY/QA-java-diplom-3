package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class HeaderPage {
    //selector link logo
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'logo')]/a[@href='/']")
    private SelenideElement logoLink;

    //selector link constructor
    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'header')][@href='/']")
    private SelenideElement constructorLink;

    //selector link feed
    @FindBy(how = How.XPATH, using = "//a[@href='/feed']")
    private SelenideElement feedLink;

    //selector link account
    @FindBy(how = How.XPATH, using = "//a[@href='/account']")
    private SelenideElement accountLink;

    @Step("Click logo in header")
    public HeaderPage clickHeaderLogoButton() {
        logoLink.shouldBe(visible).click();
        return this;
    }

    @Step("Click constructor button in header")
    public HeaderPage clickHeaderConstructorButton() {
        constructorLink.shouldBe(visible).click();
        return this;
    }

    @Step("Click feed button in header")
    public HeaderPage clickHeaderFeedButton() {
        feedLink.shouldBe(visible).click();
        return this;
    }

    @Step("Click account button in header")
    public HeaderPage clickHeaderAccountButton() {
        accountLink.shouldBe(visible).click();
        return this;
    }
}