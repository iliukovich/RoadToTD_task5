package herokuapptests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class SwitchingFramesTest extends BaseTest {

    private static final String HEROKUAPP_FRAMES_URL = "http://the-internet.herokuapp.com/nested_frames";

    @Test
    public void switchingFramesTest() {
        driver.get(HEROKUAPP_FRAMES_URL);
        log.info("Herokuapp is opened");

        List<WebElement> listOfInnerFrames = getInnerFrames();
        log.info(String.format("Count of high level frames is %d", listOfInnerFrames.size()));
        for (WebElement frame : listOfInnerFrames) {
            log.info("Switching to high level frame");
            driver.switchTo().frame(frame);
            List<WebElement> listOfInnerFramesOfCurrentFrame = getInnerFrames();
            log.info(String.format("Count of inner frames of current frame is %d", listOfInnerFramesOfCurrentFrame.size()));
            if (!listOfInnerFramesOfCurrentFrame.isEmpty()) {
                for (WebElement innerFrame : listOfInnerFramesOfCurrentFrame) {
                    log.info("Switching to inner frame");
                    driver.switchTo().frame(innerFrame);
                    logTextFromBody();
                    log.info("Switching to parent frame");
                    driver.switchTo().parentFrame();
                }
            } else {
                logTextFromBody();
            }
            log.info("Switching to main frame");
            driver.switchTo().parentFrame();
        }
    }

    private List<WebElement> getInnerFrames() {
        return driver.findElements(By.xpath("//frame"));
    }

    private void logTextFromBody() {
        String bodyText = driver.findElement(By.xpath("//body")).getText();
        log.info(String.format("Body text in frame is %s", bodyText));
    }
}
