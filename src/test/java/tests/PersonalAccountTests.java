package tests;

import factories.BrowserFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import steps.PersonalAccountSteps;

public class PersonalAccountTests {

    private WebDriver driver;
    private PersonalAccountSteps steps;

    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver(System.getProperty("browser", "yandex"));
        driver.manage().window().maximize();
        steps = new PersonalAccountSteps(driver);
        steps.openMainPage();
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void checkNavigationToPersonalAccount() {
        steps.clickPersonalAccount();
        Assert.assertTrue("После клика на Личный кабинет не открылась страница входа", steps.isEnterButtonDisplayed());
    }

    @Test
    public void shouldOpenConstructorWhenClickFromPersonalAccount() {
        steps.clickPersonalAccount();
        steps.clickConstructorButton();
        Assert.assertTrue("Текст Соберите бургер отсутствует", steps.isConstructorLogoDisplayed());
    }
}
