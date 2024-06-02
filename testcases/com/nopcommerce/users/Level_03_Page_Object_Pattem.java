package com.nopcommerce.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.CustomerPageObject;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.RegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_03_Page_Object_Pattem {

    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");

    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePageObject(driver);
        loginPage = new LoginPageObject(driver);
        customerPage = new CustomerPageObject(driver);
        registerPage = new RegisterPageObject(driver);
    }

    @Test
    public void Register_01_Empty_Data() {

        homePage.clickRegisterLink();
        registerPage.clickRegisterButton();

        Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
    }

    @Test
    public void Register_02_Invalid_Email() {

        registerPage.clearFirstNameTextbox();
        registerPage.clearLastNameTextbox();
        registerPage.clearEmailTextbox();
        registerPage.clearPasswordTextbox();
        registerPage.clearConfirmPasswordTextbox();

        registerPage.sendKeyToFirstNameTextbox("John");
        registerPage.sendKeyToLastNameTextbox("Wick");
        registerPage.sendKeyToEmailTextbox("John@gmail@com");
        registerPage.sendKeyToPasswordTextbox("123456");
        registerPage.sendKeyToConfirmPasswordTextbox("123456");

        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isEmailErrorMessageDisplayed());
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Please enter a valid email address.");
    }

    @Test
    public void Register_04_Incorect_ConfirmPassword() {

        registerPage.clearFirstNameTextbox();
        registerPage.clearLastNameTextbox();
        registerPage.clearEmailTextbox();
        registerPage.clearPasswordTextbox();
        registerPage.clearConfirmPasswordTextbox();

        registerPage.sendKeyToFirstNameTextbox("John");
        registerPage.sendKeyToLastNameTextbox("Wick");
        registerPage.sendKeyToEmailTextbox(getEmailAddress());
        registerPage.sendKeyToPasswordTextbox("123456");
        registerPage.sendKeyToConfirmPasswordTextbox("123457");

        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isConfirmPasswordErrorMessageDisplayed());
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");
    }

    @Test
    public void Register_05_Register_Success() {

        registerPage.clearFirstNameTextbox();
        registerPage.clearLastNameTextbox();
        registerPage.clearEmailTextbox();
        registerPage.clearPasswordTextbox();
        registerPage.clearConfirmPasswordTextbox();

        String emailAddress = getEmailAddress();

        registerPage.sendKeyToFirstNameTextbox("John");
        registerPage.sendKeyToLastNameTextbox("Wick");
        registerPage.sendKeyToEmailTextbox(emailAddress);
        registerPage.sendKeyToPasswordTextbox("123456");
        registerPage.sendKeyToConfirmPasswordTextbox("123456");

        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isRegisterSuccessMessageDisplayed());
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        registerPage.clickContinueButton();

        homePage.clickLogOutLink();

        homePage.clickLoginLink();
        loginPage.sendKeyToEmailTextbox(emailAddress);
        loginPage.sendKeyToPasswordTextbox("123456");
        loginPage.clickLoginButton();

        homePage.clickMyAccountLink();

        Assert.assertEquals(customerPage.getFirstNameAttributeValue("value"), "John");
        Assert.assertEquals(customerPage.getLastNameAttributeValue("value"), "Wick");
        Assert.assertEquals(customerPage.getEmailAttributeValue("value"), emailAddress);
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
