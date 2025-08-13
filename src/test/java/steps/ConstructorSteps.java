package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageobjects.StellarBurgerMainPage;

public class ConstructorSteps {

    private WebDriver driver;
    private StellarBurgerMainPage mainPage;

    public ConstructorSteps(WebDriver driver) {
        this.driver = driver;
        this.mainPage = new StellarBurgerMainPage(driver);
    }

    @Step("Открываем главную страницу Stellar Burger")
    public void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Кликаем на вкладку Начинки")
    public void clickFillingsTab() {
        mainPage.clickFillingsTab();
    }

    @Step("Кликаем на вкладку Булки")
    public void clickBunsTab() {
        mainPage.clickBunsTab();
    }

    @Step("Кликаем на вкладку Соусы")
    public void clickSaucesTab() {
        mainPage.clickSaucesTab();
    }

    @Step("Проверяем, что вкладка Булки активна")
    public void checkBunsTabIsActive() {
        assert mainPage.isBunsTabActive();
    }

    @Step("Проверяем, что вкладка Соусы активна")
    public void checkSaucesTabIsActive() {
        assert mainPage.isSaucesTabActive();
    }

    @Step("Проверяем, что заголовок Булки виден")
    public void checkBunsHeaderIsVisible() {
        assert mainPage.isBunsHeaderVisible();
    }

    @Step("Проверяем, что заголовок Соусы виден")
    public void checkSaucesHeaderIsVisible() {
        assert mainPage.isSaucesHeaderVisible();
    }

    @Step("Проверяем, что заголовок Начинки виден")
    public void checkFillingsHeaderIsVisible() {
        assert mainPage.isFillingsHeaderVisible();
    }

    @Step("Проверяем, что вкладка Начинки активна")
    public void checkFillingsTabIsActive() {
        assert mainPage.isFillingsTabActive();
    }
}
