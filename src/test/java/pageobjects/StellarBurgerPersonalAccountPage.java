package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StellarBurgerPersonalAccountPage {
    private WebDriver driver;

    public StellarBurgerPersonalAccountPage (WebDriver driver){
        this.driver = driver;
    }

    // Локаторы на кнопки Профиль, История заказов и Выход

    private final By profileButton = By.xpath(".//a[contains(@href, '/account/profile') and text()='Профиль']");
    private final By orderHistoryButton = By.xpath(".//a[contains(@href, '/account/order-history') and text()='История заказов']");
    private final By exitButton = By.xpath(".//button[text()='Выход']");


    public void clickExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(exitButton))
                .click();
    }

    public boolean isExitButtonDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton))
                .isDisplayed();
    }

}
