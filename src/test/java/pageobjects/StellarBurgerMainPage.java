package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StellarBurgerMainPage {
    private WebDriver driver;


    // Локатор кнопки Личный кабинет и Войти в аккаунт

    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By enterAccountButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    public StellarBurgerMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }

    public void clickOnEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }


}
