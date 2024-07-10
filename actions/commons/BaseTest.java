package commons;

//import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    // Chứa các hàm dùng dùng chung cho cả layer testcase
    private WebDriver driver;
    private String projectPath = GlobalConstants.RELATIVE_PROJECT_PATH;

    protected WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserList) {
            case CHROME:
//                System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//                driver = new ChromeDriver();
//                driver = WebDriverManager.chromedriver().create();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
//                System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//                driver = new FirefoxDriver();
//                driver = WebDriverManager.firefoxdriver().create();
                driver = new FirefoxDriver();
                break;
            case HFIREFOX:
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver = new FirefoxDriver(options);
                break;
            case EDGE:
//                System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
//                driver = new EdgeDriver();
//                driver = WebDriverManager.edgedriver().create();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser is not support.");
        }

        driver.get(url);
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();
        return driver;
    }

    protected String getEmailAddress() {
        Random rand = new Random();
        return "john" + rand.nextInt() + "@gmail.com";
    }

    protected void quitBrowserDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
