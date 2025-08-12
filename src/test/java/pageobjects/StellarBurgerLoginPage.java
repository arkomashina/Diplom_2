package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StellarBurgerLoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public StellarBurgerLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //локатор для поля ввода Email

    private final By emailField = By.xpath("//label[contains(text(),'Email')]/following-sibling::input");

    //локатор для поля ввода пароль
    private final By passwordField = By.xpath("//label[contains(text(),'Пароль')]/following-sibling::input");

    //локатор для кнопки Войти

    private final By enterButton = By.cssSelector("button.button_button_type_primary__1O7Bx");

    //локатор для кнопки Регистрация
    private final By registerButton = By.xpath(".//*[text() = 'Зарегистрироваться']");

    //локатор для кнопки Восстановить пароль
    private final By restorePasswordButton = By.xpath(".//*[text() = 'Восстановить пароль']");

    //локатор для кнопки Конструктор

    private final By constructorButton = By.xpath(".//*[text() = 'Конструктор']");

    public boolean enterButtonIsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(enterButton)).isDisplayed();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickEnterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(enterButton))
                .click();
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void clickRestorePasswordButton() {
        driver.findElement(restorePasswordButton).click();
    }

    public void enterEmail(String email) {
        WebElement input = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(emailField));
        input.click();
        input.clear();
        input.sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void login(String email, String password) throws InterruptedException {
        Thread.sleep(3000);
        enterEmail(email);
        Thread.sleep(3000);
        enterPassword(password);
        Thread.sleep(3000);
        clickEnterButton();
    }
}

