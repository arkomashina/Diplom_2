package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageobjects.*;

public class LoginSteps {
    private WebDriver driver;
    private StellarBurgerMainPage mainPage;
    private StellarBurgerLoginPage loginPage;
    private StellarBurgerRegisterPage registerPage;
    private StellarBurgerRestorePasswordPage restorePasswordPage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        mainPage = new StellarBurgerMainPage(driver);
        loginPage = new StellarBurgerLoginPage(driver);
        registerPage = new StellarBurgerRegisterPage(driver);
        restorePasswordPage = new StellarBurgerRestorePasswordPage(driver);
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Переходим в Личный кабинет")
    public void clickPersonalAccount() {
        mainPage.clickOnPersonalAccount();
    }

    @Step("Переходим на страницу входа")
    public void clickEnterAccountButton() {
        mainPage.clickOnEnterAccountButton();
    }

    @Step("Проверяем, что кнопка Войти отображается")
    public boolean isEnterButtonDisplayed() {
        return loginPage.enterButtonIsDisplayed();
    }

    @Step("Переходим на страницу регистрации")
    public void clickRegisterButton() {
        loginPage.clickRegisterButton();
    }

    @Step("Переходим на страницу восстановления пароля")
    public void clickRestorePasswordButton() {
        loginPage.clickRestorePasswordButton();
    }

    @Step("Нажимаем кнопку Войти на странице регистрации")
    public void registerPageClickEnterButton() {
        registerPage.clickEnterButton();
    }

    @Step("Нажимаем кнопку Войти на странице восстановления пароля")
    public void restorePasswordPageClickEnterButton() {
        restorePasswordPage.clickEnterButton();
    }
}
