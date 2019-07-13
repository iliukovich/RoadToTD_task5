package herokuapptests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HoversTest extends BaseTest {

    private static final String HEROKUAPP_HOVERS_URL = "http://the-internet.herokuapp.com/hovers";
    private static final String FIRST_USER_NAME = "name: user1";

    @Test
    public void hoversTest() {
        driver.get(HEROKUAPP_HOVERS_URL);
        log.info("Herokuapp is opened");

        WebElement userProfile = driver.findElement(By.xpath("//div[contains(@class, 'figure')][1]"));
        Actions hoverProfile = new Actions(driver);
        log.info("Hover over the first user profile");
        hoverProfile.moveToElement(userProfile).build().perform();

        WebElement username = driver.findElement(By.xpath("(//h5)[1]"));
        log.info("Checking username of first user profile");
        Assert.assertEquals(username.getText(), FIRST_USER_NAME, "Username is not equal to expected one.");
        log.info("Checking username location of first user profile");
        Assert.assertTrue(userProfile.getLocation().getY() < username.getLocation().getY(), "Username isn' located below the profile avatar");
    }
}
