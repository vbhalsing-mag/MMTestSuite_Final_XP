package com.mm.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.dto.CincomPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.ExtentReporter;
import com.mm.utils.TestCaseDetails;
import com.relevantcodes.extentreports.LogStatus;

public class CincomPage extends CommonAction {

    WebDriver driver;
    CincomPageDTO cincomedto;

    @FindBy(xpath = "//table[@id='coverageListGrid']//tbody//td//div[@id='CPRODUCTCOVERAGEDESC']")
    List<WebElement> coverageList;

    @FindBy(xpath = "//input[@id = 'PM_MANU_PUP'] | //input[@id='PM_QT_MANU_PUP']")
    WebElement optionalFormBtn;

    @FindBy(xpath = "//table[@id='maintainManuscriptListGrid']")
    WebElement manuscriptList;

    @FindBy(id = "PM_MANU_DELETE")
    WebElement manuscriptPageDeleteBtn;

    @FindBy(id = "PM_MANU_ADD")
    WebElement manuscriptPageAddBtn;

    @FindBy(xpath = "//table[@id='maintainManuscriptListGrid']//tr")
    List<WebElement> manuscriptInformationTypeListGrid;

    @FindBy(xpath = "//table[@id='selectManuscriptGrid']//tr//div[@id='CSHORTDESCRIPTION']")
    List<WebElement> manuscriptAddListformName;

    @FindBy(xpath = "//table[@id='selectManuscriptGrid']//tr//input[@name='chkCSELECT_IND']")
    List<WebElement> manuscriptAddListformNameChkBox;

    @FindBy(id = "PM_SEL_MANU_DONE")
    WebElement manuscriptAddListDoneBtn;

    @FindBy(id = "PM_MANU_SAVE")
    WebElement manuscriptPageSaveBtn;

    @FindBy(id = "PM_MANU_CLOSE")
    WebElement manuscriptPageCloseBtn;

    @FindBy(xpath = "//span[@class='dragbox_main_head_class']")
    WebElement spellchkBoxHeading;

    @FindBy(id = "PM_MANU_DTENTRY")
    WebElement dataEntryBtn;

    @FindBy(xpath = "//div[@id='loginPanel divpanel']")
    WebElement errorOnCinCOmPage;

    @FindBy(id = "ictextField")
    WebElement titleHFLHPLCHGGE;

    @FindBy(name = "ictextField")
    WebElement titleHFLHPLCHGGETextField;

    @FindBy(name = "paraSel")
    WebElement freeFormCHGGEBeginChkBox;

    @FindBy(xpath = "//button[@role='presentation']//i[@class='mce-ico mce-i-paste']")
    WebElement saveIconOnCincom;

    @FindBy(xpath = "//body[@id = 'tinymce']//p")
    WebElement freeFormCHGGEBeginTxtField;

    @FindBy(xpath = "//iframe[contains(@title,'Rich Text Area')]")
    WebElement cincomIFrame;

    @FindBy(name = "IgnoreAllBtn")
    WebElement ignoreAllBtn;

    @FindBy(xpath = "//input[contains(@id,'delopt')]")
    WebElement DelOptions;

    @FindBy(id = "Deliver")
    WebElement deliverBtn;

    @FindBy(xpath = "//div[@class='noerror']")
    WebElement sucessMsg;

    @FindBy(name = "additionalText")
    WebElement addText;

    @FindBy(xpath = "//div[@id='CFORMCODELOVLABEL']")
    List<WebElement> manuScriptForm;

    @FindBy(xpath = "//input[@name='paraSel' and @class='checked']")
    WebElement formCheckbox;

