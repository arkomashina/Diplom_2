package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageobjects.*;

public class ExitFromAccountSteps {
    private WebDriver driver;
    private StellarBurgerMainPage mainPage;
    private StellarBurgerLoginPage loginPage;
    private StellarBurgerRegisterPage registerPage;
    private StellarBurgerPersonalAccountPage personalAccountPage;

    public ExitFromAccountSteps(WebDriver driver) {
        this.driver = driver;
        mainPage = new StellarBurgerMainPage(driver);
        loginPage = new StellarBurgerLoginPage(driver);
        registerPage = new StellarBurgerRegisterPage(driver);
        personalAccountPage = new StellarBurgerPersonalAccountPage(driver);
    }

    @Step("Открываем главную страницу Stellar Burger")
    public void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Переходим в Личный кабинет")
    public void goToPersonalAccount() {
        mainPage.clickOnPersonalAccount();
    }

    @Step("Переходим к регистрации")
    public void goToRegistration() {
        loginPage.clickRegisterButton();
    }

    @Step("Регистрируем пользователя: имя {0}, email {1}")
    public void registerUser(String name, String email, String password) {
        registerPage.registration(name, email, password);
    }

    @Step("Входим в аккаунт с email {0}")
    public void login(String email, String password) throws InterruptedException {
        loginPage.login(email, password);
    }

    @Step("Проверяем, что кнопка выхода отображается")
    public boolean isExitButtonDisplayed() {
        return personalAccountPage.isExitButtonDisplayed();
    }

    @Step("Нажимаем кнопку выхода")
    public void clickExitButton() {
        personalAccountPage.clickExitButton();
    }

    @Step("Проверяем, что кнопка Войти отображается")
    public boolean isEnterButtonDisplayed() {
        return loginPage.enterButtonIsDisplayed();
    }
}
