package baitap;

/*

Test Steps:

Step 1. Goto http://live.techpanda.org/

Step 2. Click on �MOBILE� menu

Step 3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

Step 4. Click on Sony Xperia mobile

Step 5. Read the Sony Xperia mobile from detail page.

Step 6. Compare Product value in list and details page should be equal ($100).

*/

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

@Test
public class TestCase02 {
    public static double fromPriceText(String priceText) {
        StringBuilder builder = new StringBuilder(priceText);
        builder.deleteCharAt(0);
        return Double.parseDouble(builder.toString());
    }

    public static void test02() throws IOException {
        // Step 1. Go to http://live.techpanda.org/

        WebDriver chromeDriver = driverFactory.getChromeDriver();
        chromeDriver.get("http://live.techpanda.org/");

        // Step 2. Click on �MOBILE� menu

        WebElement element = chromeDriver.findElement(By.linkText("MOBILE"));
        element.click();

        // Step 3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

        System.out.println("Step 3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)");
        element = chromeDriver.findElement(By.xpath("//span[contains(text(),'$100.00')]"));
        String listPriceText = element.getText();
        double listPrice = fromPriceText(listPriceText);
        System.out.println("Xperia price on the list: " + listPriceText);

        // Step 4. Click on Sony Xperia mobile

        element = chromeDriver.findElement(By.linkText("SONY XPERIA"));
        element.click();

        // Step 5. Read the Sony Xperia mobile from detail page.

        System.out.println("Step 5. Read the Sony Xperia mobile from detail page.");
        element = chromeDriver.findElement(By.xpath("//span[@class='h1']"));
        System.out.println("Name: " + element.getText());
        element = chromeDriver.findElement(By.xpath("//span[@class='price']"));
        System.out.println("Price: " + element.getText());
        element = chromeDriver.findElement(By.xpath("//a[normalize-space()='752 Review(s)']"));
        System.out.println("Review: " + element.getText());
        element = chromeDriver.findElement(By.xpath("//span[@class='value']"));
        System.out.println("Status: " + element.getText());
        element = chromeDriver.findElement(By.xpath("//div[contains(text(),'Sony XperiaE')]"));
        System.out.println("Description: " + element.getText());

        // Step 6. Compare Product value in list and details page should be equal ($100).

        System.out.println("Step 6. Compare Product value in list and details page should be equal ($100).");
        element = chromeDriver.findElement(By.xpath("//span[@class='price']"));
        double detailPrice = fromPriceText(element.getText());
        System.out.println("List price = detail price? " + (listPrice == detailPrice));

        // Take screen shot
//        String dirPath = "screenshots";
//        File dir = new File(dirPath);
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//        TakesScreenshot takesScreenshot = (TakesScreenshot) chromeDriver;
//        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
//        File f = new File(dirPath + "/" + screenshot.getName());
//        FileHandler.copy(screenshot, f);

        // Close Chrome window

        chromeDriver.quit();
    }
}
