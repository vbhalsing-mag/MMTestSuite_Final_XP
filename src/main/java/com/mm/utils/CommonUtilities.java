package com.mm.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

public class CommonUtilities {

    public void selectDropdown(WebElement Element, String value) {

        Select Sel = new Select(Element);

        Sel.selectByValue(value);

    }

    public void verifyFormIsAdded(List<WebElement> pageElement, String formValue) {

        if (pageElement.get(pageElement.size() - 1).getAttribute("innerHTML").trim().equals(formValue.trim())) {

            ExtentReporter.logger.log(LogStatus.PASS, formValue + "is added successfully unedr Manuscript List");
        } else {

            ExtentReporter.logger.log(LogStatus.FAIL, formValue + "is Not added unedr Manuscript List");
        }
    }

    public String getSystemDateMMddyy_hhmmss() {

        DateFormat dateFormatter = new SimpleDateFormat("MMddyy_hhmmss");
        Date today = Calendar.getInstance().getTime();
        String date = dateFormatter.format(today);

        return date;
    }

    public String getSystemDatemm_dd_yyyy() {

        DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();
        String date = dateFormatter.format(today);

        return date;
    }

    public String getSystemDatemmddyyyy() {

        DateFormat dateFormatter = new SimpleDateFormat("MMddyyyy");
        Date today = Calendar.getInstance().getTime();
        String date = dateFormatter.format(today);

        return date;
    }

    public void downloadedFileExists(String fileNamePath) {

        try {

            File file = new File(fileNamePath);

            if (file.exists()) {
                System.out.println("File is available at location");
                ExtentReporter.logger.log(LogStatus.PASS, "Excel file is available at download location.");
            }

        } catch (Exception e) {
            System.out.println("File is Not available at location");
            ExtentReporter.logger.log(LogStatus.FAIL, "Excel file is Not available at download location.");
            e.printStackTrace();
        }

    }

    // -------------------------------------------------captureScreenshot-------------------------------------------------------

    public static String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "target\\ScreenShots\\" + screenshotName + ".png";

        File destination = new File(path);

        FileUtils.copyFile(source, destination);

        System.out.println("Screenshot captured");

        return path;
    }

    public static void resetZoomLevel(WebDriver driver) {
        driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
    }
}
