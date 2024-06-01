package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.Set;

public class BasePage {
    // Chứa những hàm dùng chung cho cả layer pageObject

    public static BasePage getBasePage(){
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
        return new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent());
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

    public WebElement getElementByXpath(WebDriver driver, String xpathLocator) {
        return driver.findElement(getByXpath(xpathLocator));
    }

    public List<WebElement> getListElement(WebDriver driver, String xpathLocator) {
        return driver.findElements(getByXpath(xpathLocator));
    }

    public void clickToElement(WebDriver driver, String xpathLocator) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator))).click();
    }

    public void sendkeyToElement(WebDriver driver, String xpathLocator, String content) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator))).clear();
        getElementByXpath(driver, xpathLocator).sendKeys(content);
    }

    public void selectItemInDropdown(WebDriver driver, String xpathLocator, String itemText) {
        new Select(getElementByXpath(driver, xpathLocator)).selectByVisibleText(itemText);
    }

    public String getFirstSelectedOptionText(WebDriver driver, String xpathLocator) {
        return new Select(getElementByXpath(driver, xpathLocator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String xpathLocator){
        return new Select(getElementByXpath(driver, xpathLocator)).isMultiple();
    }

    public void selectedCustomDropdown(WebDriver driver, String xpathParent, String xpathChild, String expectedText){
        getElementByXpath(driver, xpathParent).click();

        List<WebElement> allItems = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(xpathChild)));

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

    public String getElementText(WebDriver driver, String xpathLocator) {
        return getElementByXpath(driver, xpathLocator).getText();
    }

    public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName){
        return getElementByXpath(driver, xpathLocator).getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName){
        return getElementByXpath(driver, xpathLocator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbdColor){
        return Color.fromString(rgbdColor).asHex().toUpperCase();
    }

    public int getListElementSize(WebDriver driver, String xpathLocator){
        return getListElement(driver, xpathLocator).size();
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String xpathLocator){
        if (!getElementByXpath(driver, xpathLocator).isSelected()){
            clickToElement(driver, xpathLocator);
        }
    }

    public void unCheckToCheckbox(WebDriver driver, String xpathLocator){
        if (getElementByXpath(driver, xpathLocator).isSelected()){
            clickToElement(driver, xpathLocator);
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String xpathLocator){
        return getElementByXpath(driver, xpathLocator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String xpathLocator){
        return getElementByXpath(driver, xpathLocator).isSelected();
    }

    public boolean isElementEnable(WebDriver driver, String xpathLocator){
        return getElementByXpath(driver, xpathLocator).isEnabled();
    }

    public void switchToFrame(WebDriver driver, String xpathLocator){
        driver.switchTo().frame(getElementByXpath(driver, xpathLocator));
    }

    public void switchToDefault(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String xpathLocator){
        new Actions(driver).moveToElement(getElementByXpath(driver, xpathLocator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String xpathLocator){
        new Actions(driver).doubleClick(getElementByXpath(driver, xpathLocator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String xpathLocator){
        new Actions(driver).contextClick(getElementByXpath(driver, xpathLocator)).perform();
    }

    public void dragAndDropToElement(WebDriver driver, String sourceXpath, String targetXpath){
        new Actions(driver).dragAndDrop(getElementByXpath(driver, sourceXpath), getElementByXpath(driver, targetXpath)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String xpathLocator, Keys key){
        new Actions(driver).sendKeys(getElementByXpath(driver, xpathLocator), key).perform();
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

    public void hightlightElement(WebDriver driver, String xpathLocator) {
        WebElement element = getElementByXpath(driver, xpathLocator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String xpathLocator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElementByXpath(driver, xpathLocator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(WebDriver driver, String xpathLocator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElementByXpath(driver, xpathLocator));
    }

    public void scrollToElementOnDown(WebDriver driver, String xpathLocator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElementByXpath(driver, xpathLocator));
    }

    public void setAttributeInDOM(WebDriver driver, String xpathLocator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElementByXpath(driver, xpathLocator));
    }

    public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElementByXpath(driver, xpathLocator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String xpathLocator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElementByXpath(driver, xpathLocator));
    }

    public String getAttributeInDOM(WebDriver driver, String xpathLocator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElementByXpath(driver, xpathLocator));
    }

    public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElementByXpath(driver, xpathLocator));
    }

    public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElementByXpath(driver, xpathLocator));
    }

    public void waiForElementVisible(WebDriver driver, String xpathLocator){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
    }

    public void waiForListElementVisible(WebDriver driver, String xpathLocator){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
    }

    public void waiForListElementClickAble(WebDriver driver, String xpathLocator){
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
    }

    public void waiForListElementInVisible(WebDriver driver, String xpathLocator){
        new WebDriverWait(driver,30).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
    }

}
