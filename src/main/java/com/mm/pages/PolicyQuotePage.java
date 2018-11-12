package com.mm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.dto.PolicyQuotePageDTO;
import com.mm.dto.RateAPolicyPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.mm.utils.TestCaseDetails;
import com.relevantcodes.extentreports.LogStatus;

public class PolicyQuotePage extends CommonAction {

    WebDriver driver;
    PolicyQuotePageDTO policyquotepageDTO;

    String valueOfPolicyActionCopy = "javascript:copyQuote();";
    String saveAsPolicyValue = "OFFICIAL";
    String QuotePhaseValue = "QUOTE";
    String ProductNotifyValue = "Y";
    String saveASWindowTitle = "Save As";
    String captureTranxDetailsWindowTitle = "Capture Transaction Details";
    String PDFErrorMsgContains = "Error occurred";

    @FindBy(name = "globalSearch")
    WebElement Policy_Search;

    @FindBy(name = "search")
    WebElement Search_btn;

    @FindBy(id = "findPolicyListGrid_CPOLICYNO_0_HREF") // QA
    WebElement policyList;

    @FindBy(id = "pageTitleForpageHeaderForPolicyFolder")
    WebElement pageHeaderForPolicyFolder;

    @FindBy(xpath = "//div[@id='globalDropdownActionItems']//select[@class='globalActionItemList'] |//select[@id='PM_POLICY_FOLDER_AG']")
    WebElement policyAction;

    @FindBy(id = "PM_CPT_TRAN_OK")
    WebElement captTranxOk;

    @FindBy(xpath = "//a[@id='PM_PT_VIEWCVG']//span[@class='tabWithNoDropDownImage']")
    WebElement coverageTab;

    @FindBy(xpath = "//table[@id='coverageListGrid']//tbody//td//div[@id='CPRODUCTCOVERAGEDESC']")
    List<WebElement> coverageList;

    @FindBy(xpath = "//input[@id='PM_QT_MANU_PUP']")
    WebElement optionalFormBtn;

    @FindBy(xpath = "//table[@id='maintainManuscriptListGrid']")
    WebElement manuscriptList;

    @FindBy(xpath = "//table[@id='maintainManuscriptListGrid']//div[@id='CFORMTYPECODELOVLABEL']")
    WebElement manuscriptPageFirstItem;

    @FindBy(id = "PM_MANU_DELETE")
    WebElement manuscriptPageDeleteBtn;

    @FindBy(id = "PM_MANU_ADD")
    WebElement manuscriptPageAddBtn;

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

    @FindBy(id = "PM_COMMON_TABS_RATE")
    WebElement rateBtn;

    @FindBy(id = "PM_VIEW_PREM_CLOSE")
    WebElement closeBtnOnViewPremiumPopup;

    @FindBy(name = "workflowExit_Ok")
    WebElement okPolicySaveAsWIPPopup;

    @FindBy(xpath = "//div[@class='horizontalButtonCollection'][1]//input[@value='Save Options'][1]")
    WebElement saveOptionBtn;

    @FindBy(xpath = "//select[@name='saveAsCode']")
    WebElement saveAsDropDown;

    @FindBy(id = "PM_SAVE_OPTION_OK")
    WebElement saveOptionOkBtn;

    @FindBy(name = "workflowExit_Ok")
    WebElement exitOK;

    @FindBy(name = "policyPhaseCode")
    WebElement policyPhase;

    @FindBy(id = "PM_QT_POLICY_FOLDER_AG")
    WebElement PolicyActionDropDown;

    @FindBy(name = "483865737.confirmed")
    WebElement productNotifyDropDown;

    @FindBy(id = "PM_NOTIFY_CLOSE")
    WebElement prodNotifyClose;

    @FindBy(name = "additionalText")
    WebElement addText;

    @FindBy(id = "PM_COMMON_TABS_PREVIEW")
    WebElement PreviewTab;

    @FindBy(xpath = "//iframe[contains(@src,'process=preview&transLogId')]")
    WebElement pdfErrorForm;

