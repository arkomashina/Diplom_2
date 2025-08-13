package tests;

import factories.BrowserFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import steps.ExitFromAccountSteps;

public class ExitFromAccountTests {

    private WebDriver driver;
    private ExitFromAccountSteps steps;

    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver(System.getProperty("browser", "yandex"));
        driver.manage().window().maximize();
        steps = new ExitFromAccountSteps(driver);
        steps.openMainPage();
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void logoutFromPersonalAccountTest() throws InterruptedException {
        steps.goToPersonalAccount();
        steps.goToRegistration();

        String email = "user" + System.currentTimeMillis() + "@test.com";
        String password = "123456";
        steps.registerUser("Иван Иванов", email, password);

        steps.login(email, password);
        steps.goToPersonalAccount();

        Assert.assertTrue("Кнопка выхода не отображается", steps.isExitButtonDisplayed());
        steps.clickExitButton();

        Assert.assertTrue("Кнопка 'Войти' не отображается после выхода", steps.isEnterButtonDisplayed());
    }
}
