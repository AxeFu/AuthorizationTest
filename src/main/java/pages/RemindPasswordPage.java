package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.Page;

public class RemindPasswordPage extends Page {

    private final By logoLink = By.xpath("//img[contains(@src,'Logo')]/parent::a");
    private final By loginOrNameInput = By.cssSelector("input[name='loginOrEmail']");
    private final By beforeLink = By.cssSelector("a.mira-page-forgot-password-link");
    private final By sendLoginOrMailButton = By.cssSelector("button.mira-page-forgot-password-button");
    private final By successDiv = By.cssSelector("div.success");
    private final By alertDiv = By.cssSelector("div.alert");
    public RemindPasswordPage(WebDriver driver) {
        super(driver);
    }

    public RemindPasswordPage sendLoginOrName(String login) {
        getVisibleWebElement(loginOrNameInput).sendKeys(login);
        return this;
    }

    public RemindPasswordPage enterButton() {
        getVisibleWebElement(sendLoginOrMailButton).click();
        return this;
    }

    public LoginPage clickLogo() {
        getVisibleWebElement(logoLink).click();
        return new LoginPage(driver);
    }

    public LoginPage clickBeforeLink() {
        getVisibleWebElement(beforeLink).click();
        return new LoginPage(driver);
    }

    public boolean isSuccess() {
        WebElement element = getVisibleWebElement(successDiv);
        if (element == null) return false;
        return element.isDisplayed();
    }

    public boolean isAlert() {
        WebElement element = getVisibleWebElement(alertDiv);
        if (element == null) return false;
        return element.isDisplayed();
    }

}
