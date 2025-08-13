package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageobjects.StellarBurgerLoginPage;
import pageobjects.StellarBurgerMainPage;
import pageobjects.StellarBurgerRegisterPage;

public class RegistrationSteps {
    private WebDriver driver;
    private StellarBurgerMainPage mainPage;
    private StellarBurgerLoginPage loginPage;
    private StellarBurgerRegisterPage registerPage;

    public RegistrationSteps(WebDriver driver) {
        this.driver = driver;
        mainPage = new StellarBurgerMainPage(driver);
        loginPage = new StellarBurgerLoginPage(driver);
        registerPage = new StellarBurgerRegisterPage(driver);
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Переходим в Личный кабинет")
    public void clickPersonalAccount() {
        mainPage.clickOnPersonalAccount();
    }

    @Step("Переходим на страницу регистрации")
    public void clickRegisterButton() {
        loginPage.clickRegisterButton();
    }

    @Step("Регистрируем пользователя: имя {0}, email {1}, пароль {2}")
    public void registerUser(String name, String email, String password) {
        registerPage.registration(name, email, password);
    }

    @Step("Проверяем, что кнопка 'Войти' отображается")
    public boolean isEnterButtonDisplayed() {
        return loginPage.enterButtonIsDisplayed();
    }

    @Step("Проверяем, что отображается ошибка о неверном пароле")
    public boolean isWrongPasswordDisplayed() {
        return registerPage.wrongPasswordIsDisplayed();
    }
}
