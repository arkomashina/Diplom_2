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


public class RegistrationTests {
    private WebDriver driver;
    private StellarBurgerMainPage mainPage;
    private StellarBurgerLoginPage loginPage;
    private StellarBurgerRegisterPage registerPage;
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
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void successfulRegistrationTest() {
        mainPage.clickOnPersonalAccount();
        loginPage.clickRegisterButton();

        String email = "user" + System.currentTimeMillis() + "@test.com";
        registerPage.registration("Иван Иванов", email, "123456");

        Assert.assertTrue("Кнопка 'Войти' не отображается", loginPage.enterButtonIsDisplayed());
    }


    @Test
    public void registrationWithShortPasswordShowsError() {
        mainPage.clickOnPersonalAccount();
        loginPage.clickRegisterButton();

        String email = "user" + System.currentTimeMillis() + "@test.com";
        registerPage.registration("Иван Иванов", email, "123");

        Assert.assertTrue("Ошибка о коротком пароле не отображается", registerPage.wrongPasswordIsDisplayed());
    }
}
