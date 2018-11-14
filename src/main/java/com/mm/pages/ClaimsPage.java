package com.mm.pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.dto.ClaimsDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mm.utils.ExtentReporter;
import com.mm.utils.TestCaseDetails;
import com.relevantcodes.extentreports.LogStatus;

public class ClaimsPage extends CommonAction {
    WebDriver driver;
    ClaimsDTO claimsdto;
    HomePage homePage;
    CommonUtilities comUtil = new CommonUtilities();
    String fileStatusDropDownOption = "OPENERROR";
    String verifyFileStatusValue = "Opened in Error";
    String fileSearchPageTitle = "File Search";
    String duplicateClaimPageTitle = "Possible Duplicate Claim";
    String duplicateTransactionPageTitle = "Possible Duplicate Transaction";
    String ExcelPath = System.getProperty("user.dir") + "\\src\\main\\resources\\Form_Data.xlsx";
    String addFilePageTitle = "";

    @FindBy(xpath = "//div[@class='pageTitle']")
    WebElement pageTitle;

    @FindBy(name = "globalSearch")
    WebElement claim_Search;

    @FindBy(name = "search")
    WebElement Search_btn;

    @FindBy(xpath = "//span[@id='CCLAIMNO']")
    WebElement policyList;

    @FindBy(name = "claim_claimStatusDate")
    WebElement chagneFileDate;

    @FindBy(id = "CM_GENL_STAT_AI")
    WebElement chagneStatus;

    @FindBy(id = "CM_PICKER_SAVE")
    WebElement saveBtnOnChagneStatusPopup;

    @FindBy(xpath = "//span[contains(@class,'ui-button-icon']")
    WebElement closeSymbolOnChagneStatusPopup;

    @FindBy(name = "claim_claimCurrentStatus")
    WebElement fileStatus;

    @FindBy(id = "claimHeaderClaimStatusCodeLongDescROSPAN1")
    WebElement fileStatusOnClaimFolderPage;

    @FindBy(xpath = "//iframe[contains(@id,'popupframe')]")
    WebElement changeFileStatusFrameEle;

    @FindBy(xpath = "//td[@class='errortext']")
    WebElement ErrorMsg;

    @FindBy(xpath = "//a[@class='selectedMenu fNiv isParent']//span")
    WebElement filesMenuTab;

    @FindBy(xpath = "//li[@id='CM_ADD_CLAIM_MI']//a")
    WebElement fileAddMenuOption;

    @FindBy(id = "btnFind_claimantFullName")
    WebElement patientSearchIcon;

    @FindBy(xpath = "//input[@name='entitySearch_lastOrOrgName']")
    WebElement lastNameEntitySearchPage;

    @FindBy(xpath = "//input[@name='entity_firstName']")
    WebElement firstNameEntitySearchPage;

    @FindBy(id = "CI_ENTITY_SELECT_SCH_SCH")
    WebElement searchBtnOnEntitySearchPage;

    @FindBy(id = "CCLIENT_NAME")
    WebElement resultOnEntityListPage;

    @FindBy(xpath = "//input[@name='chkCSELECTIND']")
    WebElement selectEntityChkBox;

    @FindBy(id = "CI_ENT_SEL_LST_FORM_SEL")
    WebElement selectBtnOnEntitySelectListPage;

    @FindBy(name = "coverageFilter_policyNo")
    WebElement policyNoDDL;

    @FindBy(xpath = "//table[@id='claimCoverageListGrid']//div[@id='CCOVERAGEDESC']")
    List<WebElement> coverageDescriptionList;

    @FindBy(xpath = "//table[@id='claimCoverageListGrid']//input[@name='chkCSELECT_IND']")
    List<WebElement> coverageDescriptionChkBoxList;

    @FindBy(id = "CM_CREATE_CLM_SC")
    WebElement saveAsClaimBtn;

