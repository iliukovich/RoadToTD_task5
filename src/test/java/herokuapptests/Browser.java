package herokuapptests;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static ThreadLocal<Browser> localBrowser = new ThreadLocal<>();
    private static ThreadLocal<RemoteWebDriver> localDriver = new ThreadLocal<>();

    private Browser() {

    }

    public static Browser getInstance() throws MalformedURLException {
        if (localBrowser.get() == null) {
            localBrowser.set(new Browser());
            initDriver();
        }
        return localBrowser.get();
    }

    WebDriver getDriver() {
        return localDriver.get();
    }

    private static void initDriver() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WIN10);

        localDriver.set(new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"), capabilities));
        localDriver.get().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        localDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        localDriver.get().manage().window().maximize();
    }

    public WebDriver reinitBrowser() throws MalformedURLException {
        localDriver.get().close();
        initDriver();
        return localDriver.get();
    }
}