    @FindBy(xpath = "//body")
    WebElement pdfErrorMsg;

    @FindBy(xpath = "//div[@aria-describedby='newpopup1']")
    WebElement pdfPane;

    @FindBy(xpath = "//select[@name='policyNavLevelCode']//option[@value='RISK']")
    WebElement verifyRisk;

    @FindBy(name = "transactionComment")
    WebElement quoteDescription;

    @FindBy(id = "PM_COMMON_TABS_ACCPTQUOTE")
    WebElement applyButton;

    @FindBy(id = "PM_COMMON_TABS_DELETEWIP")
    WebElement deleteWIPBtn;

    // This is a constructor for PolicyQuotePage class to initialize page
    // elments and DTO object
    public PolicyQuotePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        policyquotepageDTO = new PolicyQuotePageDTO(TestCaseDetails.testDataDictionary);
    }

    // Identify Policy number from page.
    public String policyNo() {
        WebDriverWait wait = new WebDriverWait(driver, 180);
        wait.until(ExpectedConditions.elementToBeClickable(pageHeaderForPolicyFolder));
        String profileNoLable = pageHeaderForPolicyFolder.getAttribute("innerHTML");
        String[] portfolioNo = profileNoLable.split(" ", 3);
        return portfolioNo[2];
    }

    // Select Copy to action from "Action DropDown", search back up policy if
    // needed.
    public PolicyQuotePage CopyOptionFromActionDropDown() {
        sleep(4000);
        invisibilityOfLoader(driver);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click Policy Actions>Copy>Ok and verify Policy displays in Indication phase.");
        if (selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policyquotepageDTO.valueOfPolicyActionCopy,
                "Policy Action").equals("false")) {
            PolicyBinderPage pbp = new PolicyBinderPage(driver);
            // Below code will search the policy from application.
            searchBackUpPolicyUsingSearchCriteria();
            pbp.copyToQuoteFromActionDropDownwithoutBackUpPolicy(pbp.policyNo());
            selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policyquotepageDTO.valueOfPolicyActionCopy,
                    "Policy Action");
        }
        return new PolicyQuotePage(driver);
    }

    // Select Copy to action from "Action DropDown", search back up policy if
    // needed.
    public PolicyQuotePage CopyOptionFromActionDropDowBTS_QA() {
        sleep(4000);
        invisibilityOfLoader(driver);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click Policy Actions>Copy>Ok and verify Policy displays in Indication phase.");
        if (selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policyquotepageDTO.valueOfPolicyActionCopy,
                "Policy Action").equals("false")) {
            PolicyBinderPage pbp = new PolicyBinderPage(driver);
            // Below code will search the policy from application.
            try {
                searchBackUpPolicyUsingSearchCriteriaBTS_QA();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            pbp.copyToQuoteFromActionDropDownwithoutBackUpPolicy(pbp.policyNo());
            selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policyquotepageDTO.valueOfPolicyActionCopy,
                    "Policy Action");
        }
        return new PolicyQuotePage(driver);
    }

    // Select Copy to action from "Action DropDown" without search for backup
    // policy.
    public PolicyQuotePage CopyOptionFromActionDropDownwithoutBackupPolicy() {
        sleep(6000);
        invisibilityOfLoader(driver);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click Policy Actions>Copy>Ok and verify Policy displays in Indication phase.");
        selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policyquotepageDTO.valueOfPolicyActionCopy,
                "Policy Action");
        return new PolicyQuotePage(driver);
    }

    public PolicyQuotePage copyFromActionDropDownForQAWithoutBackup() {
        sleep(2000);
        PolicyBinderPage policyBinderPage = new PolicyBinderPage(driver);
        CopyOptionFromActionDropDownwithoutBackupPolicy();
        isAlertPresent(driver);
        String policyNum = policyBinderPage.policyNo();
        policyBinderPage.captureTransactionDetails(policyNum);
        return new PolicyQuotePage(driver);
    }

    public PolicyQuotePage copyFromActionDropDownForQA() {
        sleep(2000);
        PolicyBinderPage policyBinderPage = new PolicyBinderPage(driver);
        CopyOptionFromActionDropDown();
        isAlertPresent(driver);
        String policyNum = policyBinderPage.policyNo();
        policyBinderPage.captureTransactionDetails(policyNum);
        return new PolicyQuotePage(driver);
    }

    public PolicyQuotePage copyFromActionDropDownForBTS_QA() {
        sleep(2000);
        PolicyBinderPage policyBinderPage = new PolicyBinderPage(driver);
        CopyOptionFromActionDropDowBTS_QA();
        isAlertPresent(driver);
        String policyNum = policyBinderPage.policyNo();
        policyBinderPage.captureTransactionDetails(policyNum);
        return new PolicyQuotePage(driver);
    }

    public PolicyQuotePage copyToQuoteFromActionDropDownForQA() {
        PolicyBinderPage policyBinderPage = new PolicyBinderPage(driver);
        String policyNum = policyBinderPage.policyNo();
        policyBinderPage.copyToQuoteFromActionDropDownForQA();
        policyBinderPage.captureTransactionDetails(policyNum);
        return new PolicyQuotePage(driver);
    }

    // Search Policy from Search Policy text field.
    public RateApolicyPage searchPolicy(String policy_no) {
        sleep(3000);
        policySearch(driver, policy_no, Policy_Search, Search_btn, policyList);
        String actual = getText(driver, pageHeaderForPolicyFolder);
        ExtentReporter.logger.log(LogStatus.INFO, "Policy # dispalys correctly under Policy Folder");
        Assert.assertEquals(actual, "Policy Folder " + policy_no, "The policy " + policy_no + " is Not available.");
        sleep(3000);
        return new RateApolicyPage(driver);
    }

    // Coverage details flow.
    public void coverageDetailsSelect() {
        sleep(2000);
        ExtentReporter.logger.log(LogStatus.INFO, "Click Coverage tab & verify Coverage tab is displayed.");
        clickButton(driver, coverageTab, "Coverage");
        sleep(3000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Select Primary risk in DDL if not selected & verify Primary risk is selected and coverages are displayed.");
        sleep(3000);
        Assert.assertEquals(coverageList.get(0).getAttribute("innerHTML"), policyquotepageDTO.riskType,
                "Coverage for Primary Risk is NOT displayed");
    }

    // Coverage Update flow.
    public void coverageUpdates(String CoverageName, String binderForm, String PolicyNo) {
        for (int i = 0; i < coverageList.size(); i++) {
            if (coverageList.get(i).getAttribute("innerHTML").equals(CoverageName)) {
                ExtentReporter.logger.log(LogStatus.INFO, "Select" + CoverageName + " Coverage.");
                clickButton(driver, coverageList.get(i), coverageList.get(i).getAttribute("innerHTML"));
                sleep(4000);
                break;
            }
        }
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Optional Forms]");
        clickButton(driver, optionalFormBtn, "Optional Form");
        getPageTitle(driver, "Manuscript Information");
        sleep(4000);
        switchToFrameUsingElement(driver,
                driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
        if (manuscriptList.isDisplayed()) {
            String firstManuScriptInfoName = manuscriptPageFirstItem.getAttribute("innerHTML");
            ExtentReporter.logger.log(LogStatus.INFO,
                    "Delete current Indication form, Are you sure you want to delete this? Click Ok");
            clickButton(driver, manuscriptPageDeleteBtn, "Manu script Delete");
            driver.switchTo().alert().accept();
            // Verify first item displayed in menu script list is not displayed
            // in list.
            Assert.assertEquals(manuscriptPageFirstItem.getAttribute("innerHTML"), firstManuScriptInfoName,
                    "Manuscript lsit first item " + firstManuScriptInfoName + " is not deleted.");
            sleep(2000);
            ExtentReporter.logger.log(LogStatus.INFO, "Click [Add].");
            clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
        } else {
            ExtentReporter.logger.log(LogStatus.INFO, "Click [Add].");
            clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
        }
        switchToFrameUsingElement(driver,
                driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
        sleep(3000);
        for (int i = 0; i < manuscriptAddListformName.size(); i++) {
            if (manuscriptAddListformName.get(i).getAttribute("innerHTML").equals(binderForm)) {
                ExtentReporter.logger.log(LogStatus.INFO, "Select " + binderForm + ", Click done.");
                clickButton(driver, manuscriptAddListformNameChkBox.get(i), "check Box for " + binderForm);
                break;
            }
        }
        clickButton(driver, manuscriptAddListDoneBtn, "Done");
        sleep(2000);
        switchToParentWindowfromframe(driver);
        switchToFrameUsingElement(driver,
                driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
        enterTextIn(driver, addText, binderForm + " form added.", "Aditional Text");
        switchToParentWindowfromframe(driver);
        switchToFrameUsingElement(driver,
                driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
        ExtentReporter.logger.log(LogStatus.INFO,
                "Enter additional text: " + binderForm + " form added  Click [Save] and Click [Close].");
        clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
        sleep(2000);
        clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
        switchToParentWindowfromframe(driver);
    }

    // Rate with premium Amount verification functionality flow.
    public PolicyQuotePage rateFunctionality(String policyNo) {
        RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
        rateapolicypage.rateFunctionality(policyNo);
        return new PolicyQuotePage(driver);
    }

    // Rate without premium Amount verification functionality flow.
    public PolicyQuotePage rateFunctionalityWithoutPremiumVerification(String policyNo) {
        sleep(2000);
        RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
        rateapolicypage.rateFunctionalityWithoutPremiumAmountVerification(policyNo);
        return new PolicyQuotePage(driver);
    }

    // Save option functionality flow.
    // We need to call multiple times with different values, so we are passing
    // values in test case call
    public PolicyQuotePage saveOption(String saveAsPolicyValue, String PolicyNo) {
        saveOption(driver, saveOptionBtn, saveAsDropDown, saveOptionOkBtn, exitOK, saveAsPolicyValue, PolicyNo);
        return new PolicyQuotePage(driver);
    }

    public PolicyQuotePage saveOptionOfficial(String PolicyNo) {
        RateApolicyPage rateapolicypage = new RateApolicyPage(driver);

        /*
         * Note- PDF process kill and refresh page code is added because IE
         * faces issue intermittently after PDF generation.
         */
        rateapolicypage.refreshCurrentPage(driver);
        try {
            Process processkillpdf = Runtime.getRuntime()
                    .exec("TASKKILL /F /FI \"USERNAME eq " + System.getProperty("user.name") + "\" /IM savePdf.exe");
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.WARNING, "Error while killing pdf process in SaveOption.");
        }
        saveOption(driver, saveOptionBtn, saveAsDropDown, saveOptionOkBtn, exitOK,
                policyquotepageDTO.saveAsPolicyValueOfficial, PolicyNo);
        return new PolicyQuotePage(driver);
    }

    public PolicyQuotePage product_Notify() {

        switchToFrameUsingId(driver, "popupframe1");
        waitForElementToLoad(driver, 10, productNotifyDropDown);
        selectDropdownByValue(driver, productNotifyDropDown, policyquotepageDTO.productNotifyValue, "product notify");
        sleep(1000);
        ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window dispalyed to user.");
        clickButton(driver, prodNotifyClose, "Product Notify Close");
        ExtentReporter.logger.log(LogStatus.PASS, " Yes selected from Product Notify drop down list.");
        return new PolicyQuotePage(driver);
    }

    public PolicyQuotePage applyChanges() {
        sleep(2000);
        invisibilityOfLoader(driver);
        clickButton(driver, applyButton, "Apply");
        sleep(2000);
        isAlertPresent(driver);
        return new PolicyQuotePage(driver);
    }

    public Boolean deleteWIPForReUse() {
        sleep(5000);
        Boolean flag = null;
        try {
            deleteWIPBtn.click();
            ExtentReporter.logger.log(LogStatus.PASS, "clicked on button / Link- " + "DeleteWIP");
            isAlertPresent(driver);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    // Change phase to Quote.
    public PolicyQuotePage changePolicyPhase(String newPhaseValue) {
        sleep(8000);
        invisibilityOfLoader(driver);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Change Phase to " + newPhaseValue + " & verify Phase is changed to " + newPhaseValue);
        selectDropdownByValue(driver, policyPhase, newPhaseValue, "Phase");
        return new PolicyQuotePage(driver);
    }

    // Click preview tab.
    public PDFReader clickPreviewTab(String policyNumber) {
        invisibilityOfLoader(driver);
        WebDriverWait wait = new WebDriverWait(driver, High);
        wait.until(ExpectedConditions.visibilityOf(PreviewTab));
        sleep(2000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click [Preview]& verify Preview window displays with Form Printing on Form's List");
        clickButton(driver, PreviewTab, "Preview");
        sleep(10000);
        invisibilityOfLoader(driver);
        verifySaveAsPopUp(policyNumber);
        verifyPDFgenratedOrNot();
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click the x to close the Preview screen. Verify Preview screen closes");
        sleep(5000);
        return new PDFReader(driver);
    }

    public void verifyPDFgenratedOrNot() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Low);
            wait.until(ExpectedConditions.elementToBeClickable(pdfErrorForm));
            switchToFrameUsingElement(driver, pdfErrorForm);
            String errorMsgForPDF = pdfErrorMsg.getAttribute("innerHTML");
            errorMsgForPDF.contains(PDFErrorMsgContains);
            ExtentReporter.logger.log(LogStatus.FAIL, " Error while generating PDF.");
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.INFO, "PDF generated sucessfully.");
        }
    }

    // this method will verify saveAs pop up displayed or not
    public void verifySaveAsPopUp(String policyNo) {
        try {
            WebElement iframeEle = driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]"));
            if (iframeEle.isDisplayed()) {
                switchToFrameUsingElement(driver, iframeEle);
                clickButton(driver, okPolicySaveAsWIPPopup, "Ok");
            } else {
                ExtentReporter.logger.log(LogStatus.INFO, "Save as Pop up window is not displayed.");
            }
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.INFO, "Save as Pop up window is not displayed.");
        }
    }

    // Select the policy Action from DDL
    public PolicyQuotePage selectPolicyActionAndAddDescription() {
        sleep(4000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click Policy Actions>Renewal. Verify Capture Transaction Details window opens");
        if (selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policyquotepageDTO.policyActionValue,
                "Policy Action").equals("false")) {
            RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
            RateAPolicyPageDTO rateApolicyPageDTO = new RateAPolicyPageDTO(TestCaseDetails.testDataDictionary);
            rateapolicypage.searchPolicy(rateApolicyPageDTO.backUpPolicyNum);
            PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
            selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policyquotepageDTO.policyActionValue,
                    "Policy Action");
        }
        return new PolicyQuotePage(driver);
    }

    public PolicyQuotePage addQuoteDescription() {

        sleep(6000);
        invisibilityOfLoader(driver);
        switchToFrameUsingId(driver, "popupframe1");
        getPageTitle(driver, captureTranxDetailsWindowTitle);
        ExtentReporter.logger.log(LogStatus.INFO, "Enter Quote Description: " + policyquotepageDTO.quoteDescriptionText
                + "Click [OK].Verify Text is entered and Policy is ready for renewal");
        enterTextIn(driver, quoteDescription, policyquotepageDTO.quoteDescriptionText, "Enter Quote Description");
        sleep(2000);
        return new PolicyQuotePage(driver);
    }

    // Select the policy Action from DDL
    public PolicyQuotePage selectPolicyAction() {
        sleep(4000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click Policy Actions>Renewal. Verify Capture Transaction Details window opens");

        if (selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policyquotepageDTO.policyActionValue,
                "Policy Action").equals("false")) {

            // Deleting the Work in progress will enable required action from
            // policy Action
            // DDL
            searchBackUpPolicyUsingSearchCriteriaBTS_QA();
            sleep(5000);

            if (selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policyquotepageDTO.policyActionValue,
                    "Policy Action").equals("false")) {
                // Below method will search policy number using search criteria
                RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
                rateapolicypage.searchThroughPolicyList(policyquotepageDTO.policyActionValue);
            }
        }
        return new PolicyQuotePage(driver);
    }

    // This method will select a policy using the required search criteria
    public void searchBackUpPolicyUsingSearchCriteria() {

        FindPolicyPage findPolicyPage = new FindPolicyPage(driver);
        findPolicyPage.navigateToPolicySearchPage().openSearchPolicyPane().selectTermStatusAndIssueCompany()
                .selectPolicyType().searchFromFindPolicyPage();
    }

    // This method will select a policy using the required search criteria on
    // New BTS env
    public void searchBackUpPolicyUsingSearchCriteriaBTS_QA() {
        FindPolicyPage findPolicyPage = new FindPolicyPage(driver);
        findPolicyPage.navigateToPolicySearchPage().openSearchPolicyPane().selectTermStatusAndIssueCompany()
                .selectPolicyType().policySearchFromFindPolicyPage_BTS_QA();
    }

    // Switch to second frame from first frame
    public PolicyQuotePage switchToNextFrame() {

        List<WebElement> secondFrame = driver.findElements(By.id("popupframe2"));
        driver.switchTo().frame(secondFrame.get(0));
        return new PolicyQuotePage(driver);
    }

    // Save the Transaction details and switch to parent window
    public PolicyQuotePage save_CaptureTransactionDetails() {

        sleep(5000);
        click(driver, captTranxOk, "Ok button. Verify policy is ready for Renewal");
        switchToParentWindowfromframe(driver);
        return new PolicyQuotePage(driver);
    }

    // Close the exit window
    public PolicyQuotePage exit_SaveOption() {

        waitForElementToLoad(driver, 10, exitOK);
        ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]");
        clickButton(driver, exitOK, "Exit Ok");
        invisibilityOfLoader(driver);
        return new PolicyQuotePage(driver);
    }

    public PolicyQuotePage saveOptionAndCaptureTransactionDetails(String saveAsValue, String policyNo) {
        saveOptionAndHandleCaptureTransactionDetails(driver, saveOptionBtn, saveAsDropDown, saveOptionOkBtn, exitOK,
                saveAsValue, policyNo);
        return new PolicyQuotePage(driver);
    }

    public void saveOptionAndHandleCaptureTransactionDetails(WebDriver driver, WebElement saveOptionBtn,
            WebElement saveAsDropDown, WebElement saveOKBtn, WebElement exitOK, String saveAsValue, String policyNo) {
        sleep(5000);
        ExtentReporter.logger.log(LogStatus.INFO, "Click Save Options & verify Save as window displays.");
        waitForElementToLoad(driver, 20, saveOptionBtn);
        clickButton(driver, saveOptionBtn, "Save Option");
        sleep(5000);
        invisibilityOfLoader(driver);
        sleep(2000);
        WebElement iframeElement = driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]"));
        switchToFrameUsingElement(driver, iframeElement);
        getPageTitle(driver, saveASWindowTitle);
        ExtentReporter.logger.log(LogStatus.INFO, "Select " + saveAsValue + " from drop down list");
        selectDropdownByValue(driver, saveAsDropDown, saveAsValue, "Selected " + saveAsValue);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Select " + saveAsValue + " Click [OK] & verify Message is closed and WIP is saved as" + saveAsValue);
        sleep(2000);
        clickButton(driver, saveOKBtn, "OK");
        switchToParentWindowfromframe(driver);
        sleep(5000);
        invisibilityOfLoader(driver);
        List<WebElement> iframeEle1 = driver
                .findElements(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]"));
        switchToFrameUsingElement(driver, iframeEle1.get(1));
        sleep(1000);
        getPageTitle(driver, captureTranxDetailsWindowTitle);
        if (captTranxOk.isDisplayed()) {
            clickButton(driver, captTranxOk, "OK");
        } else {
            Assert.assertTrue(false, "Capture Transaction Details window is not present.");
        }
        switchToParentWindowfromframe(driver);
        sleep(2000);
    }
}
