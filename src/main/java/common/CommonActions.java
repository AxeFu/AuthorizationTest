package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CommonActions {

    public static WebDriver createDriver() {
        switch (Config.PLATFORM_AND_BROWSER) {
            case "win_chrome": {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriverwin.exe");
                break;
            }
            case "mac_chrome": {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedrivermac");
                break;
            }
        }
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Config.IMPLICIT_WAIT);
        return driver;
    }
}
