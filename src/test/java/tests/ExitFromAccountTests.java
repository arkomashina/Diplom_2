package tests;

import factories.BrowserFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.StellarBurgerLoginPage;
import pageobjects.StellarBurgerMainPage;
import pageobjects.StellarBurgerPersonalAccountPage;
import pageobjects.StellarBurgerRegisterPage;

public class ExitFromAccountTests {

    private WebDriver driver;
    private StellarBurgerMainPage mainPage;
    private StellarBurgerLoginPage loginPage;
    private StellarBurgerRegisterPage registerPage;
    private StellarBurgerPersonalAccountPage personalAccountPage;
    private String browser;

    @Before
    public void setUp() {
        browser = System.getProperty("browser", "yandex");
        driver = BrowserFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");

        mainPage = new StellarBurgerMainPage(driver);
        loginPage = new StellarBurgerLoginPage(driver);
        registerPage = new StellarBurgerRegisterPage(driver);
        personalAccountPage = new StellarBurgerPersonalAccountPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void logoutFromPersonalAccountTest() throws InterruptedException {
        mainPage.clickOnPersonalAccount();
        loginPage.clickRegisterButton();

        String email = "user" + System.currentTimeMillis() + "@test.com";
        String password = "123456";
        registerPage.registration("Иван Иванов", email, password);

        loginPage.login(email, password);

        mainPage.clickOnPersonalAccount();

        Assert.assertTrue("Кнопка выхода не отображается", personalAccountPage.isExitButtonDisplayed());
        personalAccountPage.clickExitButton();

        Assert.assertTrue("Кнопка 'Войти' не отображается после выхода", loginPage.enterButtonIsDisplayed());
    }
}
