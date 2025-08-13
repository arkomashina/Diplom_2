package tests;

import factories.BrowserFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import steps.LoginSteps;

public class LoginTests {

    private WebDriver driver;
    private LoginSteps steps;

    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        steps = new LoginSteps(driver);
        steps.openMainPage();
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testLoginPersonalAccountButton() {
        steps.clickPersonalAccount();
        Assert.assertTrue(steps.isEnterButtonDisplayed());
    }

    @Test
    public void testLoginEnterAccountButton() {
        steps.clickEnterAccountButton();
        Assert.assertTrue(steps.isEnterButtonDisplayed());
    }

    @Test
    public void testLoginOnRegisterPage() {
        steps.clickEnterAccountButton();
        steps.clickRegisterButton();
        steps.registerPageClickEnterButton();
        Assert.assertTrue(steps.isEnterButtonDisplayed());
    }

    @Test
    public void testLoginViaRestorePasswordPage() {
        steps.clickEnterAccountButton();
        steps.clickRestorePasswordButton();
        steps.restorePasswordPageClickEnterButton();
        Assert.assertTrue(steps.isEnterButtonDisplayed());
    }
}
