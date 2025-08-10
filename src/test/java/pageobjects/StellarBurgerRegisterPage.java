package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StellarBurgerRegisterPage {

    private WebDriver driver;

    public StellarBurgerRegisterPage(WebDriver driver){
        this.driver = driver;
    }

    // Локаторы для полей ввода Имя, Email и Пароль

    private final By nameField = By.xpath("//label[contains(text(),'Имя')]/following-sibling::input");


    private final By emailField = By.xpath("//label[contains(text(),'Email')]/following-sibling::input");
    private final By passwordField = By.xpath("//label[contains(text(),'Пароль')]/following-sibling::input");

    //Локатор для отображения подсвечивающегося поля с некорректным паролем
    private final By wrongPasswordField = By.xpath(".//*[text() = 'Некорректный пароль']");

    // Локатор для кнопки Зарегистрироваться
    private final By registerButton = By.xpath(".//*[text() = 'Зарегистрироваться']");




    public void enterName(String name) {
        WebElement input = driver.findElement(nameField);
        input.click();
        input.sendKeys(name);
    }


    public void enterEmail(String email) {
        WebElement input = driver.findElement(emailField);
        input.click();
        input.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement input = driver.findElement(passwordField);
        input.click();
        input.sendKeys(password);

    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void registration(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    public boolean wrongPasswordIsDisplayed() {
        return driver.findElement(wrongPasswordField).isDisplayed();

    }

}
