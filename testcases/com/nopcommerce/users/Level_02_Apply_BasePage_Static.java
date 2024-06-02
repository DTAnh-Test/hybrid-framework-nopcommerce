package com.nopcommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_02_Apply_BasePage_Static {

    WebDriver driver;
    BasePage basePage;
    String projectPath = System.getProperty("user.dir");


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        basePage = BasePage.getBasePage();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void Register_01_Empty_Data() {
        basePage.openUrl(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void Register_02_Invalid_Email() {
        basePage.openUrl(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Wick");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", "John@gmail@com");
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertTrue(basePage.isElementDisplayed(driver, "//span[@id='Email-error']"));
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Please enter a valid email address.");
    }

//    @Test
    public void Register_03_Invalid_Password() {
        basePage.openUrl(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Wick");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", getEmailAddress());
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");

        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertTrue(basePage.isElementDisplayed(driver, "//span[@class='field-validation-error']"));
        Assert.assertEquals(basePage.getElementText(driver, "//span[@class='field-validation-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void Register_04_Incorect_ConfirmPassword() {
        basePage.openUrl(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Wick");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", getEmailAddress());
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123457");

        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertTrue(basePage.isElementDisplayed(driver, "//span[@id='ConfirmPassword-error']"));
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
    }

    @Test
    public void Register_05_Register_Success() {
        basePage.openUrl(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Wick");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", getEmailAddress());
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertTrue(basePage.isElementDisplayed(driver, "//div[@class='result']"));
        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
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
