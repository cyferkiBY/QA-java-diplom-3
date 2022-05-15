import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {
    @Test
    @DisplayName("Check click on the tab Buns to activate the tab Buns")
    public void checkClickTabBuns() {
        //т.к. по умолчанию активизирован элемент Булки
        //для проверки сначала переключимся на другой элемент;
        String expectedTab = "Булки";
        open(MainPage.URL, MainPage.class).clickTabSauces();
        page(MainPage.class).clickTabBuns();

        String currentTab = page(MainPage.class).getCurrentTab();
        assertEquals("Вкладка не переключилась", expectedTab, currentTab);
    }

    @Test
    @DisplayName("Check click on the tab Sauces to activate the tab Sauces")
    public void checkClickTabSauces() {
        String expectedTab = "Соусы";
        String currentTab = open(MainPage.URL, MainPage.class)
                .clickTabSauces()
                .getCurrentTab();
        assertEquals("Вкладка не переключилась", expectedTab, currentTab);
    }

    @Test
    @DisplayName("Check click on the tab Filling to activate the tab Filling")
    public void checkClickTabFilling() {
        String expectedTab = "Начинки";
        String currentTab = open(MainPage.URL, MainPage.class)
                .clickTabFilling()
                .getCurrentTab();
        assertEquals("Вкладка не переключилась", expectedTab, currentTab);
    }
}