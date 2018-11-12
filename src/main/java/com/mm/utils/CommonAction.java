package com.mm.utils;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.pages.PolicyBinderPage;
import com.mm.pages.PolicyQuotePage;
import com.mm.pages.RateApolicyPage;
import com.relevantcodes.extentreports.LogStatus;

import BaseClass.CommonActionInterface;

public class CommonAction implements CommonActionInterface {

    Properties pro = new Properties();

    protected int Low = 30;
    protected int Medium = 90;
    protected int High = 180;
    String findPolicyQuotePage = "Find Policy/Quote";
    public static String url;

    public void selectValue(WebDriver driver, WebElement pageElement, String value) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(pageElement));
            Assert.assertTrue(pageElement.isDisplayed(), pageElement + " is Not displayed on screen.");
            ExtentReporter.logger.log(LogStatus.PASS, "Selected value " + value);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", pageElement);
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.FAIL, value + " element is not found.");
            Assert.assertTrue(false);
        }
    }

    public void switchToSecondFramefromFirst(WebDriver driver, String frameID) {
        List<WebElement> secondFrame = driver.findElements(By.id(frameID));
        WebDriverWait wait = new WebDriverWait(driver, High);
        wait.until(ExpectedConditions.visibilityOf(secondFrame.get(0)));
        driver.switchTo().frame(secondFrame.get(0));

    }

    public String switchToParentWindowfromotherwindow(WebDriver driver, String parentwindow) {
        String flag = "";
        sleep(2000);
        try {
            driver.switchTo().window(parentwindow);
            ExtentReporter.logger.log(LogStatus.INFO, "Control switched back to parent window.");
            flag = "true";
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false, "Error while switching to parent window.");
            ExtentReporter.logger.log(LogStatus.FAIL, "Error while switching to parent window.");
            flag = "false";
        }
        return flag;
    }

    public void switchToFrameUsingId(WebDriver driver, String idValue) {
        try {
            WebElement FrameNameEle = driver.findElement(By.id(idValue));
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(FrameNameEle));
            driver.switchTo().frame(idValue);
            ExtentReporter.logger.log(LogStatus.INFO, "Control switched to frame.");
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.INFO, "Error while switching to frame.");
        }

    }

    public Boolean switchToFrameUsingElement(WebDriver driver, WebElement element) {
        Boolean flag = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            // wait.until(ExpectedConditions.visibilityOf(element));
            driver.switchTo().frame(element);
            ExtentReporter.logger.log(LogStatus.INFO, "Control switched to frame.");
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.INFO, "Error while switching to frame.");
            flag = false;
        }
        return flag;
    }

    public void captureScreenshot(WebDriver driver, String imageFileName) {

        try {
            CommonUtilities commUtil = new CommonUtilities();
            TakesScreenshot ts = (TakesScreenshot) driver;

            File source = ts.getScreenshotAs(OutputType.FILE);

            File destination = new File(
                    "C://ScreenShotsSmokeTest//" + commUtil.getSystemDatemmddyyyy() + "_" + imageFileName + ".png");

            FileUtils.copyFile(source, destination);
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.WARNING, "Error occured while capturing screenshot");
        }

    }

    public void switchToParentWindowfromframe(WebDriver driver) {
        try {
            sleep(1000);
            driver.switchTo().defaultContent();
            ExtentReporter.logger.log(LogStatus.PASS, "User Switched to default frame.");
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.INFO, "Error while switching to default frame.");
            Assert.assertTrue(false, "Error while switching to default frame.");
        }
    }

    public String getSelectedTextFromDropDown(WebDriver driver, WebElement dropDownElement) {
        WebDriverWait wait = new WebDriverWait(driver, High);
        wait.until(ExpectedConditions.visibilityOf(dropDownElement));
        Assert.assertTrue(dropDownElement.isDisplayed());
        Select dropDownList = new Select(dropDownElement);
        String selectedDDLValue = dropDownList.getFirstSelectedOption().getText();

        return selectedDDLValue;
    }

    public String randomNumGenerator(int digit, String numbers) {
        return RandomStringUtils.random(digit, numbers);
    }

    // Enter text values in the text field
    public void enterTextIn(WebDriver driver, WebElement pageElement, String text, String textField) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(pageElement));
            Assert.assertTrue(pageElement.isDisplayed(), textField + " is not displayed on screen.");
            pageElement.sendKeys(text);
            ExtentReporter.logger.log(LogStatus.PASS, "Value " + text + " is entered in text field " + textField);
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
            Assert.assertTrue(false);
        }
    }

    // Enter the data values which are not text, like date and amount.
    public void enterDataIn(WebDriver driver, WebElement pageElement, String text, String textField) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(pageElement));
            Assert.assertTrue(pageElement.isDisplayed(), textField + " is not displayed on screen.");
            pageElement.sendKeys(text);
            ExtentReporter.logger.log(LogStatus.PASS, "Value " + text + " is entered in text field " + textField);
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
            Assert.assertTrue(false);
        }
    }

    public void clickButton(WebDriver driver, WebElement pageElement, String buttonName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.elementToBeClickable(pageElement));
            Assert.assertTrue(pageElement.isDisplayed(), buttonName + " button is Not displayed on screen.");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", pageElement);
            ExtentReporter.logger.log(LogStatus.PASS, "clicked on button / Link- " + buttonName);
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.FAIL, buttonName + " element is not found.");
        }
    }

    public void waitFor(WebDriver driver, long time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public void close(WebDriver driver) {
        driver.quit();
        ExtentReporter.logger.log(LogStatus.PASS, "Browser is closed.");
    }

    public void takeScreenShot(String pageTitle) {
        // TODO Auto-generated method stub
    }

    public void navigatetoMenuItemPage(WebDriver driver, WebElement mainMenu, WebElement menuItem) {
        WebDriverWait wait = new WebDriverWait(driver, High);
        wait.until(ExpectedConditions.visibilityOf(mainMenu));
        Assert.assertTrue(mainMenu.isDisplayed());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", mainMenu);
        Actions act = new Actions(driver);
        act.moveToElement(mainMenu).build().perform();
        js.executeScript("arguments[0].click();", menuItem);
        invisibilityOfLoader(driver);
    }

    public String getPageTitle(WebDriver driver, String expectedPageTitle) {
        invisibilityOfLoader(driver);
        sleep(4000);
        List<WebElement> getPageTitleFromPage = driver.findElements(By.xpath("//div[@class='pageTitle']"));
        WebDriverWait wait = new WebDriverWait(driver, High);
        try {
            int i = 0;
            for (i = 0; i < getPageTitleFromPage.size(); i++) {
                if ((getPageTitleFromPage.get(i).getAttribute("innerHTML").trim().equals(expectedPageTitle))) {
                    ExtentReporter.logger.log(LogStatus.PASS,
                            getPageTitleFromPage.get(i).getAttribute("innerHTML").trim()
                                    + " page is sucessfully displayed.");
                    break;
                }
            }
            // if Expected page title is not found then below code will stop the
            // test case and throw the error.
            if (i == getPageTitleFromPage.size()) {
                ExtentReporter.logger.log(LogStatus.FAIL, expectedPageTitle + " Page is NOT displayed");
                // assertTrue(false, expectedPageTitle + " Page is NOT
                // displayed");
            }
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.FAIL, expectedPageTitle + " page title is NOT displayed.");
            // assertTrue(false, expectedPageTitle + " Page is NOT displayed");
            return "false";
        }
        return "true";
    }

    public String getPageTitleWithPolicyNumber(WebDriver driver, String policyNum) {
        List<WebElement> pageheaders = driver.findElements(By.xpath("//div[@class='pageTitle']"));
        WebElement pageLoader = driver.findElement(By.xpath("//span[@class='txtOrange']"));
        WebDriverWait wait = new WebDriverWait(driver, 40);
        Assert.assertEquals(pageheaders.get(1).getAttribute("innerHTML").trim(), "Policy Folder " + policyNum,
                "Page title is not matching.");
        return null;
    }

    public String getText(WebDriver driver, WebElement pageElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(pageElement));
        } catch (Exception e) {
            Assert.assertTrue(false, pageElement + " element not found.");
        }
        return pageElement.getAttribute("innerHTML");
    }

    public void clearTextBox(WebDriver driver, WebElement pageElement, String textField) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(pageElement));
            Assert.assertTrue(pageElement.isDisplayed(), textField + " is displayed");
            pageElement.clear();
            ExtentReporter.logger.log(LogStatus.PASS, "Cleared the initial contents from field" + textField);
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
            Assert.assertTrue(false);
        }
    }

    public String getAttributeValue(WebElement pageElement, String attributeName) {

        return null;
    }

    public void click(WebDriver driver, WebElement pageElement, String ElementName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(pageElement));
            Assert.assertTrue(pageElement.isDisplayed(), ElementName + " is not displayed on screen.");
            pageElement.click();
            ExtentReporter.logger.log(LogStatus.PASS, "clicked on Element - " + ElementName);
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.FAIL, ElementName + " element is not found on page.");
            Assert.assertTrue(false, "Failed to click on " + pageElement);
        }
    }

    public void visibilityOfElement(WebDriver driver, WebElement pageElement, String text) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(pageElement));
            Assert.assertTrue(pageElement.isDisplayed(), "Logo / text " + text + " is not displayed on the page.");
            ExtentReporter.logger.log(LogStatus.PASS, "Logo / text " + text + " is displayed on page");

        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.FAIL, text + " is not displayed on page");
            Assert.assertTrue(false);
        }
    }

    public String switchToWindow(WebDriver driver) {

        ExtentReporter.logger.log(LogStatus.INFO, "Switching to the pop up window");

        String parentWindow = driver.getWindowHandle();

        Set<String> handles = driver.getWindowHandles(); // Return a set of
                                                         // window handle
        sleep(3000);
        for (String currentWindow : handles) {
            try {
                if (!currentWindow.equals(parentWindow)) {
                    driver.switchTo().window(currentWindow);
                    driver.manage().window().maximize();
                    ExtentReporter.logger.log(LogStatus.INFO, "Control is switched to pop up window");
                }
            } catch (Exception e) {
                ExtentReporter.logger.log(LogStatus.WARNING, "Error while switching control to pop up window");
            }
        }
        sleep(2000);
        return parentWindow;
    }

    // Select drop down value based on passed parameter driver, element locator
    // for drop down, DropDown Option and lable of drop down.
    public void selectDropdownByValue(WebDriver driver, WebElement element, String DropDownOption, String label) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(element));
            sleep(2000);
            Assert.assertTrue(element.isDisplayed(), element.getText() + " is not displaye on page.");
            Select Sel = new Select(element);
            Sel.selectByValue(DropDownOption);
            ExtentReporter.logger.log(LogStatus.PASS,
                    DropDownOption + " value is selected from " + label + " drop down list");

        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.FAIL,
                    DropDownOption + " value is NOT selected from " + label + " drop down list");
            Assert.assertTrue(false, DropDownOption + " value not available in drop down list");
        }
    }

    public String selectDropdownByValueFromPolicyActionDDL(WebDriver driver, WebElement element, String DropDownOption,
            String label) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertTrue(element.isDisplayed(), element.getText() + " is not displaye on page.");
            Select Sel = new Select(element);
            Sel.selectByValue(DropDownOption);
            ExtentReporter.logger.log(LogStatus.PASS,
                    DropDownOption + " value is selected from " + label + " drop down list");
            sleep(4000);
            return "true";

        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.WARNING,
                    DropDownOption + " value is NOT available in " + label + " drop down list");
            ExtentReporter.logger.log(LogStatus.INFO,
                    "Searching for new policy Number as previous policy number does not contain value: "
                            + DropDownOption + " in " + label + " DDL .");

            return "false";
        }
    }

    public void selectDropdownByVisibleText(WebDriver driver, WebElement element, String DropDownOption, String label) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(element));
            sleep(2000);
            Assert.assertTrue(element.isDisplayed(), element.getText() + " is not displaye on page.");
            Select Sel = new Select(element);
            Sel.selectByVisibleText(DropDownOption);
            ExtentReporter.logger.log(LogStatus.PASS,
                    DropDownOption + "  is selected from " + label + " drop down list");

        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.FAIL, "No value is selected from " + label + " drop down list");
            Assert.assertTrue(false, "No value is selected from " + label + " drop down list");
        }

    }

    public Boolean verifyValueFromField(WebDriver driver, WebElement pageElement, String expectedValue,
            String attributeName, String fieldName) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Medium);
            wait.until(ExpectedConditions.visibilityOf(pageElement));
            Assert.assertEquals(pageElement.getAttribute(attributeName).trim(), expectedValue,
                    "Value entered/ selected in " + fieldName + " is NOT as expected. Expected value is "
                            + expectedValue + ". And actual value is  " + pageElement.getAttribute(attributeName).trim()
                            + ".");
            ExtentReporter.logger.log(LogStatus.PASS, expectedValue,
                    "Value entered/ selected in " + fieldName + " is as expected. Expected value is " + expectedValue
                            + ". And actual value is  " + pageElement.getAttribute(attributeName).trim() + ".");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.FAIL, expectedValue,
                    "Value entered/ selected in " + fieldName + " is NOT as expected. Expected value is "
                            + expectedValue + ". And actual value is  " + pageElement.getAttribute(attributeName).trim()
                            + ".");
            return false;
        }

    }

    // This method is called to load a page during navigation through pages
    public void waitForPageLoad(WebDriver driver, int Timeout) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, Timeout);
        wait.until(pageLoadCondition);

    }

    public void waitForElementToLoad(WebDriver driver, int time, WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Medium);
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public static boolean verifyAlertDisplay(WebDriver driver) {
        try {
            sleep(2000);
            Alert alert = driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public void acceptAlert(WebDriver driver) {

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean isAlertPresent(WebDriver driver) {
        try {
            sleep(2000);
            Alert alert = driver.switchTo().alert();
            alert.accept();
            sleep(2000);
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public boolean dismissAlert(WebDriver driver) {
        try {
            sleep(2000);
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            sleep(3000);
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public String getAlertText(WebDriver driver) {
        String saveAlertText = "";
        try {
            sleep(3000);
            saveAlertText = driver.switchTo().alert().getText();
        } catch (NoAlertPresentException ex) {

        }
        return saveAlertText;
    }

    public void invisibilityOfLoader(WebDriver driver) {
        int i = 0;
        do {
            sleep(2000);
            try {
                if (verifypageloaderdisplayedornot(driver) == true) {
                    WebElement pageLoader = driver.findElement(By.xpath("//span[@class='txtOrange']"));
                    WebDriverWait wait = new WebDriverWait(driver, High);
                    wait.until(ExpectedConditions.invisibilityOf(pageLoader));
                    ExtentReporter.logger.log(LogStatus.PASS, "Page Loader disappeared sucessfully.");
                    i++;
                }
            } catch (Exception e) {
                ExtentReporter.logger.log(LogStatus.WARNING, "Page is taking longer time than usual for loading.");
                Assert.assertTrue(false);
            }
        } while (i == 3);
    }

    public void invisibilityOfProcessesingWindow(WebDriver driver) {

        try {
            if (verifypageloaderdisplayedornot(driver) == true) {
                WebElement pageLoader = driver.findElement(By.xpath("//td[@class='infomessage']"));
                sleep(2000);
                WebDriverWait wait = new WebDriverWait(driver, High);
                wait.until(ExpectedConditions.invisibilityOf(pageLoader));
                ExtentReporter.logger.log(LogStatus.PASS, "Page Loader disappeared sucessfully.");
            }
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.WARNING, "Page is taking longer time than usual for loading.");
            Assert.assertTrue(false);
        }
    }

    public Boolean copyFile(String saveFilName) {
        File source = new File("C:\\TempsaveExcel\\OnDemandInvoiceCredit.xlsx");
        File dest = new File(ExtentReporter.reportFolderPath + "\\" + saveFilName + ".xlsx");
        ExtentReporter.excelPath = ExtentReporter.excelPath
                .concat(ExtentReporter.reportFolderPath + "\\" + saveFilName + ".xlsx;");
        Boolean flag = null;
        // Below logic will verify if file present at destination if not it will
        // download file again and copy it to destination folder.
        if (source.exists() == true) {
            try {
                FileUtils.copyFile(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
                Assert.assertTrue(false, "Error while copying file from location " + source + " TO " + dest);
            }
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    public String copyPDFFile(String saveFilName) {
        String flag = null;
        File source = new File("C:\\savePDF\\verifyPDF.pdf");
        File dest = new File(ExtentReporter.reportFolderPath + "\\" + saveFilName + ".pdf");
        ExtentReporter.excelPath = ExtentReporter.excelPath
                .concat(ExtentReporter.reportFolderPath + "\\" + saveFilName + ".pdf;");
        // Below logic will verify if file present at detination if not it will
        // download file again and copy it to destination folder.
        try {
            if (source.exists() == false) {
                flag = "false";
            } else {

                FileUtils.copyFile(source, dest);
                flag = "true";
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue(false, "Error while copying file from location " + source + " TO " + dest);
        }
        return flag;
    }

    public String getDataFromExcel(String sheetName, String columnName, int rowNum, String filePath) {

        String excelFilePath = filePath;
        FileInputStream inputStream;
        String returnCellValue = null;
        try {
            inputStream = new FileInputStream(new File(excelFilePath));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet dataSheet = (XSSFSheet) workbook.getSheet(sheetName);

            Row headerRow = dataSheet.getRow(0);

            for (int cellNumber = headerRow.getFirstCellNum(); cellNumber <= headerRow.getLastCellNum()
                    - 1; cellNumber++) {
                Cell headerCell = headerRow.getCell(cellNumber);

                // System.out.println(headerCell.getStringCellValue().toLowerCase());
                if (headerCell.getStringCellValue().toLowerCase().trim().equals(columnName.toLowerCase())) {
                    Row dataRow = dataSheet.getRow(rowNum);
                    Cell dataCell = dataRow.getCell(cellNumber);
                    returnCellValue = dataCell.getStringCellValue();
                    break;
                }

            } // for loop - cellNumber
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false, "Error while reading data from excel sheet.");
            Assert.assertTrue(false);
        }
        return returnCellValue;
    }

    public void writeData(String testCaseId, String columnName, String cellValue, int rowNum, String saveDataFilePath) {

        try {
            String excelFilePath = saveDataFilePath;
            FileInputStream inputStream;

            inputStream = new FileInputStream(new File(excelFilePath));

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet dataSheet = (XSSFSheet) workbook.getSheet(testCaseId);

            Row headerRow = dataSheet.getRow(0);

            for (int cellNumber = headerRow.getFirstCellNum(); cellNumber <= headerRow.getLastCellNum()
                    - 1; cellNumber++) {
                Cell headerCell = headerRow.getCell(cellNumber);
                if (headerCell.getStringCellValue().toLowerCase().trim().equals(columnName.toLowerCase())) {
                    Row dataRow = dataSheet.getRow(rowNum);
                    Cell dataCell = dataRow.getCell(cellNumber);
                    dataCell.setCellValue(cellValue);
                    break;
                }

            } // for loop - cellNumber
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false, "Error while Writing data to excel sheet.");
        }

    }

    public boolean verifypageloaderdisplayedornot(WebDriver driver) {
        try {
            if (driver.findElement(By.xpath("//span[@class='txtOrange']")).isDisplayed()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.INFO, "Page Loader is not displayed.");
        }
        return false;
    }

    public void refreshAPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void saveOption(WebDriver driver, WebElement saveOptionBtn, WebElement saveAsDropDown, WebElement saveOKBtn,
            WebElement exitOK, String saveAsValue, String policyNo) {

        try {
            Process processkillpdf = Runtime.getRuntime()
                    .exec("TASKKILL /F /FI \"USERNAME eq " + System.getProperty("user.name") + "\" /IM savePdf.exe");
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.INFO, "Process kill for pdf did not work");
        }
        sleep(3000);
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, High);
        wait.until(ExpectedConditions.visibilityOf(saveOptionBtn));
        ExtentReporter.logger.log(LogStatus.INFO, "Click Save Options & verify Save as window displays.");
        clickButton(driver, saveOptionBtn, "Save Option");
        sleep(3000);
        invisibilityOfLoader(driver);
        WebElement iframeEle = driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]"));
        switchToFrameUsingElement(driver, iframeEle);
        getPageTitle(driver, "Save As");
        selectDropdownByValue(driver, saveAsDropDown, saveAsValue, "Selected " + saveAsValue);
        sleep(12000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Select " + saveAsValue + " Click [OK]& verify Message is closed and WIP is saved as" + saveAsValue);
        click(driver, saveOKBtn, "Save");
        sleep(3000);
        invisibilityOfLoader(driver);
        RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
        rateapolicypage.handleProducNotifyWindow(policyNo);
        switchToParentWindowfromframe(driver);
        try {
            sleep(3000);
            WebElement iframeEle1 = driver
                    .findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]"));
            switchToFrameUsingElement(driver, iframeEle1);
            sleep(1000);
            ExtentReporter.logger.log(LogStatus.INFO, "Save as Official window displays");
            clickButton(driver, exitOK, "Workflow exit OK");
            switchToParentWindowfromframe(driver);
            sleep(5000);
            invisibilityOfLoader(driver);
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.INFO, "Work flow Exit Ok button is not displayed.");
        }
    }

    public String verifypolicyNotDisplayErrorMsg(WebDriver driver) {
        String flag = null;
        try {
            WebElement policyNotFoudErrorMsg = driver.findElement(By.xpath("//td[@class='errormessage'][1]"));
            WebDriverWait wait = new WebDriverWait(driver, High);
            wait.until(ExpectedConditions.visibilityOf(policyNotFoudErrorMsg));
            if (policyNotFoudErrorMsg.isDisplayed()) {
                flag = "true";
            }
        } catch (Exception e) {
            flag = "false";
        }
        return flag;
    }

    public String policySearch(WebDriver driver, String policyNo, WebElement policySearchTxtBox, WebElement searchBtn,
            WebElement policyList) {
        String flag = null;
        WebDriverWait wait = new WebDriverWait(driver, High);
        wait.until(ExpectedConditions.visibilityOf(policySearchTxtBox));
        PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Enter in active Hospital/Facility policy number in Enter Policy # entry box, Click Search. Policy Will display");
        clearTextBox(driver, policySearchTxtBox, "Enter Policy # text field");
        enterTextIn(driver, policySearchTxtBox, policyNo, "Enter Policy # text field");
        ExtentReporter.logger.log(LogStatus.INFO, "Click search button and Verify full policy page is displayed");
        sleep(1000);
        click(driver, searchBtn, "Search button");
        sleep(3000);
        invisibilityOfLoader(driver);
        if (verifyPolicyListDispOnQAEnv(driver, policyList) == true) {
            // clickButton(driver, policyList, "First policy from Searched
            // Policies");
            Actions action = new Actions(driver);
            action.click(policyList).build().perform();
            flag = "true";
        } else if (verifypolicyNotDisplayErrorMsg(driver).equals("true")) {
            WebElement policyNotFoudErrorMsg = driver.findElement(By.xpath("//td[@class='errormessage'][1]"));
            ExtentReporter.logger.log(LogStatus.WARNING,
                    "Policy is not available, please enter another/correct policy Number.");
            ExtentReporter.logger.log(LogStatus.INFO, "Searching for backUp policy.");
            PolicyQuotePage pqp = new PolicyQuotePage(driver);
            pqp.searchBackUpPolicyUsingSearchCriteria();
            flag = "false";
        } else {
            getPageTitle(driver, "Policy Folder " + policybinderpage.policyNo());
            ExtentReporter.logger.log(LogStatus.INFO, "Policy list is displayed after policy Search");
            flag = "true";
        }
        return flag;
    }

    public String policySearchBTS_QA(WebDriver driver, String policyNo, WebElement policySearchTxtBox,
            WebElement searchBtn, WebElement policyList) {
        String flag = null;
        WebDriverWait wait = new WebDriverWait(driver, High);
        wait.until(ExpectedConditions.visibilityOf(policySearchTxtBox));
        PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Enter in active Hospital/Facility policy number in Enter Policy # entry box, Click Search. Policy Will display");
        clearTextBox(driver, policySearchTxtBox, "Enter Policy # text field");
        enterTextIn(driver, policySearchTxtBox, policyNo, "Enter Policy # text field");
        ExtentReporter.logger.log(LogStatus.INFO, "Click search button and Verify full policy page is displayed");
        sleep(1000);
        click(driver, searchBtn, "Search button");
        sleep(3000);
        invisibilityOfLoader(driver);
        if (verifyPolicyListDispOnQAEnv(driver, policyList) == true) {
            // clickButton(driver, policyList, "First policy from Searched
            // Policies");
            Actions action = new Actions(driver);
            action.click(policyList).build().perform();
            flag = "true";
        } else if (verifypolicyNotDisplayErrorMsg(driver).equals("true")) {
            WebElement policyNotFoudErrorMsg = driver.findElement(By.xpath("//td[@class='errormessage'][1]"));
            ExtentReporter.logger.log(LogStatus.WARNING,
                    "Policy is not available, please enter another/correct policy Number.");
            ExtentReporter.logger.log(LogStatus.INFO, "Searching for backUp policy.");
            PolicyQuotePage pqp = new PolicyQuotePage(driver);
            pqp.searchBackUpPolicyUsingSearchCriteriaBTS_QA();
            flag = "false";
        } else {
            getPageTitle(driver, "Policy Folder " + policybinderpage.policyNo());
            ExtentReporter.logger.log(LogStatus.INFO, "Policy list is displayed after policy Search");
            flag = "true";
        }
        return flag;
    }

    public void claimsSearch(WebDriver driver, String policyNo, WebElement policySearchTxtBox, WebElement searchBtn,
            WebElement policyList) {
        PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Enter in active Hospital/Facility policy number in Enter Policy # entry box, Click Search. Policy Will display");
        clearTextBox(driver, policySearchTxtBox, "Enter Policy # text field");
        enterTextIn(driver, policySearchTxtBox, policyNo, "Enter Policy # text field");
        ExtentReporter.logger.log(LogStatus.INFO, "Click search button and Verify full policy page is displayed");
        clickButton(driver, searchBtn, "Search button");
        sleep(1000);
        invisibilityOfLoader(driver);
        if (verifyPolicyListDispOnQAEnv(driver, policyList) == true) {
            // clickButton(driver, policyList, "First policy from Searched
            // Policies");
            Actions action = new Actions(driver);
            action.click(policyList).build().perform();
        } else if (verifypolicyNotDisplayErrorMsg(driver).equals("trrue")) {
            ExtentReporter.logger.log(LogStatus.FAIL,
                    "Policy is not available, please enter another/correct policy Number.");
            Assert.assertTrue(false, "Policy is not available, please enter another/correct policy Number.");
        } else {
            getPageTitle(driver, "Claim Folder " + policybinderpage.policyNo());
            ExtentReporter.logger.log(LogStatus.INFO, "Policy list is displayed after policy Search");
        }
    }

    public boolean verifyPolicyListDispOnQAEnv(WebDriver driver, WebElement policyList) {
        try {
            if (policyList.isDisplayed()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void sleep(final long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
