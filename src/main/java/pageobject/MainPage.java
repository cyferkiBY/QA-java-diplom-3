package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;

public class MainPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";


    //selector header order
    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement orderHeader;

    //selector button sign in to account
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement signInToAccountButton;

    //selector tab Buns
    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    private SelenideElement tabBuns;

    //selector tab Sauces
    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    private SelenideElement tabSauces;

    //selector tab Filling
    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    private SelenideElement tabFilling;

    //current tab
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'current')]/span")
    private SelenideElement currentTab;

    //selector tab Filling
    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    private SelenideElement headerFilling;

    @Step("Click sign in to account button on Main page")
    public MainPage clickSignInToAccountButton() {
        signInToAccountButton.shouldBe(visible).click();
        return this;
    }

    @Step("Click tab Buns on Main page")
    public MainPage clickTabBuns() {
        tabBuns.shouldBe(enabled).click();
        return this;
    }

    @Step("Click tab Sauces on Main page")
    public MainPage clickTabSauces() {
        tabSauces.shouldBe(enabled).click();
        return this;
    }

    @Step("Click tab Filling on Main page")
    public MainPage clickTabFilling() {
        tabFilling.shouldBe(enabled).click();
        return this;
    }

    @Step("Get name of current tab")
    public String getCurrentTab() {
        return currentTab.getText();
    }

    @Step("Wait till Main page is loaded")
    public MainPage mainPageLoaded() {
        orderHeader.shouldBe(visible);
        return this;
    }

    @Step("Wait till Main page is left")
    public MainPage mainPageDisappear() {
        orderHeader.should(disappear);
        return this;
    }

    @Step("Get load status Main page")
    public boolean isMainPageLoaded() {
        return orderHeader.isDisplayed();
    }

}
