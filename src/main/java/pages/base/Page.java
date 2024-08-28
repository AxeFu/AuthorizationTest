package pages.base;

import common.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    protected WebDriver driver;
    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public Page sendKeys(CharSequence... keys) {
        Actions actions = new Actions(driver);
        actions.sendKeys(keys).build().perform();
        return this;
    }

    public WebElement getVisibleWebElement(By selector) {
        return waitElementIsVisible(driver.findElement(selector));
    }

    public WebElement waitElementIsVisible(WebElement element) {
        new WebDriverWait(driver, Config.EXPLICIT_WAIT).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public Alert waitAlertIsPresent() {
        return new WebDriverWait(driver, Config.EXPLICIT_WAIT).until(ExpectedConditions.alertIsPresent());
    }
}
