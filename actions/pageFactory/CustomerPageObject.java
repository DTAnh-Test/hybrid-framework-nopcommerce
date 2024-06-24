package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CustomerPageObject extends BasePageFactory{
    WebDriver driver;

    public CustomerPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // TestNG: Annotation: @BeforeClass/ @BeforeTest/ @Test/ @Parameter
    // UI: Annotation: @FindBy/ @FindBys/ @FindAll/ @CacheLookup
    @FindBy(how = How.XPATH, using = "//input[@id='FirstName']")
    WebElement firstNameTextbox;

    @FindBy(xpath = "//input[@id='LastName']")
    WebElement lastNameTextbox;

    @FindBy(css = "input#Email")
    WebElement emailTextbox;

    @FindBy(css = "button#login")
    WebElement loginButton;

    // Actions:
    public String getFirstNameAttributeValue(String attributeName){
        waitForElementVisible(driver, firstNameTextbox);
        return getElementAttribute(driver, firstNameTextbox, attributeName);
    }

    public String getLastNameAttributeValue(String attributeName){
        waitForElementVisible(driver, lastNameTextbox);
        return getElementAttribute(driver, lastNameTextbox, attributeName);
    }

    public String getEmailAttributeValue(String attributeName){
        waitForElementVisible(driver, emailTextbox);
        return getElementAttribute(driver, emailTextbox, attributeName);
    }

    public void clickToLoginButton(){
        clickToElement(driver, "button#login");
    }


}

