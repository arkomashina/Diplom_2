package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StellarBurgerMainPage {
    private WebDriver driver;


    // Локатор кнопки Личный кабинет

    private By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    public StellarBurgerMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }


}