    @FindBy(xpath = "//input[@id = 'CM_VERDUP_CLM_SC'] | //input[@id = 'CM_TXN_DUP_SAVE']")
    WebElement saveAsClaimBtnOnPsblDuplciateClaimPage;

    @FindBy(id = "claimHeaderClaimNoROSPAN1")
    WebElement claimNo;

    @FindBy(xpath = "//select[@name ='claimType']")
    WebElement FileTypeDropDown;

    @FindBy(xpath = "//select[@name ='cmLobCode']")
    WebElement lobDropDown;

    @FindBy(xpath = "//textarea[@class='oasis_formfieldreq']")
    WebElement descriptionTextBox;

    @FindBy(xpath = "//input[@name='claimantFullName']")
    WebElement patientSelectedValue;

    @FindBy(xpath = "//select[@name='entityExaminerId']")
    WebElement fileHandlerDorpDown;

    @FindBy(xpath = "//select[@name='claimStateCode']")
    WebElement stateOfLossDorpDown;

    @FindBy(xpath = "//input[@name='lossDate']")
    WebElement accidentDateTextBox;

    @FindBy(xpath = "//input[@name='date3']")
    WebElement triageMeetingDateField;

    @FindBy(xpath = "//img[@id='btnFind_insuredFullName']")
    WebElement insuredSearchIcon;

    @FindBy(xpath = "//input[@name = 'entity_clientID']")
    WebElement entityClientId;

    // WebELements for TC42252 -- this test has changed completely

    @FindBy(xpath = "//li[@id='CM_CLAIMS_MENU']")
    WebElement filesMenu;

    @FindBy(xpath = "//ul[@id='subMenuForCM_CLAIMS_MENU']//li//a//span")
    List<WebElement> filesMenuList;

    @FindBy(xpath = "//a//img[@id='btnFind_claimantFullName']")
    WebElement patientSearchBtn;

    @FindBy(name = "entitySearch_lastOrOrgName")
    WebElement lastOrgName;

    @FindBy(id = "CI_ENTITY_SELECT_SCH_SCH")
    WebElement searchEntityButton;

    @FindBy(id = "CCLIENT_NAME")
    List<WebElement> entitySelectclientNameList;

    @FindBy(xpath = "//input[@name='chkCSELECTIND']")
    List<WebElement> entityCheckboxList;

    @FindBy(xpath = "//div[@id='CCLIENT_ID']")
    List<WebElement> entitySelectClientIdList;

    @FindBy(id = "CI_ENT_SEL_LST_FORM_SEL")
    WebElement selectEntity;

    @FindBy(name = "name=claimType")
    WebElement fileTypeDDL;

    @FindBy(name = "cmLobCode")
    WebElement LOBDDL;

    @FindBy(xpath = "//textarea[@name='headline']")
    WebElement fileDescription;

    @FindBy(name = "entityExaminerId")
    WebElement fileHandlerDDL;

    @FindBy(name = "claimStateCode")
    WebElement stateOfLoss;

    @FindBy(name = "lossDate")
    WebElement accidentDate;

    @FindBy(xpath = "//a//img[@id='btnFind_insuredFullName']")
    WebElement searchInsuredName;

    @FindBy(name = "entity_clientID")
    WebElement entityClientID;

    // TC 42252 - Claims - Enter Transactions

    @FindBy(id = "CM_CMF_TRANS_MI")
    WebElement transactionTab;

    @FindBy(id = "CM_CMTXN_ADD_AI")
    WebElement addTransactionBtn;

    @FindBy(name = "cmTransactionTypeCode")
    WebElement transactionType;

    @FindBy(name = "cmPaymentTypeCode")
    WebElement paymentType;

    @FindBy(id = "btnFind_payeeCisName")
    WebElement searchIconPayeeName;

    @FindBy(name = "entitySearch_addlField")
    WebElement vendorID;

    @FindBy(name = "taxIdType")
    WebElement taxIDType;

    @FindBy(name = "transAmt")
    WebElement transactionAmount;

