package tests;

import factories.BrowserFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.StellarBurgerLoginPage;
import pageobjects.StellarBurgerMainPage;
import pageobjects.StellarBurgerRegisterPage;
import pageobjects.StellarBurgerRestorePasswordPage;

public class LoginTests {

    private WebDriver driver;
    private StellarBurgerMainPage mainPage;
    private StellarBurgerLoginPage loginPage;
    private StellarBurgerRegisterPage registerPage;
    private StellarBurgerRestorePasswordPage restorePasswordPage;
    private String browser;

    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");

        mainPage = new StellarBurgerMainPage(driver);
        loginPage = new StellarBurgerLoginPage(driver);
        registerPage = new StellarBurgerRegisterPage(driver);
        restorePasswordPage = new StellarBurgerRestorePasswordPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginPersonalAccountButton() {
        mainPage.clickOnPersonalAccount();
        Assert.assertTrue(loginPage.enterButtonIsDisplayed());
    }

    @Test
    public void testLoginEnterAccountButton() {
        mainPage.clickOnEnterAccountButton();
        Assert.assertTrue(loginPage.enterButtonIsDisplayed());
    }

    @Test
    public void testLoginOnRegisterPage() {
        mainPage.clickOnEnterAccountButton();
        loginPage.clickRegisterButton();
        registerPage.clickEnterButton();
        Assert.assertTrue(loginPage.enterButtonIsDisplayed());
    }

    @Test
    public void testLoginViaRestorePasswordPage() {
        mainPage.clickOnEnterAccountButton();
        loginPage.clickRestorePasswordButton();
        restorePasswordPage.clickEnterButton();
        Assert.assertTrue(loginPage.enterButtonIsDisplayed());
    }
}
