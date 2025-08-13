package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageobjects.StellarBurgerLoginPage;
import pageobjects.StellarBurgerMainPage;

public class PersonalAccountSteps {
    private WebDriver driver;
    private StellarBurgerMainPage mainPage;
    private StellarBurgerLoginPage loginPage;

    public PersonalAccountSteps(WebDriver driver) {
        this.driver = driver;
        mainPage = new StellarBurgerMainPage(driver);
        loginPage = new StellarBurgerLoginPage(driver);
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Переходим в Личный кабинет")
    public void clickPersonalAccount() {
        mainPage.clickOnPersonalAccount();
    }

    @Step("Проверяем, что кнопка Войти отображается")
    public boolean isEnterButtonDisplayed() {
        return loginPage.enterButtonIsDisplayed();
    }

    @Step("Переходим в конструктор из страницы входа")
    public void clickConstructorButton() {
        loginPage.clickConstructorButton();
    }

    @Step("Проверяем, что логотип конструктора отображается")
    public boolean isConstructorLogoDisplayed() {
        return mainPage.constructorLogoIsDisplayed();
    }
}
