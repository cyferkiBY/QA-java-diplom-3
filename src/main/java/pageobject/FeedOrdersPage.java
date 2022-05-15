package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;

public class FeedOrdersPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/feed";

    //selector header feed orders
    @FindBy(how = How.XPATH, using = "//h1[text()='Лента заказов']")
    private SelenideElement feedOrdersHeader;

    @Step("Wait till Feed order page is left")
    public FeedOrdersPage feedOrderPageDisappear() {
        feedOrdersHeader.should(disappear);
        return this;
    }

    @Step("Wait till Feed order page is loaded")
    public FeedOrdersPage feedOrderPageLoaded() {
        feedOrdersHeader.shouldBe(visible);
        return this;
    }
}
