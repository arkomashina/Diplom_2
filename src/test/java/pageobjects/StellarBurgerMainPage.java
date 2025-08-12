package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StellarBurgerMainPage {
    private WebDriver driver;


    // Локатор кнопки Личный кабинет и Войти в аккаунт

    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By enterAccountButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    // Локатор лого Соберите бургер

    private final By headerOfConstructor = By.xpath(".//h1[contains(text(), 'Соберите бургер')]");

    // Локаторы вкладок Булки, Соусы и Начинки

    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");


    // Локаторы активных вкладок

    private final By activeBunsTab = By.xpath("//div[contains(@class, 'tab_tab_type_current__')]/span[text()='Булки']");
    private final By activeSaucesTab = By.xpath("//div[contains(@class, 'tab_tab_type_current__')]/span[text()='Соусы']");
    private final By activeFillingsTab = By.xpath("//div[contains(@class, 'tab_tab_type_current__')]/span[text()='Начинки']");

    // Локаторы заголовок секций

    private final By bunsSection = By.xpath("//h2[text()='Булки']");
    private final By saucesSection = By.xpath("//h2[text()='Соусы']");
    private final By fillingsSection = By.xpath("//h2[text()='Начинки']");


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

    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    public boolean isBunsTabActive() {
        return driver.findElement(activeBunsTab).isDisplayed();
    }

    public boolean isSaucesTabActive() {
        return driver.findElement(activeSaucesTab).isDisplayed();
    }

    public boolean isFillingsTabActive() {
        return driver.findElement(activeFillingsTab).isDisplayed();
    }

    public boolean isBunsHeaderVisible() {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsSection))
                .isDisplayed();
    }

    public boolean isSaucesHeaderVisible() {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesSection))
                .isDisplayed();
    }

    public boolean isFillingsHeaderVisible() {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsSection))
                .isDisplayed();
    }


}
