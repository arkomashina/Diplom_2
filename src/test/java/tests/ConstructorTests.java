package tests;

import factories.BrowserFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.StellarBurgerMainPage;

public class ConstructorTests {

    private WebDriver driver;
    private StellarBurgerMainPage mainPage;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "yandex");
        driver = BrowserFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");
        mainPage = new StellarBurgerMainPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldOpenBunsSection() {
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        Assert.assertTrue("Вкладка Булки не активна", mainPage.isBunsTabActive());
    }

    @Test
    public void shouldOpenSaucesSection() {
        mainPage.clickSaucesTab();
        Assert.assertTrue("Вкладка Соусы не активна", mainPage.isSaucesTabActive());
    }

    @Test
    public void shouldShowBunsSectionWhenClickBunsTab() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        Assert.assertTrue("Раздел Булки не виден", mainPage.isBunsHeaderVisible());
    }

    @Test
    public void shouldShowSaucesSectionWhenClickSaucesTab() {
        mainPage.clickSaucesTab();
        Assert.assertTrue("Раздел Соусы не виден", mainPage.isSaucesHeaderVisible());
    }

    @Test
    public void shouldShowFillingsSectionWhenClickFillingsTab() {
        mainPage.clickFillingsTab();
        Assert.assertTrue("Раздел Начинки не виден", mainPage.isFillingsHeaderVisible());
    }

    @Test
    public void shouldOpenFillingsSection() {
        mainPage.clickFillingsTab();
        Assert.assertTrue("Вкладка Начинки не активна", mainPage.isFillingsTabActive());
    }

}
