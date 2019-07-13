package herokuapptests;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Set;

public class LoginWithCookieTest extends BaseTest {

    private static final String HEROKUAPP_LOGIN_URL = "http://the-internet.herokuapp.com/login";
    private static final String HEROKUAPP_SECURE_URL = "http://the-internet.herokuapp.com/secure";
    private static final String USERNAME = "tomsmith";
    private static final String PSWD = "SuperSecretPassword!";
    private static final String SUCCESS_MESSAGE = "Welcome to the Secure Area. When you are done click logout below.";

    @Test
    public void loginWithCookieTest() throws MalformedURLException {
        driver.get(HEROKUAPP_LOGIN_URL);
        log.info("Herokuapp is opened");

        driver.findElement(By.id("username")).sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PSWD);
        driver.findElement(By.tagName("button")).click();
        log.info("Checking user is logged in");
        Assert.assertEquals(driver.findElement(By.className("subheader")).getText(), SUCCESS_MESSAGE, "User is not logged in");

        Set<Cookie> authorizedUserCookies = driver.manage().getCookies();

        driver = Browser.getInstance().reinitBrowser();

        driver.get(HEROKUAPP_LOGIN_URL);
        driver.manage().deleteAllCookies();

        for (Cookie userCookie : authorizedUserCookies) {
            driver.manage().addCookie(userCookie);
        }

        driver.navigate().to(HEROKUAPP_SECURE_URL);

        Assert.assertEquals(driver.findElement(By.className("subheader")).getText(), SUCCESS_MESSAGE, "User is not logged in");
    }
}
