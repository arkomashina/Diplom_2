package factories;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {
    public static WebDriver getDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case "yandex":
                WebDriverManager.chromedriver().driverVersion("136.0.7103.36").setup();
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Users\\arcad\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe"); // путь к Яндекс.Браузеру на Windows
                return new ChromeDriver(options);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }
}
