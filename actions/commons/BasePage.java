package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.admin.AdminLoginPageObject;
import pageObject.users.HomePageObject;
import pageUis.users.BasePageUi;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    // Chứa những hàm dùng chung cho cả layer pageObject

    private long longTimeout = GlobalConstants.LONG_TIMEOUT;

    public static BasePage getBasePage() {
        return new BasePage();
    }

    public void openUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
//        return new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
        return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        // driver.switchTo().alert().accept();
        waitForAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver) {
        // driver.switchTo().alert().dismiss();
        waitForAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        // return driver.switchTo().alert().getText();
        return waitForAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String content) {
        // driver.switchTo().alert().sendKeys(content);
        waitForAlertPresence(driver).sendKeys(content);
    }

    public String getCurrentWindowID(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public void switchWindowByID(WebDriver driver, String expectedId) {
        Set<String> allIds = driver.getWindowHandles();
        for (String id : allIds) {
            if (!id.equals(expectedId)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allIds = driver.getWindowHandles();
        for (String id : allIds) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutExpected(WebDriver driver, String expectedId) {
        Set<String> allIds = driver.getWindowHandles();
        for (String id : allIds) {

            if (!id.equals(expectedId)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(expectedId);
        }
    }

    public void sleepInSeconds(int timmeoutInSecond) {
        try {
            Thread.sleep(timmeoutInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public By getByXpath(String xpathLocator) {
        return By.xpath(xpathLocator);
    }

    public By getByLocator(String locator){
        By by = null;

        if (locator.startsWith("xpath=") || locator.startsWith("XPath=") || locator.startsWith("XPATH=") || locator.startsWith("Xpath=")){
            by = By.xpath(locator.substring(6));
        } else if (locator.startsWith("css=") || locator.startsWith("CSS=") || locator.startsWith("Css=")) {
            by = By.cssSelector(locator.substring(4));
        } else if (locator.startsWith("id=") || locator.startsWith("ID=") || locator.startsWith("Id=")) {
            by = By.id(locator.substring(3));
        } else if (locator.startsWith("name=") || locator.startsWith("Name=") || locator.startsWith("NAME=")) {
            by = By.name(locator.substring(5));
        } else if (locator.startsWith("class=") || locator.startsWith("Class=") || locator.startsWith("CLass=")) {
            by = By.className(locator.substring(6));
        } else if (locator.startsWith("Tagname=") || locator.startsWith("TAGNAME=") || locator.startsWith("tagname=") || locator.startsWith("tagName=")) {
            by = By.tagName(locator.substring(8));
        } else
            throw new RuntimeException("Locator is invalid");

        return by;
    }

    public WebElement getElementByLocator(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElementByLocator(driver, locator).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String content) {
        clearToElement(driver, locator);
        getElementByLocator(driver, locator).sendKeys(content);
    }

    public void clearToElement(WebDriver driver, String locator) {
        getElementByLocator(driver, locator).clear();
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String itemText) {
        new Select(getElementByLocator(driver, locator)).selectByVisibleText(itemText);
    }

    public String getFirstSelectedOptionText(WebDriver driver, String locator) {
        return new Select(getElementByLocator(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getElementByLocator(driver, locator)).isMultiple();
    }

    public void selectedCustomDropdown(WebDriver driver, String locatorParent, String locatorChild, String expectedText) {
        getElementByLocator(driver, locatorParent).click();

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locatorChild)));

        for (WebElement tempElement : allItems) {
            if (tempElement.getText().equals(expectedText)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
                sleepInSeconds(1);
                tempElement.click();
                sleepInSeconds(1);
                break;
            }
        }
    }

    public String getElementText(WebDriver driver, String locator) {
        return getElementByLocator(driver, locator).getText();
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getElementByLocator(driver, locator).getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
        return getElementByLocator(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbdColor) {
        return Color.fromString(rgbdColor).asHex().toUpperCase();
    }

    public int getListElementSize(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
        if (!getElementByLocator(driver, locator).isSelected()) {
            clickToElement(driver, locator);
        }
    }

    public void unCheckToCheckbox(WebDriver driver, String locator) {
        if (getElementByLocator(driver, locator).isSelected()) {
            clickToElement(driver, locator);
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElementByLocator(driver, locator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElementByLocator(driver, locator).isSelected();
    }

    public boolean isElementEnable(WebDriver driver, String locator) {
        return getElementByLocator(driver, locator).isEnabled();
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getElementByLocator(driver, locator));
    }

    public void switchToDefault(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElementByLocator(driver, locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElementByLocator(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElementByLocator(driver, locator)).perform();
    }

    public void dragAndDropToElement(WebDriver driver, String sourceXLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getElementByLocator(driver, sourceXLocator), getElementByLocator(driver, targetLocator)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getElementByLocator(driver, locator), key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getElementByLocator(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElementByLocator(driver, locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElementByLocator(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElementByLocator(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElementByLocator(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElementByLocator(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElementByLocator(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElementByLocator(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElementByLocator(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElementByLocator(driver, locator));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForListElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitForListElementClickAble(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForListElementInVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public HomePageObject clickUserLogout(WebDriver driver){
        waitForListElementClickAble(driver, BasePageUi.USER_LOGOUT_LINK);
        clickToElement(driver, BasePageUi.USER_LOGOUT_LINK);
        return PageGenaratorManager.getHonePage(driver);
    }

    public AdminLoginPageObject clickAdminLogout(WebDriver driver){
        waitForListElementClickAble(driver, BasePageUi.ADMIN_LOGOUT_LINK);
        clickToElement(driver, BasePageUi.ADMIN_LOGOUT_LINK);
        return PageGenaratorManager.getAdminLoginPage(driver);
    }

    public boolean isPageLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }
}
