package com.nopcommerce.users;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGenaratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.admin.AdminDashboardPageObject;
import pageObject.admin.AdminLoginPageObject;
import pageObject.users.*;

public class Level_10_Refactor_By_Locator_Global_Constant extends BaseTest {

    private WebDriver driver;

    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private AddressesPageObject addressesPage;
    private DownloadableProductsPageObject downloadablePage;
    private RewardPointsPageObject rewardPointsPage;

    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;

    private String userUrl = GlobalConstants.DEV_USER_URL;
    private String adminUrl = GlobalConstants.DEV_ADMIN_URL;
    String emailAddress = getEmailAddress();

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {

        driver = getBrowserDriver(browserName, userUrl);

        homePage = PageGenaratorManager.getHonePage(driver);
    }

    @Test
    public void User_01_Register() {

        registerPage = homePage.clickRegisterLink();
        registerPage.clearFirstNameTextbox();
        registerPage.clearLastNameTextbox();
        registerPage.clearEmailTextbox();
        registerPage.clearPasswordTextbox();
        registerPage.clearConfirmPasswordTextbox();


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

    @Test
    public void User_02_Switch_Url(){
        // Hiện đang ở Customer Page
        // Logout ra từ trang User
        homePage = customerPage.clickUserLogout(driver);
        // Qua trang Admin
        homePage.openUrl(driver, adminUrl);
        adminLoginPage = PageGenaratorManager.getAdminLoginPage(driver);
            // Login thành công
        adminDashboardPage = adminLoginPage.loginAsAdmin(GlobalConstants.ADMIN_USERNAME,GlobalConstants.ADMIN_PASSWORD);
        Assert.assertTrue(adminDashboardPage.isPageLoadedSuccess(driver));
            // Logout ra từ trang Admin
        adminLoginPage = adminDashboardPage.clickAdminLogout(driver);

        // Qua trang User
        adminLoginPage.openUrl(driver, userUrl);
        homePage = PageGenaratorManager.getHonePage(driver);

        // Login user
        loginPage = homePage.clickLoginLink();
        homePage = loginPage.loginWithUser(emailAddress, "123456");

    }

    @AfterClass
    public void afterClass() {
        quitBrowserDriver();
    }

}
