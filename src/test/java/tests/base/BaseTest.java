package tests.base;

import common.CommonActions;
import org.junit.After;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver = CommonActions.createDriver();

    @After
    public void clearCookieAndLocalStorage() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().deleteAllCookies();
        javascriptExecutor.executeScript("window.sessionStorage.clear()");
        driver.quit();
    }
}