    public CincomPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        cincomedto = new CincomPageDTO(TestCaseDetails.testDataDictionary);
    }

    public RateApolicyPage cincomFlow(String PolicyNo) {
        for (int j = 0; j < cincomedto.coverage.size(); j++) {
            for (int i = 0; i < coverageList.size(); i++) {
                if (coverageList.get(i).getAttribute("innerHTML").equals(cincomedto.coverage.get(j))) {
                    clickButton(driver, coverageList.get(i), coverageList.get(i).getAttribute("innerHTML"));
                    ExtentReporter.logger.log(LogStatus.INFO, "Select" + cincomedto.coverage.get(j) + " Coverage.");
                    break;
                }
            }
        }
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click [Optional Forms] & verify Manuscript Information window displays");
        sleep(4000);
        clickButton(driver, optionalFormBtn, "Optional Form");
        sleep(3000);
        invisibilityOfLoader(driver);
        switchToFrameUsingElement(driver,
                driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Add] & verify Add Manuscript window opens.");
        clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
        sleep(4000);
        invisibilityOfLoader(driver);
        switchToFrameUsingElement(driver,
                driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
        for (int k = 0; k < cincomedto.phase.size(); k++) {
            for (int l = 0; l < manuscriptAddListformName.size(); l++) {
                if (manuscriptAddListformName.get(l).getAttribute("innerHTML").equals(cincomedto.phase.get(k))) {
                    clickButton(driver, manuscriptAddListformNameChkBox.get(l),
                            "check Box for " + cincomedto.phase.get(k));
                    ExtentReporter.logger.log(LogStatus.INFO, "Select " + cincomedto.phase.get(k) + ", Click done.");
                    break;
                }
            }
            clickButton(driver, manuscriptAddListDoneBtn, "Done");
            sleep(4000);
            switchToParentWindowfromframe(driver);
            switchToFrameUsingElement(driver,
                    driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
            ExtentReporter.logger.log(LogStatus.INFO, "Enter additional text: " + cincomedto.phase.get(k) + "");
            enterTextIn(driver, addText, cincomedto.phase.get(k) + " form added.", "Aditional Text");
            ExtentReporter.logger.log(LogStatus.INFO, "Select [Save]");
            sleep(3000);
            clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
            sleep(4000);
            ExtentReporter.logger.log(LogStatus.INFO, "Select Form " + cincomedto.phase.get(k));

            for (int formCount = 0; formCount < manuScriptForm.size(); formCount++) {

                if (manuScriptForm.get(formCount).getAttribute("innerHTML").trim()
                        .equalsIgnoreCase(cincomedto.phase.get(k))) {
                    clickButton(driver, manuScriptForm.get(formCount), cincomedto.phase.get(k));
                    break;
                }
            }
            ExtentReporter.logger.log(LogStatus.INFO,
                    "Click the Data Entry button & verify Manuscript Information window displays");
            clickButton(driver, dataEntryBtn, "Data Entry");
            sleep(8000);
            // Below AutoIT code will accept the security warning window for
            // cincom page
            try {
                String[] executionPath = {
                        System.getProperty("user.dir") + "\\src\\main\\java\\autoItScripts\\SecurityWindowHandle.exe" };
                Runtime.getRuntime().exec(executionPath).waitFor(30, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
                ExtentReporter.logger.log(LogStatus.WARNING,
                        "Security Warning window has not appeared Or Error handling window");
            }

            String parentWindowId = switchToWindow(driver);
            sleep(2000);
            // Below code will verify if there is an error on cincom page then
            // it will close the current window and fail the test cases else it
            // will execute CINCOM page flow.
            try {
                if (verifyErrorWhileOpeningCinComPage() == true) {
                    ExtentReporter.logger.log(LogStatus.FAIL, "Error while opening Cincom Page.");
                    throw new Exception();
                } else {
                    try {
                        sleep(2000);
                        ExtentReporter.logger.log(LogStatus.INFO, "Enter in Title_HFLHPLCHGGE: 'Automated Test CHGGE'");
                        titleHFLHPLCHGGE.clear();
                        enterTextIn(driver, titleHFLHPLCHGGE, "Automated Test CHGGE", " Cincom Title");
                        ExtentReporter.logger.log(LogStatus.INFO,
                                "Select the Free_Form_CHGGE_Begin check box an enter the following text in the box: \r\n"
                                        + "\r\n" + "'Automated Test Case {Today's Date}\r\n" + "This test is to\r\n"
                                        + "\r\n" + "Adds the form \r\n" + "Enter data entry \r\n"
                                        + "Verify Bulletpoints display as entered. '");
                        // If the Free Form CHGGE check box is unchecked, then
                        // select check box.
                        if (freeFormCHGGEBeginChkBox.getAttribute("class").trim().equalsIgnoreCase("unchecked")) {
                            clickButton(driver, freeFormCHGGEBeginChkBox, "freeFormCHGGEBeginChkBox");
                        }
                        sleep(2000);
                        // CommonUtilities comUtil = new CommonUtilities();
                        // Select Font family and font size
                        ExtentReporter.logger.log(LogStatus.INFO, "Select Font Family: Arial Select Font Size: 10pt");
                        JavascriptExecutor executor = (JavascriptExecutor) driver;
                        executor.executeScript("document.getElementById('mceu_43-open').innerHTML='Arial';", "");
                        executor.executeScript("document.getElementById('mceu_44-open').innerHTML='10pt';", "");
                        sleep(2000);
                        // To get cursor position
                        titleHFLHPLCHGGETextField.click();
                        // Enter required test in the text field using AutoIT
                        String[] executionPath = {
                                System.getProperty("user.dir") + "\\src\\main\\java\\autoItScripts\\Cincom.exe" };
                        Runtime.getRuntime().exec(executionPath).waitFor(20, TimeUnit.SECONDS);
                        sleep(3000);
                        ExtentReporter.logger.log(LogStatus.INFO,
                                "Click [Delivery Options] & verify Check Spelling window will appear.");
                        clickButton(driver, DelOptions, "Delivery Options");
                        sleep(4000);
                        ExtentReporter.logger.log(LogStatus.INFO,
                                "Click Ingore All(Might have to do it multiple times) & verify Check Spelling window is closes.");
                        boolean chkspellpopupvalue = verifyCheckSpellingPopup();
                        if (chkspellpopupvalue == true) {
                            do {
                                clickButton(driver, ignoreAllBtn, "Ignore All");
                                sleep(2000);
                                clickButton(driver, DelOptions, "Delivery Options");
                            } while (verifyCheckSpellingPopup() == true);
                        }
                        sleep(2000);
                        ExtentReporter.logger.log(LogStatus.INFO,
                                "Click [Deliver] Exit out of window  & Return code: 0 message appears, Exit out of window.");
                        clickButton(driver, deliverBtn, "deliver button");
                        sleep(2000);
                        visibilityOfElement(driver, sucessMsg, "Sucess Message");
                        driver.close();
                    } catch (Exception e) {
                        ExtentReporter.logger.log(LogStatus.FAIL, "Error while performing action on Cincom Page.");
                        driver.close();
                        switchToParentWindowfromotherwindow(driver, parentWindowId);
                        switchToFrameUsingElement(driver,
                                driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
                        sleep(2000);
                        clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
                        sleep(3000);
                        switchToParentWindowfromframe(driver);
                        Assert.assertTrue(false, "Error while performing action on Cincom Page.");
                    }
                }
            } catch (Exception e) {
                ExtentReporter.logger.log(LogStatus.FAIL, "Error while opening Cincom Page.");
                driver.close();
                switchToParentWindowfromotherwindow(driver, parentWindowId);
                switchToFrameUsingElement(driver,
                        driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
                sleep(2000);
                clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
                sleep(2000);
                switchToParentWindowfromframe(driver);
                Assert.assertTrue(false, "Error while opening Cincom Page.");
            }

            switchToParentWindowfromotherwindow(driver, parentWindowId);
            sleep(1000);
            switchToFrameUsingElement(driver,
                    driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
            sleep(3000);
            click(driver, manuscriptPageSaveBtn, "Manu Script page Save");
            sleep(2000);
            clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
            switchToParentWindowfromframe(driver);
            break;
        }
        return new RateApolicyPage(driver);
    }

    // This method will handle spell check pop up on CINCOM page.
    public boolean verifyCheckSpellingPopup() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            if (wait.until(ExpectedConditions.visibilityOf(spellchkBoxHeading)) != null)
                ExtentReporter.logger.log(LogStatus.INFO, "Spell check pop up window displayed..");
            return true;
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.INFO, "Spell check pop up window is NOT displayed..");
            return false;
        }
    }

    public boolean verifyErrorWhileOpeningCinComPage() {
        try {
            if (errorOnCinCOmPage.isDisplayed()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.INFO, "Concom page opened successfully.");
        }
        return false;
    }

}
