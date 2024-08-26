package tests;

import common.WordBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import pages.LoginPage;
import pages.RemindPasswordPage;
import tests.base.BaseTest;

public class AuthorizationTest extends BaseTest {

    public final String path = "https://lmslite47vr.demo.mirapolis.ru/mira";
    public final String trueLogin = "fominaelena";
    public final String truePassword = "1P73BP4Z";

    @Test
    public void logoRedirectTest() {
        LoginPage page = new LoginPage(driver);
        page.open(path);

        page.clickLogo();

        Assert.assertNotNull(page.getLogoLinkElement());
    }

    @Test
    public void incorrectInputTest() {
        LoginPage page = new LoginPage(driver);
        page.open(path);

        page.sendLogin(WordBuilder.getRandomWord(trueLogin.length() + 1))
                .sendPassword(WordBuilder.getRandomWord(truePassword.length() + 1))
                .enterButton();

        Assert.assertTrue(page.wrongDataWasShowing());

        page.sendLogin(trueLogin)
                .sendPassword(WordBuilder.getRandomWord(truePassword.length()+1))
                .enterButton();

        Assert.assertTrue(page.wrongDataWasShowing());

        page.sendLogin(WordBuilder.getRandomWord(trueLogin.length()+1))
                .sendPassword(truePassword)
                .enterButton();

        Assert.assertTrue(page.wrongDataWasShowing());
    }

    @Test
    public void correctInputTest() {
        LoginPage page = new LoginPage(driver);
        page.open(path);

        page.sendLogin(trueLogin)
                .sendPassword(truePassword)
                .enterButton();

        boolean alertWasShown = false;
        try {
            alertWasShown = page.wrongDataWasShowing();
        } catch (TimeoutException ignore) {}

        Assert.assertFalse(alertWasShown);
    }

    @Test
    public void showPasswordButtonTest() {
        LoginPage page = new LoginPage(driver);
        page.open(path);

        page.sendPassword(truePassword);

        Assert.assertFalse(page.passwordIsShowing());

        page.showPassword(true);
        Assert.assertTrue(page.passwordIsShowing());

        page.showPassword(false);
        Assert.assertFalse(page.passwordIsShowing());
    }

    @Test
    public void remindPasswordPageTest() {
        RemindPasswordPage page = new LoginPage(driver, path).remindPassword();
        page.sendLoginOrName(WordBuilder.getRandomWord(trueLogin.length() + 1));
        page.enterButton();
        Assert.assertTrue(page.isAlert());

        page.sendLoginOrName(trueLogin);
        page.enterButton();
        Assert.assertTrue(page.isSuccess());
    }

    @Test
    public void remindPasswordToLoginPageRedirectTest() {
        RemindPasswordPage passwordPage = new LoginPage(driver, path).remindPassword();

        LoginPage loginPage = passwordPage.clickBeforeLink();
        loginPage.remindPassword();
        Assert.assertTrue(true);

        LoginPage loginPage2 = passwordPage.clickLogo();
        Assert.assertNotNull(loginPage2.getLogoLinkElement());
    }

    @Test
    public void keyboardNavigationTest() {
        LoginPage page = new LoginPage(driver);
        page.open(path);

        page.sendKeys(Keys.TAB, Keys.ENTER);

        page.sendKeys(Keys.TAB, Keys.TAB, trueLogin, Keys.TAB, truePassword, Keys.ENTER);

        boolean alertWasShown = false;
        try {
            alertWasShown = page.wrongDataWasShowing();
        } catch (TimeoutException ignore) {}

        Assert.assertFalse(alertWasShown);
    }
}
