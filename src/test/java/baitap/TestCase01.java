/*

Test Steps

Step 1. Go to http://live.techpanda.org/

Step 2. Verify Title of the page

Step 3. Click on -> MOBILE -> menu

Step 4. In the list of all mobile , select SORT BY -> dropdown as name

Step 5. Verify all products are sorted by name

*/

package baitap;

import element.ElementController;
import org.openqa.selenium.*;
import driver.driverFactory;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

@Test
public class TestCase01 {
    public static void test01() throws IOException {
        // Step 1. Go to http://live.techpanda.org/

        WebDriver chromeDriver = driverFactory.getChromeDriver();
        chromeDriver.get("http://live.techpanda.org/");

        // Step 2. Verify Title of the page

        System.out.println("Title: " + chromeDriver.getTitle());

        // Step 3. Click on -> MOBILE -> menu

        WebElement element = chromeDriver.findElement(By.linkText("MOBILE"));
        element.click();

        // Step 4. In the list of all mobile , select SORT BY -> dropdown as name

        element = chromeDriver.findElement(By.cssSelector("select[title=\"Sort By\"]"));
        Select sortBySelect = new Select(element);
        sortBySelect.selectByVisibleText("Name");

        // Take screen shot
        String dirPath = "screenshots";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        TakesScreenshot takesScreenshot = (TakesScreenshot) chromeDriver;
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File f = new File(dirPath + "/" + screenshot.getName());
        FileHandler.copy(screenshot, f);

        // Close Chrome window

        chromeDriver.quit();
    }
}