    @FindBy(name = "invoiceNo")
    WebElement invoiceNo;

    @FindBy(name = "separateCheckB")
    WebElement seperateCheck;

    @FindBy(id = "CM_TXN_ADD_DATA_SAVE")
    WebElement saveTransactionBtn;

    @FindBy(id = "CM_TXN_ADD_DATA_CLS")
    WebElement closeTransactionBtn;

    @FindBy(name = "payeeCisName")
    WebElement payeeNameCIS;

    @FindBy(id = "CM_TXN_DUP_SAVE")
    WebElement saveDuplicate;

    @FindBy(id = "CCMTRANSACTIONTYPECODELOVLABEL")
    List<WebElement> transTypeFromTransList;

    @FindBy(xpath = "//div[@id='CINVOICENO']")
    List<WebElement> InvoiceNumberList;

    @FindBy(id = "CTRANSAMT")
    List<WebElement> transAmountFromTransList;

    @FindBy(id = "CPAYEECISNAME")
    List<WebElement> CISpayeeNameFromTransList;

    @FindBy(id = "CINVOICENO")
    List<WebElement> invoiceNumFromTransactionList;

    @FindBy(id = "panelTitleIdForClaimTrans")
    WebElement transactionListTitle;

    // Constructor to initialize driver, page elements and DTO PageObject for
    // Claims Page.
    public ClaimsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        claimsdto = new ClaimsDTO(TestCaseDetails.testDataDictionary);
    }

    // This code will open Transaction tab.
    public ClaimsPage openTransactionTab() {
        ExtentReporter.logger.log(LogStatus.INFO, "Transaction List displays at the bottom of the page");
        clickButton(driver, transactionTab, "Transaction");
        invisibilityOfLoader(driver);
        visibilityOfElement(driver, transactionListTitle, "Transaction List");
        return new ClaimsPage(driver);
    }

    // Code will add transaction data and save transaction.
    public void addTransactionDataAndSaveTransaction() {
        // Get the number of transaction types from excel sheet
        ExtentReporter.logger.log(LogStatus.INFO, "Add Transaction window displays");
        for (int i = 0; i < claimsdto.transactionType.size(); i++) {
            clickButton(driver, addTransactionBtn, "Add Transaction");
            invisibilityOfLoader(driver);
            sleep(5000);
            // Logic to add first transaction value.
            String invoiceNumber = addTransactionDetails(claimsdto.transactionType.get(i), claimsdto.paymentType,
                    claimsdto.vendorIDValue.get(i), claimsdto.taxIDType.get(i), claimsdto.transactionAmount.get(i), i);
            clickButton(driver, saveTransactionBtn, "Save");
            sleep(2000);
            if (isAlertPresent(driver) == true) {

            } else {
                int counter = 0;
                do {
                    sleep(20000);
                    if (isAlertPresent(driver) == true) {
                        break;
                    }
                    counter++;
                } while (counter < 10);
            }

            // After save button, get alert message in variable and verify alert
            // pop up message
            if (checkduplicateClaimWindow(duplicateTransactionPageTitle) == true) {
                clickButton(driver, saveAsClaimBtnOnPsblDuplciateClaimPage, "Save As Claim");
                invisibilityOfLoader(driver);
                isAlertPresent(driver);
                sleep(5000);
                switchToParentWindowfromframe(driver);
                switchToFrameUsingId(driver, "popupframe1");
                sleep(3000);
            }
            ExtentReporter.logger.log(LogStatus.INFO, "Transaction is entered and the Alert message is displayed");
            sleep(2000);
            // clickButton(driver, saveTransactionBtn, "Save Transaction");
            click(driver, saveTransactionBtn, "Save Transaction");
            isAlertPresent(driver);
            sleep(2000);
            // close Add transaction window
            closeAddTransactionWindow();
            switchToParentWindowfromframe(driver);
            ExtentReporter.logger.log(LogStatus.INFO,
                    "Verify Message window closes and transaction is listed at the top of the Transaction list");
            sleep(2000);
            visibilityOfElement(driver, transactionListTitle, "Transaction List");
            // verify transaction is listed at the top of transaction list
            ExtentReporter.logger.log(LogStatus.INFO, "Transactions display as entered in the Transaction List.");
            verifyAddedTransactionIsListedInTransactionList(invoiceNumber);
        }
    }

    // Method will add details while creating transaction.
    private String addTransactionDetails(String txnType, String payType, String vendorId, String taxId, String amount,
            int invoiceNoCount) {
        String Empty = "";
        switchToFrameUsingId(driver, "popupframe1");
        sleep(2000);
        // Select Trans type and Payee Type from DDL
        selectDropdownByVisibleText(driver, transactionType, txnType, "Trans Type");
        sleep(1000);
        selectDropdownByVisibleText(driver, paymentType, payType, "Pay Type");
        sleep(1000);
        // Click PayeeCISNameSearchIcon
        clickButton(driver, searchIconPayeeName, "Search Icon for Payee Name");
        // switch to pop up window and call searchEntity and selectEntity
        // methods from home page
        // to search and Select Payee Name.
        String parentWindow = switchToWindow(driver);
        sleep(2000);
        HomePage homePage = new HomePage(driver);
        // Blank value is passed to match arguments, as method is used by other
        // test case as well.
        homePage.searchEntity(Empty, vendorId);
        homePage.selectEntity(parentWindow, Empty);
        sleep(10000);
        switchToFrameUsingId(driver, "popupframe1");
        sleep(2000);
        selectDropdownByVisibleText(driver, taxIDType, taxId, "Tax ID Type");
        enterDataIn(driver, transactionAmount, amount, "Trans Amount");
        // Invoice no. is current date + index. of transaction(e.g. 1,2,3..)
        CommonUtilities comUtil = new CommonUtilities();
        String Date = comUtil.getSystemDatemmddyyyy();
        String invoiceNumber = Date + "-" + (invoiceNoCount + 1);
        enterDataIn(driver, invoiceNo, invoiceNumber, "Invoice No.");
        selectDropdownByVisibleText(driver, seperateCheck, claimsdto.seperateCheck, "Sep Check");
        return invoiceNumber;
    }

    // Navigate to open window and close Add Transaction window.
    public void closeAddTransactionWindow() {
        switchToParentWindowfromframe(driver);
        switchToFrameUsingId(driver, "popupframe1");
        sleep(1000);
        clickButton(driver, closeTransactionBtn, "Close");
        sleep(1000);
        isAlertPresent(driver);
        invisibilityOfLoader(driver);
        sleep(4000);
    }

    // Verify added transaction is available at the top of Transaction List.
    public void verifyAddedTransactionIsListedInTransactionList(String InvoiceNum) {
        boolean flag = false;
        try {
            for (int j = 0; j < InvoiceNumberList.size(); j++) {

                if (InvoiceNumberList.get(j).getAttribute("innerHTML").equals(InvoiceNum)) {
                    ExtentReporter.logger.log(LogStatus.PASS,
                            "Transaction invoice no. " + InvoiceNum + " is listed in Transaction list.");
                    flag = true;
                    break;
                }
            }
            if (flag == false)
                throw new Exception();
        } catch (Exception e) {
            // Log status as failed in report and stop execution
            ExtentReporter.logger.log(LogStatus.FAIL,
                    "Transaction invoice no. " + InvoiceNum + " is Not available in Transaction list.");
            Assert.assertTrue(false,
                    "\nTransaction invoice no. " + InvoiceNum + " is Not available in Transaction list.");
        }
    }

    // Get the alert window text message and compare with expected alert message
    public void verifySaveAlertMessage(String actualAlertMessage, String claimNumber, String Amount, String TransType) {

        // Spaces and \n are added to make the message identical.
        String expectedAlertMessage = "The transaction has been successfully saved:\n Source #:File " + claimNumber
                + " \n" + " Transaction Type:" + TransType + " \n Transaction Amount:$" + Amount;

        // Below expectedAlertMessage string is compared with actual alert
        // message
        if (actualAlertMessage.equals(expectedAlertMessage)) {
            ExtentReporter.logger.log(LogStatus.PASS, "Alert message is as Expected\n" + actualAlertMessage);
        } else {
            ExtentReporter.logger.log(LogStatus.WARNING,
                    "Alert message is Incorrect, not as Expected\n" + actualAlertMessage);
        }
    }

    // Search and select the client from 'Entity Select List' using client name
    // and matching id from excel file
    public void searchAPatientNameFromEntitySelectList(String parentWindow) {

        waitForElementToLoad(driver, 20, lastOrgName);
        // getPageTitle(driver, entitySelectSearchPageTitle);
        visibilityOfElement(driver, lastOrgName, "Last Org Name on Entity Select Search window");
        sleep(3000);
        // click Search button to load Entity List
        clickButton(driver, searchEntityButton, "Search Name of an entity");
        invisibilityOfLoader(driver);
        sleep(3000);

        // Get the list of client names from system
        for (int i = 0; i < entitySelectclientNameList.size(); i++) {

            // compare the client name from system with client name from excel
            // sheet
            // also the client id from system to client id from sheet
            if (entitySelectclientNameList.get(i).getAttribute("innerHTML").trim().equals(claimsdto.clientNameValue)
                    && entitySelectClientIdList.get(i).getAttribute("innerHTML").trim()
                            .equals(claimsdto.clientIDValue)) {
                // select client name whose id matches
                ExtentReporter.logger.log(LogStatus.INFO, claimsdto.clientNameValue + " check box is selected ");
                selectValue(driver, entitySelectclientNameList.get(i), claimsdto.clientNameValue);
                break;
            } else {
                break;
            }
        }
        clickButton(driver, selectEntity, "Select Entity");
        switchToParentWindowfromotherwindow(driver, parentWindow);
    }

    // method to search claims from 'Enter Text #' text box(Top right corner of
    // the screen)
    public ClaimsPage searchClaim() {

        getPageTitle(driver, fileSearchPageTitle);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Enter Claim # from Hospital Create Claim Test Case(Example 66429) & Click Search.");
        claimsSearch(driver, claimsdto.claimNum, claim_Search, Search_btn, policyList);
        sleep(2000);
        getPageTitle(driver, "Claim Folder " + claimsdto.claimNum);

        return new ClaimsPage(driver);
    }

    // This method will change the status of claims.
    public void statusChange(String claimNo) {
        ExtentReporter.logger.log(LogStatus.INFO, "Click Green Change Status button.");
        clickButton(driver, chagneStatus, "Change Status");
        switchToFrameUsingElement(driver, changeFileStatusFrameEle);
        // As File Search Page (Claims) having 2 headers Hence instead of using
        // getPageTitle method below logic is written to verify page title.
        List<WebElement> pageheaders = driver.findElements(By.xpath("//div[@class='pageTitle']"));
        WebElement pageLoader = driver.findElement(By.xpath("//span[@class='txtOrange']"));
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.invisibilityOf(pageLoader));
        Assert.assertEquals(pageheaders.get(0).getAttribute("innerHTML").trim(), "Change File Status",
                "Page title is not matching.");

        ExtentReporter.logger.log(LogStatus.INFO, "Enter/select the below information");
        ExtentReporter.logger.log(LogStatus.INFO, "File Status Date: Enter in Current Date if no date is entered");
        clearTextBox(driver, chagneFileDate, "File Status Date");
        CommonUtilities commUtil = new CommonUtilities();
        enterDataIn(driver, chagneFileDate, commUtil.getSystemDatemm_dd_yyyy(), "File Status Date");
        ExtentReporter.logger.log(LogStatus.INFO, "File Status: Select Opened in Error");
        selectDropdownByValue(driver, fileStatus, fileStatusDropDownOption, "File Status");
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Save]");
        clickButton(driver, saveBtnOnChagneStatusPopup, "Change Status Save");
        sleep(2000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Are you sure you want to change the File Status to Opened in Error?");
        ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]");
        isAlertPresent(driver);
        sleep(5000);
        switchToParentWindowfromframe(driver);
        getPageTitle(driver, "Claim Folder " + claimNo);
        verifyValueFromField(driver, fileStatusOnClaimFolderPage, verifyFileStatusValue, "innerHTML", "File Status");
    }

    // Select Patient.
    public ClaimsPage getPatientDetails() {
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Files]-- Add[File]");
        sleep(2000);
        Actions builder = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        builder.moveToElement(filesMenuTab).build().perform();
        js.executeScript("arguments[0].click();", fileAddMenuOption);
        invisibilityOfLoader(driver);
        sleep(3000);
        getPageTitle(driver, ClaimsDTO.addFilePageTitle);
        ExtentReporter.logger.log(LogStatus.INFO, "Click Magnifying glass by Search Patient.");
        clickButton(driver, patientSearchIcon, "Patient Search");
        waitFor(driver, 10);
        ExtentReporter.logger.log(LogStatus.INFO, "In Last name field enter '" + ClaimsDTO.lastName + "'");
        ExtentReporter.logger.log(LogStatus.INFO, "In Last name field enter '" + ClaimsDTO.firstName + "'");
        String parentWindowId = switchToWindow(driver);
        getPageTitle(driver, ClaimsDTO.entitySearchListPageTitle);
        enterTextIn(driver, lastNameEntitySearchPage, ClaimsDTO.lastName, "Last Name");
        enterTextIn(driver, firstNameEntitySearchPage, ClaimsDTO.firstName, "First Name");
        ExtentReporter.logger.log(LogStatus.INFO, "Press Enter or Search");
        clickButton(driver, searchBtnOnEntitySearchPage, "Entity Search Page's Search");
        sleep(8000);
        Assert.assertEquals(resultOnEntityListPage.getAttribute("innerHTML").trim(),
                ClaimsDTO.lastName + ", " + ClaimsDTO.firstName + ",", "Data displayed after search is not correct");
        waitFor(driver, 5);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Check the checkbox next to" + ClaimsDTO.lastName + "," + ClaimsDTO.lastName + ".");
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Select]");
        sleep(2000);
        clickButton(driver, selectEntityChkBox, "Select Entity Check Box");
        sleep(2000);
        clickButton(driver, selectBtnOnEntitySelectListPage, "Entity Select List Page's Select");
        sleep(5000);
        if (switchToParentWindowfromotherwindow(driver, parentWindowId).equals("false")) {
            // This assert to added to fail the test if switch does not work
            // because test is halted and never failed or progressed.
            assertTrue(false);
        }
        sleep(3000);
        return new ClaimsPage(driver);
    }

    // Enter data in Claims page.
    public void enterDataOnClaimsPage(String clientIdValue) {
        sleep(3000);
        ExtentReporter.logger.log(LogStatus.INFO, "Click the dropdown by file type and select claim ");
        selectDropdownByValue(driver, FileTypeDropDown, ClaimsDTO.fileTypeDropDownValue, "File Type");
        ExtentReporter.logger.log(LogStatus.INFO, "Click the LOB dropdown and select HLP");
        selectDropdownByValue(driver, lobDropDown, ClaimsDTO.lobDropDownValue, "LOB");
        ExtentReporter.logger.log(LogStatus.INFO, "Enter 'Test' in the description field ");
        enterTextIn(driver, descriptionTextBox, ClaimsDTO.description, "Description");
        if (triageMeetingDateField.isDisplayed() == true) {
            ExtentReporter.logger.log(LogStatus.INFO, "Enter today's date in 'Traige Meeting Date' field");
            enterTextIn(driver, triageMeetingDateField, comUtil.getSystemDatemm_dd_yyyy(), "Triage Meeting Date");
        }
        ExtentReporter.logger.log(LogStatus.INFO, "Click File Handler dropdown and select Act Super, EJ");
        selectDropdownByValue(driver, fileHandlerDorpDown, ClaimsDTO.fileHandlerDropDownValue, "File Handler");
        ExtentReporter.logger.log(LogStatus.INFO, "Click State of Loss dropdown and select GA");
        selectDropdownByValue(driver, stateOfLossDorpDown, ClaimsDTO.stateOfLossDropDownValue, "State Of Loss");
        ExtentReporter.logger.log(LogStatus.INFO, "Enter today's date in 'Accident Date' field");
        enterTextIn(driver, accidentDateTextBox, comUtil.getSystemDatemm_dd_yyyy(), "Accident Date");
        ExtentReporter.logger.log(LogStatus.INFO, "Click the magnifying glass next to insured");
        clickButton(driver, insuredSearchIcon, "Insured Search Icon");
        String parentWindowIdSearchEntity = switchToWindow(driver);
        getPageTitle(driver, ClaimsDTO.searchEntityPageTitle);
        ExtentReporter.logger.log(LogStatus.INFO,
                "In the search screen enter the client id that was noted in step 5 Click [Search]");
        sleep(2000);
        enterTextIn(driver, entityClientId, clientIdValue, "Client Id");
        clickButton(driver, searchBtnOnEntitySearchPage, "Entity Search Page's Search");
        sleep(4000);
        waitFor(driver, 10);
        Assert.assertTrue(resultOnEntityListPage.isDisplayed(),
                "Insured Name is not populated on 'Entity Select List' page.");
        ExtentReporter.logger.log(LogStatus.INFO, "Click the checkbox next to the insured's name  Click [Select]");
        sleep(2000);
        clickButton(driver, selectEntityChkBox, "Insured Name");
        sleep(1000);
        clickButton(driver, selectBtnOnEntitySelectListPage, "Select");
        sleep(8000);
        switchToParentWindowfromotherwindow(driver, parentWindowIdSearchEntity);
        sleep(4000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "In the filter criteria section, click the Policy No dropdown and Select [Policy number entered in step 3]");
        selectDropdownByValue(driver, policyNoDDL, claimsdto.policyNum, "Policy Number");
        ExtentReporter.logger.log(LogStatus.INFO, "Click the checkbox next the Prof Liab coverage Click Save as Claim");
        try {
            for (int i = 0; i < coverageDescriptionList.size(); i++) {
                if (coverageDescriptionList.get(i).getAttribute("innerHTML").trim()
                        .equalsIgnoreCase(claimsdto.CoverageDescription)) {
                    clickButton(driver, coverageDescriptionChkBoxList.get(i), "Coverage Description Check Box");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExtentReporter.logger.log(LogStatus.INFO, "Click Save as Claim ");
        clickButton(driver, saveAsClaimBtn, "Save As Claim");

        if (checkduplicateClaimWindow(duplicateClaimPageTitle) == true) {
            clickButton(driver, saveAsClaimBtnOnPsblDuplciateClaimPage, "Save As Claim");
            invisibilityOfLoader(driver);
            sleep(3000);
        } else {
            ExtentReporter.logger.log(LogStatus.INFO, "Duplicat Claims window is not displayed.");
        }
        switchToParentWindowfromframe(driver);
        visibilityOfElement(driver, claimNo, "Claim Number");
    }

    // Method will check for duplicate claim on claims apge.
    public boolean checkduplicateClaimWindow(String duplicatePageTitle) {
        sleep(2000);
        try {
            if (switchToFrameUsingElement(driver,
                    driver.findElement(By.xpath("//iframe[contains(@id,'popupframe')]"))) == true) {
                getPageTitle(driver, duplicatePageTitle);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
