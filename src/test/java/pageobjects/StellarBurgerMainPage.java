package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StellarBurgerMainPage {
    private WebDriver driver;


    // Локатор кнопки Личный кабинет и Войти в аккаунт

    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By enterAccountButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    // Локатор лого Соберите бургер
    private final By headerOfConstructor = By.xpath(".//h1[contains(text(), 'Соберите бургер')]");

    public StellarBurgerMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }

    public void clickOnEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }

    public boolean enterAccountButtonIsDisplayed() {
        return driver.findElement(enterAccountButton).isDisplayed();
    }

    public boolean constructorLogoIsDisplayed() {
        return driver.findElement(headerOfConstructor).isDisplayed();
    }


}
