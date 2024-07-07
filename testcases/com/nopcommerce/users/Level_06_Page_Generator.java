package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.*;

public class Level_06_Page_Generator extends BaseTest {

    private WebDriver driver;

    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;

    @Parameters({"browser", "userUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);

        homePage = PageGenaratorManager.getHonePage(driver);
    }

    @Test
    public void Register_01_Empty_Data() {
        registerPage = homePage.clickRegisterLink();
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

        homePage = registerPage.clickContinueButton();

        homePage.clickLogOutLink();

        loginPage = homePage.clickLoginLink();
        loginPage.sendKeyToEmailTextbox(emailAddress);
        loginPage.sendKeyToPasswordTextbox("123456");
        homePage = loginPage.clickLoginButton();

        customerPage = homePage.clickMyAccountLink();

        Assert.assertEquals(customerPage.getFirstNameAttributeValue("value"), "John");
        Assert.assertEquals(customerPage.getLastNameAttributeValue("value"), "Wick");
        Assert.assertEquals(customerPage.getEmailAttributeValue("value"), emailAddress);
    }

    @AfterClass
    public void afterClass() {
        quitBrowserDriver();
    }

}
