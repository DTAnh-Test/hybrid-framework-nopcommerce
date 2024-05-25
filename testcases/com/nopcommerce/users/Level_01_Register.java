package com.nopcommerce.users;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.security.PublicKey;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Register {
	// RY: Repeat yourself: Cho lặp lại nhiều lần (Dry: Don't Repeat yourself)
	
	// Ưu điểm:
		// Tốc độ nhanh
		// Code mẫu vài testcase để hiểu được cách dùng tool nào đó (PoC)
		// POC là 1 vài example trong đó tập trung vào việc xác định xem 1 ý tưởng có thể biến thành thực hay không
		// Phù hợp dự án làm nhanh/ ngắn hạn/ thử nghiệm
	// Nhược điểm:
		// Sự lặp lại rất nhiều step: Locator/ Hàm Selenium
		// Phí bảo trì (maintain) tăng khi có sự thay đổi: Logic/ business/Thư viện/ UI...
		// Không phù hợp với framework
		// Không phù hợp làm dự án auto dài/ mang lại nhiều value
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void Register_01_Empty_Data() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span#FirstName-error")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(),"First name is required.");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span#LastName-error")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(),"Last name is required.");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span#Email-error")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(),"Email is required.");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span#Password-error")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(),"Password is required.");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(),"Password is required.");
		
	}

	@Test
	public void Register_02_Invalid_Email() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Wick");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("John@gmail@com");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span#Email-error")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(),"Wrong email");
	}

	@Test
	public void Register_03_Invalid_Password() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Wick");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(getEmailAddress());
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span#Password-error")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(),"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_04_Incorect_ConfirmPassword() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Wick");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(getEmailAddress());
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123457");
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(),"The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Register_Success() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Wick");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(getEmailAddress());
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.result")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");
	}
	
	public String getEmailAddress() {
		Random rand = new Random();
		return "john" + rand.nextInt() + "@gmail.com";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
