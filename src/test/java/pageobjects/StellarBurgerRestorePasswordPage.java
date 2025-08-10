package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StellarBurgerRestorePasswordPage {

    private WebDriver driver;

    public StellarBurgerRestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор для поля Email и Восстановить пароль
    private final By emailField = By.xpath(".//*[text() = 'Email']");

    private final By restoreButton = By.xpath(".//*[text() = 'Восстановить']");

    // Локатор для кнопки Войти
    private final By enterButtonInRestorePasswordPage = By.xpath(".//*[text() = 'Войти']");

    public void clickEnterButton() {
        driver.findElement(enterButtonInRestorePasswordPage).click();
    }

    public void clickRestoreButton() {
        driver.findElement(restoreButton).click();
    }

    public void enterEmail(String email) {
        WebElement input = driver.findElement(emailField);
        input.click();
        input.sendKeys(email);
    }
}
