package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.Page;

public class LoginPage extends Page {

    private final By logoLink = By.xpath("//img[contains(@src,'Logo')]/parent::a");
    private final By loginInput = By.cssSelector("input[name='user']");
    private final By passwordInput = By.cssSelector("input[name='password']");
    private final By showPasswordButton = By.cssSelector("button[id='show_password']");
    private final By enterButton = By.cssSelector("button[id='button_submit_login_form']");
    private final By remindPasswordLink = By.cssSelector("table.links-container a.mira-default-login-page-link");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage(WebDriver driver, String url) {
        super(driver);
        open(url);
    }

    public WebElement getLogoLinkElement() {
        return getVisibleWebElement(logoLink);
    }

    public boolean passwordIsShowing() {
        WebElement element = getVisibleWebElement(passwordInput);
        return !element.getAttribute("type").equals("password");
    }

    public LoginPage sendLogin(String login) {
        getVisibleWebElement(loginInput).sendKeys(login);
        return this;
    }

    public LoginPage clearLogin() {
        getVisibleWebElement(loginInput).clear();
        return this;
    }

    public LoginPage clearPassword() {
        getVisibleWebElement(passwordInput).clear();
        return this;
    }

    public LoginPage sendPassword(String password) {
        getVisibleWebElement((passwordInput)).sendKeys(password);
        return this;
    }

    public LoginPage showPassword(boolean value) {
        if (passwordIsShowing() != value) {
            getVisibleWebElement((showPasswordButton)).click();
        }
        return this;
    }

    public boolean wrongDataWasShowing() {
        Alert alert = waitAlertIsPresent();
        if (alert != null) {
            alert.accept();
            return true;
        }
        return  false;
    }

    public LoginPage enterButton() {
        getVisibleWebElement(enterButton).click();
        return this;
    }

    public LoginPage clickLogo() {
        getVisibleWebElement(logoLink).click();
        return this;
    }

    public RemindPasswordPage remindPassword() {
        getVisibleWebElement(remindPasswordLink).click();
        return new RemindPasswordPage(driver);
    }


}
