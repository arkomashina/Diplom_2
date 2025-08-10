package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StellarBurgerLoginPage {

    private final WebDriver driver;

    public StellarBurgerLoginPage(WebDriver driver) {
        this.driver = driver;
    }
    //локатор для поля ввода Email

    private By emailField = By.xpath(".//*[text() = 'Email']");

    //локатор для поля ввода пароль
    private By passwordField = By.xpath(".//*[text() = 'Пароль']");

    //локатор для кнопки Войти

    private By enterButton = By.xpath(".//*[text() = 'Войти']");

    //локатор для кнопки Регистрация
    private By registerButton = By.xpath(".//*[text() = 'Зарегистрироваться']");

    //локатор для кнопки Восстановить пароль
    private By restorePasswordButton = By.xpath(".//*[text() = 'Восстановить пароль']");

    public boolean enterButtonIsDisplayed() {
        return driver.findElement(enterButton).isDisplayed();

    }

    public StellarBurgerLoginPage enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public StellarBurgerLoginPage enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void clickRestorePasswordButton() {
        driver.findElement(restorePasswordButton).click();
    }
}
