package herokuapptests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MavenRunTest {

    @Test
    public void mavenRunTest() {
        String expectedText = "AnotherText";
        Assert.assertEquals(System.getProperty("testVariable"), expectedText, "Text is not the same as expected");
    }
}
