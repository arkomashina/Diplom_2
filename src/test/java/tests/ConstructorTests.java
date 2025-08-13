package tests;

import factories.BrowserFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import steps.ConstructorSteps;

public class ConstructorTests {

    private WebDriver driver;
    private ConstructorSteps constructorSteps;

    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver(System.getProperty("browser", "yandex"));
        driver.manage().window().maximize();

        constructorSteps = new ConstructorSteps(driver);
        constructorSteps.openMainPage();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldOpenBunsSection() {
        constructorSteps.clickFillingsTab();
        constructorSteps.clickBunsTab();
        constructorSteps.checkBunsTabIsActive();
    }

    @Test
    public void shouldOpenSaucesSection() {
        constructorSteps.clickSaucesTab();
        constructorSteps.checkSaucesTabIsActive();
    }

    @Test
    public void shouldShowBunsSectionWhenClickBunsTab() {
        constructorSteps.clickSaucesTab();
        constructorSteps.clickBunsTab();
        constructorSteps.checkBunsHeaderIsVisible();
    }

    @Test
    public void shouldShowSaucesSectionWhenClickSaucesTab() {
        constructorSteps.clickSaucesTab();
        constructorSteps.checkSaucesHeaderIsVisible();
    }

    @Test
    public void shouldShowFillingsSectionWhenClickFillingsTab() {
        constructorSteps.clickFillingsTab();
        constructorSteps.checkFillingsHeaderIsVisible();
    }

    @Test
    public void shouldOpenFillingsSection() {
        constructorSteps.clickFillingsTab();
        constructorSteps.checkFillingsTabIsActive();
    }


}
