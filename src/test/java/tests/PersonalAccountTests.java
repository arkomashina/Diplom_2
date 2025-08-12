package tests;

import factories.BrowserFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.StellarBurgerLoginPage;
import pageobjects.StellarBurgerMainPage;

public class PersonalAccountTests {

    private WebDriver driver;
    private StellarBurgerMainPage mainPage;
    private StellarBurgerLoginPage loginPage;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "yandex");
        driver = BrowserFactory.getDriver(browser);

        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");

        mainPage = new StellarBurgerMainPage(driver);
        loginPage = new StellarBurgerLoginPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkNavigationToPersonalAccount() {
        mainPage.clickOnPersonalAccount();
        Assert.assertTrue("После клика на Личный кабинет не открылась страница входа", loginPage.enterButtonIsDisplayed());
    }

    @Test
    public void shouldOpenConstructorWhenClickFromPersonalAccount() {
        mainPage.clickOnPersonalAccount();
        loginPage.clickConstructorButton();
        Assert.assertTrue("Текст Соберите бургер отсутствует", mainPage.constructorLogoIsDisplayed());
    }
}
