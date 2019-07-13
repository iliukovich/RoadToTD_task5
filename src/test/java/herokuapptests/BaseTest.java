package herokuapptests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Logger log = LogManager.getLogger();

    @BeforeMethod
    public void setUpBrowser() throws MalformedURLException {
        log.info("Browser is starting");
        driver = Browser.getInstance().getDriver();
    }

    @AfterMethod
    public void tearDown() throws MalformedURLException {
        Browser.getInstance().getDriver().close();
        log.info("Browser is closed");
    }
}
