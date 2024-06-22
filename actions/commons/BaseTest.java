package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
	// Chứa các hàm dùng dùng chung cho cả layer testcase
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");

    protected WebDriver getBrowserDriver(String browserName){
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserList){
            case CHROME:
//                System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//                driver = new ChromeDriver();
                driver = WebDriverManager.chromedriver().create();
                break;
            case FIREFOX:
//                System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//                driver = new FirefoxDriver();
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case EDGE:
//                System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
//                driver = new EdgeDriver();
                driver = WebDriverManager.edgedriver().create();
                break;
            default:
                throw new RuntimeException("Browser is not support.");
        }

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    protected String getEmailAddress() {
        Random rand = new Random();
        return "john" + rand.nextInt() + "@gmail.com";
    }

    protected void quitBrowserDriver(){
        if (driver != null){
            driver.quit();
        }
    }
}
