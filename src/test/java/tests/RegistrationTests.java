package tests;

import factories.BrowserFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import steps.RegistrationSteps;

public class RegistrationTests {
    private WebDriver driver;
    private RegistrationSteps steps;

    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver(System.getProperty("browser", "yandex"));
        driver.manage().window().maximize();
        steps = new RegistrationSteps(driver);
        steps.openMainPage();
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void successfulRegistrationTest() {
        steps.clickPersonalAccount();
        steps.clickRegisterButton();

        String email = "user" + System.currentTimeMillis() + "@test.com";
        steps.registerUser("Иван Иванов", email, "123456");

        Assert.assertTrue("Кнопка 'Войти' не отображается", steps.isEnterButtonDisplayed());
    }

    @Test
    public void registrationWithShortPasswordShowsError() {
        steps.clickPersonalAccount();
        steps.clickRegisterButton();

        String email = "user" + System.currentTimeMillis() + "@test.com";
        steps.registerUser("Иван Иванов", email, "123");

        Assert.assertTrue("Ошибка о коротком пароле не отображается", steps.isWrongPasswordDisplayed());
    }
}
