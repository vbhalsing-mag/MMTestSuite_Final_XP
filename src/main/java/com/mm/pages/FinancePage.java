package com.mm.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.dto.FinancePageDTO;
import com.mm.dto.PolicyBinderPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.mm.utils.TestCaseDetails;
import com.relevantcodes.extentreports.LogStatus;

import MMTestCase.BTS;

public class FinancePage extends CommonAction {

    WebDriver driver;
    FinancePageDTO financePageDTO;
    static String batchNumber;
    static String accountNumber;
    static String invoiceAmount;
    String cancelTypeDDLValue = "COMPANY";
    String cancelReasonDDLValue = "COMPOTHER";
    String cancelMethodDLValue = "PRORATA";
    String onDemandInvoiceExcelName = "OnDemandInvoiceCredit";
    String onDemandInvoiceInstallementExcelSheet = "OnDemandInvoiceInstallementBefore";
    String invoicesInstallmentDueDate = "invoicesInstallmentDueDate";
    String PaymentCreditExcelName = "PaymentCredit";
    String openbatchcreditExcelName = "openbatchcredit";
    String FinancePageExcelName = "FinancePage";
    String validatebatchcreditExcelName = "validatebatchcredit";
    String postedbatchcreditExcelName = "postedbatchcredit";
    String alltransactionlistafterpaymentcreditExcelName = "alltransactionlistafterpaymentcredit";
    String pollnstallmentList = "pollnstallmentList";
    String accountSearchPageTitle = "Account Search";
    String allTxnInquireyPageTitle = "All Transactions Inquiry";
    String validateBatchPageTitle = "Validate Batch";
    String cashEntryPageTitle = "Cash Entry";
    String batchFunctionPageTitle = "Batch Functions";
    String invoiceOptionDDLValue = "I";
    String createChargesDDLValue = "N";
    String specifyInvoiceDateDDLValue = "N";
    String specifyDueDateDDLValue = "N";
    String paymentTypeDDLValue = "CHECK";
    String BatchAmount = "1";
    String BatchNoPayment = "1";
    String validlateFieldExpectedValue = "VALIDATE";
    String validlateFieldAttributeValue = "innerHTML";
    String validlateFieldName = "Validate";
    String onDemandPageTitle = "On Demand Invoice";
    String checkNo = "ST12346";
    String accountNoUnExpectedValue = "";
    String accountNovalueAttributeName = "value";
    String policyNo;
    String defaultAmount = "$10.00";
    String zero = "0";
    ExcelUtil exlUtil = new ExcelUtil();

    @FindBy(name = "globalSearch")
    WebElement Policy_Search;

    @FindBy(id = "FM_ACCOUNT_LIST_SEARCH")
    WebElement searchBtn;

    @FindBy(xpath = "//input[@name='search']")
    WebElement mainSearchBtn;

    @FindBy(id = "findPolicyListGrid_CPOLICYNO_0_HREF") // QA
    WebElement policyList;

    @FindBy(name = "policyNo")
    WebElement PolicyNoTxtBox;

    @FindBy(xpath = "//span[@id='CACCOUNTNO'] | //a[@id='accountListGrid_CACCOUNTNO_0_HREF']")
    List<WebElement> accountList;

    @FindBy(id = "CACCOUNTNO")
    WebElement firstAccount;

    @FindBy(xpath = "//a[contains(@id,'accountListGrid_CACCOUNTNO')]|//span[@id='CACCOUNTNO']")
    List<WebElement> accountNumList;

    @FindBy(xpath = "//li[@id='FM_BILLING_ADMIN']//a[@class='fNiv isParent']//span")
    WebElement billingAdminMenu;

    @FindBy(xpath = "//li[@id='FM_ON_DEMAND_INVOICE']//a")
    WebElement onDemandInvoiceMenuOption;

    @FindBy(xpath = "//li[@id='FM_CASH_APPLICATION']//span")
    WebElement paymentsMenu;

    @FindBy(xpath = "//li[@id='FM_CASH_ENTRY']//span")
    WebElement cashEntryMenuOption;

    @FindBy(xpath = "//li[@id='FM_BATCH_FUNCTION']//span")
    WebElement batchFunctionMenuOption;

    @FindBy(name = "invoiceOption")
    WebElement invoiceOptionDDL;

    @FindBy(name = "createCharges")
    WebElement createChargesDDL;

    @FindBy(name = "specifyInvoiceDate")
    WebElement specifyInvoiceDateDDL;

    @FindBy(name = "specifyDueDate")
    WebElement specifyDueDateDDL;

    @FindBy(name = "paymentTypeCode")
    WebElement paymentTypeDDL;

    @FindBy(id = "FM_ON_DEMAND_INV_PROCESS")
    WebElement processButton;

    @FindBy(id = "amountROSPAN")
    WebElement invoiceAmt;

    @FindBy(id = "invoiceNoROSPAN")
    WebElement invoiceNo;

    @FindBy(id = "accountNoCriteriaROSPAN")
    WebElement accountNo;

    @FindBy(id = "FM_CONFIRM_INVOICE_JUMP")
    WebElement jumpButton;

    @FindBy(xpath = "//*[@id = 'btnSaveAsCSV'] | //input[@name='btnSaveAsCSV']")
    List<WebElement> exportExcelLink;

    @FindBy(id = "CPRODUCTCOVERAGEDESC")
    List<WebElement> coverageList;

    @FindBy(xpath = "//select[@id='PM_POLICY_FOLDER_AG'] | //select[@id='PM_QT_POLICY_FOLDER_AG']")
    WebElement policyActionDDL;

    @FindBy(name = "cancellationDate")
    WebElement cancelDateOnCancelPopUp;

    @FindBy(id = "accountingDate_VALUE_CONTAINER")
    WebElement accountingDateOnCancelPopUp;

    @FindBy(name = "cancellationType")
    WebElement cancelTypeaccountingDateOnCancelPopUp;

    @FindBy(name = "cancellationReason")
    WebElement cancelReasonOnCancelPopUp;

    @FindBy(name = "cancellationMethod")
    WebElement cancelMethodOnCancelPopUp;

    @FindBy(name = "cancellationComments")
    WebElement cancelCommentsCancelPopUp;

    @FindBy(id = "CANCEL_DONE")
    WebElement cancelBtnOnCancelPopUp;

    @FindBy(id = "PM_TAIL_CLOSE")
    WebElement tailCloseButton;

    @FindBy(name = "workflowExit_Ok")
    WebElement okPolicySaveAsWIPPopup;

    @FindBy(id = "FM_CE_BATCH_NEW")
    WebElement newButton;

    @FindBy(name = "invoiceNo")
    WebElement invoiceNoOnCashEntryPage;

    @FindBy(name = "receiptNo")
    WebElement checkNoOnCashEntryPage;

    @FindBy(name = "receiptAmount")
    WebElement amountOnCashEntryPage;

    @FindBy(id = "FM_CE_BATCH_SAVE")
    WebElement saveBtnOnCashEntryPage;

    @FindBy(name = "depositDate")
    WebElement depositDate;

    @FindBy(id = "batchNoROSPAN")
    WebElement batchNo;

    @FindBy(id = "CBATCHNO")
    List<WebElement> batchNoOnBatchFunPage;

    @FindBy(id = "FM_CASH_BATCH_LOG_VALIDAT")
    WebElement validateBatchBtn;

    @FindBy(name = "noOfPayment")
    WebElement numberOfPaymentTxtBox;

    @FindBy(name = "batchAmount")
    WebElement batchAmount;

    @FindBy(id = "FM_VALIDATE_BATCH_DONE")
    WebElement doneBatchPopUp;

    @FindBy(id = "FM_CASH_BATCH_LOG_POST")
    WebElement postBatchBtn;

    @FindBy(id = "CBATCHSTATUSCODE")
    WebElement validateField;

    @FindBy(id = "currentAccountBalanceROSPAN")
    WebElement currBalOnAllTxnEnqPage;

    @FindBy(xpath = "//a[@class='selectedMenu fNiv isParent']//span")
    WebElement accountMenuTab;

    @FindBy(name = "accountHolder")
    WebElement accountHolder;

    @FindBy(xpath = "//li[@id='FM_MAINTAIN_ACCT_MENU']//a//span")
    WebElement maintainAccount;

    @FindBy(xpath = "//iframe[contains(@id,'popupframe')]")
    WebElement iFrameElement;

    @FindBy(xpath = "//div[@id='CLONGDESCRIPTION']")
    List<WebElement> accountType;

    @FindBy(xpath = "//input[@name='entitySearch_lastOrOrgName']")
    WebElement lastOrOrgNameInputField;

    @FindBy(id = "FM_FIND_NEW_ACCOUNT")
    WebElement newAccountBtn;

    @FindBy(id = "CI_ENTITY_SELECT_SCH_SCH")
    WebElement searchBtnEntitySearchPage;

    @FindBy(xpath = "//input[@name='chkCSELECTIND']")
    WebElement searchedEntityChkBox;

    @FindBy(id = "CI_ENT_SEL_LST_FORM_SEL")
    WebElement selectBtnSearchedEntity;

    @FindBy(xpath = "//input[@name='accountNo']")
    WebElement accountNoFieldValue;

    @FindBy(id = "FM_MAINT_ACCT_SELECT")
    WebElement selectAdressBtn;

    @FindBy(id = "FM_SEL_ACCT_ADDR_DONE")
    WebElement DoneBtnOnAddListPopUp;

    @FindBy(id = "FM_MAINT_ACCT_SAVE")
    WebElement SaveBtnOnMaintainActPage;

    @FindBy(id = "longDescriptionROSPAN")
    WebElement descriptionAfterSave;

    @FindBy(id = "FM_SEL_ACCT_TYPE_DONE")
    WebElement doneBtnOnSelectAccTypePopUp;

    @FindBy(name = "billingFrequency")
    WebElement billingFrequencyDDL;

    @FindBy(name = "baseBillMmDd")
    WebElement startDate;

    @FindBy(name = "billLeadDays")
    WebElement leadDays;

    @FindBy(id = "FM_MAINT_ACCT_SAVE")
    WebElement saveMaintAction;

    @FindBy(id = "FM_FULL_INQ_RECV_TAB")
    WebElement receivableTab;

    @FindBy(id = "PM_COMMON_TABS_SAVE")
    WebElement saveOptionBtn;

    @FindBy(xpath = "//select[@name='saveAsCode']")
    WebElement saveAsDropDown;

    @FindBy(id = "PM_SAVE_OPTION_OK")
    WebElement saveOptionOkBtn;

    @FindBy(name = "workflowExit_Ok")
    WebElement Exit_Ok;

    @FindBy(xpath = "//select[contains(@name,'confirmed')]")
    WebElement productNotifyDropDown;

    @FindBy(id = "PM_NOTIFY_CLOSE")
    WebElement prodNotifyClose;

    @FindBy(id = "FM_FULL_INQ_ACCOUNT_TAB")
    WebElement accountTab;

    @FindBy(id = "FM_FULL_INQ_ACC_INV")
    WebElement invoicesButton;

    @FindBy(name = "effectiveFromDate")
    WebElement effectiveFromDate;

    @FindBy(name = "endorsementCode")
    WebElement endorsementReason;

    @FindBy(name = "transactionComment")
    WebElement CommentsTxtBoxOnEndorsePolicyPopup;

    @FindBy(id = "PM_ENDORSE_OK")
    WebElement endorsePolicyOK;

    @FindBy(name = "issueCompanyEntityId")
    WebElement issueCompany;

    @FindBy(name = "batchNo")
    WebElement batchNumField;

    @FindBy(name = "batchStatus")
    WebElement batchStatusDDL;

    @FindBy(xpath = "//span[contains(.,'Account No')]")
    WebElement accountNoColumnHeader;

    @FindBy(id = "PM_LIMIT_SHARING")
    WebElement limitSharingBtn;

    @FindBy(xpath = "//select[@name = 'shareDtlOwnerB']")
    WebElement sharedOwner;

    @FindBy(id = "PM_LIMIT_SHARING_SAVE")
    WebElement LimitSharingSaveBtn;

    @FindBy(id = "PM_LIMIT_SHARING_CLOSE")
    WebElement LimitSharingCloseBtn;

    @FindBy(xpath = "//table[@id = 'sharedGroupListGrid']//div[@dataFid = 'CSHAREGROUPDESCLOVLABEL']")
    List<WebElement> sharedGroupDescription;

    @FindBy(xpath = "//a[@id='PM_PT_VIEWPOL']//span[@class='tabWithNoDropDownImage']")
    WebElement policyTab;

    @FindBy(id = "termEffectiveFromDateROSPAN")
    WebElement termEffDate;

    @FindBy(name = "workflowExit_Ok")
    WebElement workFlowExitOk;

    @FindBy(id = "topnav_Policy")
    WebElement policyMainTab;

    // Constructor to initialize variables on Finance page.
    public FinancePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        financePageDTO = new FinancePageDTO(TestCaseDetails.testDataDictionary);
    }

    // Search the account number using Account Holder name
    public FinancePage searchAccountUsingSearchCriteria() {

        ExtentReporter.logger.log(LogStatus.INFO,
                "Using the account holder from TC42251" + " Enter name " + financePageDTO.accountHolderName
                        + " in accountholder search box. Click [Search]. Verify Search Results are displayed");
        enterTextIn(driver, accountHolder, financePageDTO.accountHolderName, "Account Holder");
        clickButton(driver, searchBtn, "Search");
        invisibilityOfLoader(driver);
        return new FinancePage(driver);
    }

    public FinancePage navigateToWindowAndSelectEntity() {
        String parentWindow = switchToWindow(driver);
        HomePage homePage = new HomePage(driver);
        homePage.selectEntity(parentWindow, "");
        return new FinancePage(driver);
    }

    // Select the last account from the account list shown in search results
    public FinancePage selectLastAccountFromAccountList() {
        sleep(5000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click on the last account number. Verify All Transaction Inquiry screen opens");

        // We need to click twice on account no. column to sort account no.s-
        // we are sorting because we need to select last account from list
        click(driver, accountNoColumnHeader, "Column header AccountNo");
        click(driver, accountNoColumnHeader, "Column header AccountNo");
        sleep(2000);
        click(driver, accountNumList.get(0), "First account after sorting");
        sleep(2000);
        invisibilityOfLoader(driver);
        return new FinancePage(driver);
    }

    // Select Maintain Account menu from Account menu list and edit the account
    // information
    public FinancePage maintainAccount() {

        sleep(2000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click Account>Maintain Account. Verify Maintain Account page is opened");
        // Hover over Account tab and select maintainAccount menu option
        Actions act = new Actions(driver);
        act.moveToElement(accountMenuTab).build().perform();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", maintainAccount);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Select Billing Frequency DDL and change from current monthly to new quarterly. Verify Billing Frequency is changed quarterly");
        sleep(2000);
        selectDropdownByVisibleText(driver, billingFrequencyDDL, financePageDTO.billingFrequency, "Billing Frequency");

        // Start date is the month and day of the current date i.e. MMdd
        ExtentReporter.logger.log(LogStatus.INFO,
                "Change Start date (mmdd) Example 0101 to currentdate(format has to be 0000). Verify Start date is changed to current date");
        clearTextBox(driver, startDate, "Start Date");
        CommonUtilities comUtil = new CommonUtilities();
        String sysDate = comUtil.getSystemDatemmddyyyy();
        String todaysdate = sysDate.substring(0, 4);
        enterDataIn(driver, startDate, todaysdate, "Start Date");
        ExtentReporter.logger.log(LogStatus.INFO,
                "Change Lead Days from current number to another number accordingly(example 1 to 10). Verify Lead days is changed to different number");
        // convert lead days field current value and random generated value from
        // string
        // to int for comparison
        int leadDaysValue = Integer.valueOf(leadDays.getAttribute("value"));
        int randomNewValue = Integer.valueOf(randomNumGenerator(1, "123456789"));

        // New value to be entered (randomNewValue) and current lead days value
        // should
        // be different and between 1 to 10.
        if (randomNewValue == leadDaysValue) {
            randomNewValue++;
            if (randomNewValue > 10) {
                randomNewValue = 1;
            }
        }
        // covert new value to string
        String leadDaysNewValue = Integer.toString(randomNewValue);

        clearTextBox(driver, leadDays, "Lead Days");
        enterDataIn(driver, leadDays, leadDaysNewValue, "Lead Days");
        return new FinancePage(driver);
    }

    // Save the changes made to Account in maintain Account method.
    public FinancePage saveAccountInformation() {
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Save]. Verify Account Information is saved message appears");
        clickButton(driver, saveMaintAction, "Save");
        sleep(3000);
        ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]. Verify Message is closed and changes are saved");
        if (isAlertPresent(driver) == false) {
            ExtentReporter.logger.log(LogStatus.INFO, "Alert not present.");
        }

        return new FinancePage(driver);
    }

    // Search Account from Search Account text field on Finance Home Page.
    public FinancePage searchPolicyOnFinanceHomePage() {
        sleep(2000);
        invisibilityOfLoader(driver);
        getPageTitle(driver, accountSearchPageTitle);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Using the policy from 'Issue Policy Forms' test case enter Policy number in Policy#: search box and click Search.");
        enterTextIn(driver, PolicyNoTxtBox, financePageDTO.policyNum, "Policy Number");
        clickButton(driver, searchBtn, "Search");
        invisibilityOfLoader(driver);
        sleep(3000);
        if (verifypolicyNotDisplayErrorMsg(driver).equals("true")) {
            ExtentReporter.logger.log(LogStatus.INFO,
                    "Searching for backup policy as account not displayed for previous policy- "
                            + financePageDTO.policyNum);
            clearTextBox(driver, PolicyNoTxtBox, "Policy Number");
            enterTextIn(driver, PolicyNoTxtBox, financePageDTO.backUpPolicyNum, "Policy Number");
            clickButton(driver, searchBtn, "Search");
            invisibilityOfLoader(driver);
            sleep(3000);
        }
        Assert.assertTrue(accountList.get(0).isDisplayed(),
                "Account list is not displayed on " + "Account Search" + "page");
        return new FinancePage(driver);
    }

    // Search Account from Search Account text field on Finance Home Page for
    // New BTS env.
    public FinancePage searchPolicyOnFinanceHomePageBTS_QA() {
        PolicyQuotePage PQP = new PolicyQuotePage(driver);
        HomePage hp = new HomePage(driver);
        sleep(2000);
        invisibilityOfLoader(driver);
        getPageTitle(driver, accountSearchPageTitle);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Using the policy from 'Issue Policy Forms' test case enter Policy number in Policy#: search box and click Search.");
        enterTextIn(driver, PolicyNoTxtBox, financePageDTO.policyNum, "Policy Number");
        clickButton(driver, searchBtn, "Search");
        invisibilityOfLoader(driver);
        sleep(3000);
        if (verifypolicyNotDisplayErrorMsg(driver).equals("true")) {
            ExtentReporter.logger.log(LogStatus.INFO,
                    "Searching for backup policy as account not displayed for previous policy.");
            try {
                // This will search new policy when previous policy is not
                // working.
                hp.navigateToPolicyPageThroughPolicyHeaderLink();
                PQP.searchBackUpPolicyUsingSearchCriteriaBTS_QA();
                String policyNum = PQP.policyNo();
                exlUtil.writeData("TC42246", "PolicyNum", policyNum, 1, BTS.ExcelPath);
                hp.navigateToFinanceHomePage();
                invisibilityOfLoader(driver);
                getPageTitle(driver, accountSearchPageTitle);
                enterTextIn(driver, PolicyNoTxtBox, policyNum, "Policy Number");
                clickButton(driver, searchBtn, "Search");
                invisibilityOfLoader(driver);
                sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            invisibilityOfLoader(driver);
            sleep(3000);
        }
        Assert.assertTrue(accountList.get(0).isDisplayed(),
                "Account list is not displayed on " + "Account Search" + "page");
        return new FinancePage(driver);
    }

    // This method will click on first account number displayed after Policy
    // search.
    public FinancePage openFirstAccount() {
        sleep(5000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click Account No(" + accountList.get(0).getAttribute("innerHTML") + ").");
        click(driver, accountList.get(0), "Account number");
        invisibilityOfLoader(driver);
        sleep(4000);
        getPageTitle(driver, allTxnInquireyPageTitle);
        return new FinancePage(driver);
    }

    public FinancePage onDemandInvoice() {
        ExtentReporter.logger.log(LogStatus.INFO, "Click Billing Admin>On Demand Invoice.");
        navigatetoMenuItemPage(driver, billingAdminMenu, onDemandInvoiceMenuOption);
        sleep(4000);
        getPageTitle(driver, onDemandPageTitle);
        ExtentReporter.logger.log(LogStatus.INFO, "Select the below options.");
        ExtentReporter.logger.log(LogStatus.INFO, "Invoice Option: Immediately.");
        ExtentReporter.logger.log(LogStatus.INFO, "Create Charges: No.");
        ExtentReporter.logger.log(LogStatus.INFO, "Specify Invoice Date: No.");
        ExtentReporter.logger.log(LogStatus.INFO, "No Specify Due Date: No.");
        selectDropdownByValue(driver, invoiceOptionDDL, invoiceOptionDDLValue, "Invoice Option");
        selectDropdownByValue(driver, createChargesDDL, createChargesDDLValue, "Invoice Option");
        selectDropdownByValue(driver, specifyInvoiceDateDDL, specifyInvoiceDateDDLValue, "Invoice Option");
        selectDropdownByValue(driver, specifyDueDateDDL, specifyDueDateDDLValue, "Invoice Option");
        accountNumber = accountNo.getAttribute("innerHTML");
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Process].");
        clickButton(driver, processButton, "Process");
        sleep(40000);
        invisibilityOfLoader(driver);

        switchToFrameUsingElement(driver,
                driver.findElement(By.xpath("//iframe[contains(@src,'&accountNo=" + accountNumber + "')]")));

        // TODO-visibilityOfElement(driver, jumpButton, "jump button");

        invoiceAmount = invoiceAmt.getAttribute("innerHTML");
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Jump].");
        clickButton(driver, jumpButton, "Jump");
        invisibilityOfLoader(driver);
        switchToParentWindowfromframe(driver);
        return new FinancePage(driver);
    }

    public FinancePage exportExcelSheet(String saveExcelNameAs) {
        sleep(2000);
        ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
        downloadExcel(saveExcelNameAs);
        return new FinancePage(driver);
    }

    public FinancePage selectReceivableTabAndExportExcel(String saveExcelNameAs) {
        ExtentReporter.logger.log(LogStatus.INFO, "Receivable page is opened");
        clickButton(driver, receivableTab, "Receivable");
        invisibilityOfLoader(driver);
        sleep(2000);
        exportExcelSheet(saveExcelNameAs);
        return new FinancePage(driver);
    }

    public HomePage selectAccountTabInvoicesButtonAndExportExcel() {
        clickButton(driver, accountTab, "Account tab");
        invisibilityOfLoader(driver);
        ExtentReporter.logger.log(LogStatus.INFO, "Invoices transactions page is displayed");
        clickButton(driver, invoicesButton, "Invoices");
        invisibilityOfLoader(driver);
        exportExcelSheet(financePageDTO.invoicesInstallmentDueDateExcel);
        return new HomePage(driver);
    }

    public String readDataFromExcelSheet(String readerDataSheetName, String readerTestDataColumnName,
            int readerDataRowNumber, String readerExportedExcelSheetName) {
        String columnCellValue = "";
        try {
            columnCellValue = getDataFromExcel(readerDataSheetName, readerTestDataColumnName, readerDataRowNumber,
                    ExtentReporter.reportFolderPath + "\\" + readerExportedExcelSheetName + ".xlsx");
            sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.WARNING, "Error while reading data from excel");
        }
        return columnCellValue;
    }

    public FinancePage writeDataInExcelSheet(String cellValue, String writerTCSheetNumber,
            String writerTestDataColumnHeader, int writerRowNumber) {
        sleep(2000);
        writeData(writerTCSheetNumber, writerTestDataColumnHeader, cellValue, writerRowNumber,
                System.getProperty("user.dir") + "\\src\\main\\resources\\Form_Data.xlsx");

        sleep(3000);
        return new FinancePage(driver);

    }

    public FinancePage getInvoiceAmountFromExcel() {
        sleep(2000);
        // below line of code will copy file from temp location to given
        // location
        copyFile(financePageDTO.saveFileName);
        sleep(2000);
        // Read invoiceNumber from saved excel sheet.
        String invoiceNumber = readDataFromExcelSheet(financePageDTO.dataSheetName,
                financePageDTO.testDataColumnName_Numbers, financePageDTO.dataRowNumber,
                financePageDTO.exportedExcelSheetName);

        // Read Amount from saved excel sheet.
        String Amount = readDataFromExcelSheet(financePageDTO.dataSheetName, financePageDTO.testDataColumnName_Amount,
                financePageDTO.dataRowNumber, financePageDTO.exportedExcelSheetName);
        sleep(2000);
        // write invoiceNumber to Form_Dat sheet.
        writeDataInExcelSheet(invoiceNumber, financePageDTO.TCSheetNumber,
                financePageDTO.testDataColumnheader_InvoiceNumber, financePageDTO.rowNumber);

        // write Amount to Form_Dat sheet.
        writeDataInExcelSheet(Amount, financePageDTO.TCSheetNumber, financePageDTO.testDataColumnheader_Amount,
                financePageDTO.rowNumber);
        sleep(3000);
        return new FinancePage(driver);
    }

    // Get the next day of the due date column from exported excel sheet
    public String nextDayOfDueDate() {
        sleep(1000);
        String nextDay = null;
        try {
            String dueDate = financePageDTO.retroDate;
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date date = formatter.parse(dueDate);
            sleep(1000);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            sleep(1000);
            Date d = c.getTime();
            System.out.println("dueDate= " + dueDate);
            nextDay = formatter.format(d);
            System.out.println("nextDay= " + nextDay);
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.INFO,
                    "Unable to parse date becaue Retero date field in Form_Data sheet is empty.");
        }
        return nextDay;
    }

    // Endorse policy scenario with input having date
    public PolicyIndicationPage policyEndorsementWithDate(String PolicyNo, String nextDayOfDueDate) {
        invisibilityOfLoader(driver);
        switchToParentWindowfromframe(driver);
        sleep(5000);
        // Select Endorsement from Policy Action
        ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Endorsement.");
        selectDropdownByVisibleText(driver, policyActionDDL, financePageDTO.policyAction, "Policy Action");
        sleep(3000);
        // Navigate to pop up frame using policy no.
        switchToFrameUsingElement(driver,
                driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
        waitForElementToLoad(driver, 10, endorsementReason);
        // Enter Data in pop up like Effective Date,Endorsement Reason
        ExtentReporter.logger.log(LogStatus.INFO,
                "Enter/Select Below Information: Effective Date:Policy Effective Date Accounting Date: Fixed Date Reason: Issue Policy Forms Comment: Issue Policy Forms");
        // Effective Date is the retroDate from Sheet
        clearTextBox(driver, effectiveFromDate, "Effective Date");

        if (nextDayOfDueDate == null) {
            CommonUtilities comUtil = new CommonUtilities();
            String currentDate = comUtil.getSystemDatemmddyyyy();
            enterDataIn(driver, effectiveFromDate, currentDate, "Effective Date");
            sleep(2000);
            exlUtil.writeData(financePageDTO.TCSheetNumber, "retroDate", "'" + currentDate, 1,
                    System.getProperty("user.dir") + "\\src\\main\\resources\\Form_Data.xlsx");
        } else {
            enterDataIn(driver, effectiveFromDate, nextDayOfDueDate, "Effective Date");
        }
        selectDropdownByVisibleText(driver, endorsementReason, financePageDTO.endorsementReason, "Reason");
        enterTextIn(driver, CommentsTxtBoxOnEndorsePolicyPopup, financePageDTO.endorsementComment, "Comments");
        ExtentReporter.logger.log(LogStatus.INFO, "Click [OK].");
        sleep(1000);
        clickButton(driver, endorsePolicyOK, "OK");
        sleep(5000);

        // If next date is not available or not in range then it will add date
        // in given
        // range.
        String nextDay = null;
        if (verifyAlertDisplay(driver) == true) {
            try {
                String alertText = getAlertText(driver);
                String[] AlldateDisplayedOnAlert = alertText.split("and", 42);
                String[] startingDate = AlldateDisplayedOnAlert[0].split(" ", 31);
                String dueDate = startingDate[5].trim();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date date = formatter.parse(dueDate);
                sleep(1000);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DATE, 1);
                sleep(1000);
                Date d = c.getTime();
                nextDay = formatter.format(d);
            } catch (Exception e) {
                e.printStackTrace();
                ExtentReporter.logger.log(LogStatus.WARNING, "Error while creating substitute date for Endorsement");
            }
            exlUtil.writeData(financePageDTO.TCSheetNumber, "retorDate", nextDay, 1,
                    System.getProperty("user.dir") + "\\src\\main\\resources\\Form_Data.xlsx");
            acceptAlert(driver);
            clearTextBox(driver, effectiveFromDate, "Effective Date");
            enterDataIn(driver, effectiveFromDate, nextDay, "Effective Date");
            clickButton(driver, endorsePolicyOK, "OK");
            // below code will write New Next date to excel which will be used
            // for add
            // coverage flow.
        }
        sleep(8000);
        switchToParentWindowfromframe(driver);

        return new PolicyIndicationPage(driver);
    }

    public PolicyIndicationPage selectCoverageFromGridList() {

        sleep(4000);
        // Get coverage count from the grid list on coverage page
        for (int i = 0; i < coverageList.size(); i++) {
            // compare if the coverage selected from excel sheet is same as
            // coverage from grid on coverage page
            if (coverageList.get(i).getAttribute("innerHTML").trim()
                    .equalsIgnoreCase(financePageDTO.coverageNameFromGrid)) {
                ExtentReporter.logger.log(LogStatus.INFO,
                        financePageDTO.coverageNameFromGrid + " Coverage is selected from Grid");
                // select the coverage from grid if it matches
                selectValue(driver, coverageList.get(i), financePageDTO.coverageNameFromGrid);
                // Assert.assertTrue(coverageList.get(i).isSelected(),coverageList.get(i)+"is
                // NOt selected");
                break;
            }
        }
        return new PolicyIndicationPage(driver);
    }

    public FinancePage cashEntry() {
        ExtentReporter.logger.log(LogStatus.INFO, "Click Payments>Cash Entry");
        navigatetoMenuItemPage(driver, paymentsMenu, cashEntryMenuOption);
        sleep(2000);
        invisibilityOfLoader(driver);
        getPageTitle(driver, cashEntryPageTitle);
        ExtentReporter.logger.log(LogStatus.INFO, "Click [New]");
        clickButton(driver, newButton, "New");
        invisibilityOfLoader(driver);
        ExtentReporter.logger.log(LogStatus.INFO, "Payment Type: Check");
        selectDropdownByValue(driver, paymentTypeDDL, paymentTypeDDLValue, "Payment Type");
        ExtentReporter.logger.log(LogStatus.INFO, "Invoice No: " + financePageDTO.Number + "");
        sleep(4000);
        enterTextIn(driver, invoiceNoOnCashEntryPage, financePageDTO.Number, "Cash Entry Page's invoice Number");
        sleep(18000);
        // check no element is clicked to enable
        isAlertPresent(driver);
        checkNoOnCashEntryPage.click();
        sleep(6000);
        isAlertPresent(driver);
        if (financePageDTO.Amount.equals(zero)) {
            ExtentReporter.logger.log(LogStatus.INFO, "Enter Default Amount:" + defaultAmount + "");
            enterTextIn(driver, amountOnCashEntryPage, defaultAmount, "Cash Entry Page's Amount");
        } else {
            ExtentReporter.logger.log(LogStatus.INFO, "Amount:" + financePageDTO.Amount + "");
            enterTextIn(driver, amountOnCashEntryPage, financePageDTO.Amount, "Cash Entry Page's Amount");
        }
        sleep(5000);
        isAlertPresent(driver);
        ExtentReporter.logger.log(LogStatus.INFO, "Check No: Enter ST12346");
        enterTextIn(driver, checkNoOnCashEntryPage, checkNo, "Cash Entry Page's Check Number");
        sleep(2000);
        isAlertPresent(driver);
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Save]");
        clickButton(driver, saveBtnOnCashEntryPage, "Cash Entry Page's Save");
        sleep(2000);
        if (isAlertPresent(driver) == false) {
            ExtentReporter.logger.log(LogStatus.INFO, "Alert not present.");
        }
        sleep(4000);
        ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
        depositDate.sendKeys(Keys.F6);
        depositDate.sendKeys(Keys.TAB);
        downloadExcel(PaymentCreditExcelName);
        return new FinancePage(driver);

    }

    public FinancePage batchFunction() {
        batchNumber = batchNo.getAttribute("innerHTML");
        sleep(3000);
        ExtentReporter.logger.log(LogStatus.INFO, "Click Payments>Batch Functions");
        navigatetoMenuItemPage(driver, paymentsMenu, batchFunctionMenuOption);
        invisibilityOfLoader(driver);
        getPageTitle(driver, batchFunctionPageTitle);
        ExtentReporter.logger.log(LogStatus.INFO, "Select Batch that was just created(usually will be the first one)");
        for (int i = 0; i < batchNoOnBatchFunPage.size(); i++) {
            if (batchNoOnBatchFunPage.get(i).getAttribute("innerHTML").equals(batchNumber)) {
                clickButton(driver, batchNoOnBatchFunPage.get(i), "Batch Number");
                break;
            }
        }
        ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
        // Shift cursor focus on save pdf option
        batchNumField.sendKeys(Keys.F6);
        batchNumField.sendKeys(Keys.TAB);
        batchStatusDDL.sendKeys(Keys.F6);
        downloadExcel(openbatchcreditExcelName);

        return new FinancePage(driver);
    }

    public FinancePage validateBatch() {
        String batchNumber = batchNoOnBatchFunPage.get(0).getAttribute("innerHTML");

        sleep(4000);
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Validate Batch]");

        sleep(2000);
        clickButton(driver, validateBatchBtn, "Validate Batch");
        sleep(2000);
        switchToFrameUsingElement(driver,
                driver.findElement(By.xpath("//iframe[contains(@src,'batchNo=" + batchNumber + "')]")));
        // getPageTitle(driver, validateBatchPageTitle);
        ExtentReporter.logger.log(LogStatus.INFO, "Enter below information");

        if (financePageDTO.Amount.equals(zero)) {
            ExtentReporter.logger.log(LogStatus.INFO, "Enter Default Amount:" + defaultAmount + "");
            enterTextIn(driver, batchAmount, defaultAmount, "batch Amount");
        } else {
            ExtentReporter.logger.log(LogStatus.INFO, "Enter Amount:" + financePageDTO.Amount + "");
            enterTextIn(driver, batchAmount, financePageDTO.Amount, "batch Amount");
        }
        ExtentReporter.logger.log(LogStatus.INFO, "No. of Payment: 1");
        enterTextIn(driver, numberOfPaymentTxtBox, BatchNoPayment, "Batch Number Of Payment");
        ExtentReporter.logger.log(LogStatus.INFO, "Click[Done]");
        clickButton(driver, doneBatchPopUp, "Done");
        sleep(4000);
        switchToParentWindowfromframe(driver);
        invisibilityOfLoader(driver);
        ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
        downloadExcel(validatebatchcreditExcelName);
        return new FinancePage(driver);

    }

    public FinancePage postBatchFunctionality() {
        ExtentReporter.logger.log(LogStatus.INFO, "Click Post Batch Button.");
        clickButton(driver, postBatchBtn, "Post Batch");
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Ok]");
        acceptAlert(driver);
        ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");

        downloadExcel(postedbatchcreditExcelName);
        return new FinancePage(driver);
    }

    // this method will download excel sheet
    public FinancePage downloadExcel(String fileName) {
        sleep(3000);
        isAlertPresent(driver);
        clickButton(driver, exportExcelLink.get(0), "Export Excel");
        exlUtil.downloadExcel();
        sleep(2000);
        copyFile(fileName);
        return new FinancePage(driver);
    }

    // this method will download excel sheet from Receivable Policy inquiry for
    // Poll
    // transaction inquirey list.
    public FinancePage downloadExcelPollTxnInq(String fileName) {
        sleep(5000);
        clickButton(driver, exportExcelLink.get(1), "Export Excel");
        exlUtil.downloadExcel();
        // Below logic will verify if file present at detination if not it will
        // download file again and copy it to destination folder.
        if (copyFile(fileName) == true) {
            sleep(2000);
            clickButton(driver, exportExcelLink.get(1), "Export Excel");
            copyFile(fileName);
        }
        return new FinancePage(driver);
    }

    public void donwloadFinalSheetBySearchingAccountNo() {
        ExtentReporter.logger.log(LogStatus.INFO,
                "Enter account number in account search box in upper right corner of the page.");
        sleep(2000);
        clearTextBox(driver, Policy_Search, "Enter Policy # text field");
        enterTextIn(driver, Policy_Search, accountNumber, "Enter Policy # text field");
        ExtentReporter.logger.log(LogStatus.INFO, "Click search button and Verify full policy page is displayed");
        sleep(2000);
        clickButton(driver, mainSearchBtn, "Search button");
        invisibilityOfLoader(driver);
        sleep(8000);
        getPageTitle(driver, allTxnInquireyPageTitle);
        ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
        downloadExcel(alltransactionlistafterpaymentcreditExcelName);
    }

    // This method will download receivable list.
    public HomePage receivableDownload(String fileName) {
        searchPolicyOnFinanceHomePage();
        openFirstAccount();
        clickButton(driver, receivableTab, "Receivable");
        sleep(2000);
        downloadExcelPollTxnInq(fileName);
        return new HomePage(driver);
    }

    // For QA -This method will download receivable list from same page without
    // searching policy .
    public HomePage receivableDownloaWithoutNavigationForQA(String fileName) {
        clickButton(driver, receivableTab, "Receivable");
        downloadExcelPollTxnInq(fileName);
        return new HomePage(driver);
    }

    // below method will cancel UMB_PL_Coverage.
    public FinancePage selectUMBCoverage() {
        for (int i = 0; i < coverageList.size(); i++) {
            if (coverageList.get(i).getAttribute("innerHTML").equals(financePageDTO.coverage)) {
                clickButton(driver, coverageList.get(i), coverageList.get(i).getAttribute("innerHTML"));
                ExtentReporter.logger.log(LogStatus.INFO, "Select" + financePageDTO.coverage + " Coverage.");
                break;
            }
        }
        /*
         * Assert.assertTrue(currBalOnAllTxnEnqPage.getAttribute("innerHTML").
         * equals( financePageDTO.currunetBalance),
         * "Current Balance is not zero on All transaction enquirey Page");
         */
        return new FinancePage(driver);
    }

    // this method will select cancel from action drop down.
    public FinancePage selectCancelFromPolicyActionDDL() {
        sleep(3000);
        cancelwindowHandle();
        if (verifyAlertDisplay(driver) == true) {
            verifyChangeremoveCoverageAlertdisplayes();
        } else {
            sleep(3000);
            switchToFrameUsingElement(driver, driver
                    .findElement(By.xpath("//iframe[contains(@src,'policyNo=" + financePageDTO.policyNum + "')]")));
            verifyTailCoverage();
            switchToParentWindowfromframe(driver);
            switchToFrameUsingElement(driver, driver
                    .findElement(By.xpath("//iframe[contains(@src,'policyNo=" + financePageDTO.policyNum + "')]")));
            sleep(5000);
            clickButton(driver, workFlowExitOk, "Ok");
            invisibilityOfLoader(driver);
            switchToParentWindowfromframe(driver);
        }
        return new FinancePage(driver);
    }

    // Code to handle cancel pop up window.
    public void cancelwindowHandle() {
        // If the 'Cancel' option is not available in policy action DDL then
        // delete WIP
        // and select Cancel
        if (selectDropdownByValueFromPolicyActionDDL(driver, policyActionDDL, financePageDTO.policyAction,
                "Policy Action").equals("false")) {

            // Deleting the Work in progress will enable required action from
            // policy Action
            // DDL
            PolicyQuotePage policyquotepage = new PolicyQuotePage(driver);
            if (policyquotepage.deleteWIPForReUse() == false) {
                PolicyBinderPageDTO policybinderpageDTO = new PolicyBinderPageDTO(TestCaseDetails.testDataDictionary);
                PolicyBinderPage pbp = new PolicyBinderPage(driver);
                selectDropdownByValueFromPolicyActionDDL(driver, policyActionDDL, financePageDTO.reinstate,
                        "Policy Action");
                sleep(1000);
                acceptAlert(driver);
                sleep(2000);
                saveOption(driver, saveOptionBtn, saveAsDropDown, saveOptionOkBtn, Exit_Ok,
                        policybinderpageDTO.saveAsPolicyValue, pbp.policyNo());
            }
            sleep(5000);
            // DeleteWIP will refresh page, so select the coverage again to
            // select Cancel
            selectUMBCoverage();
            sleep(2000);
            selectDropdownByValue(driver, policyActionDDL, financePageDTO.policyAction, "Policy Action");
        }
        sleep(1000);
        switchToFrameUsingElement(driver,
                driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + financePageDTO.policyNum + "')]")));
        sleep(2000);
        enterTextIn(driver, cancelDateOnCancelPopUp, termEffDate.getAttribute("innerHTML"), "Cancel Date");
        verifyValueFromField(driver, accountingDateOnCancelPopUp, "N", "iseditable", "Accounting Date");
        selectDropdownByValue(driver, cancelTypeaccountingDateOnCancelPopUp, cancelTypeDDLValue, "Cancel Type");
        selectDropdownByValue(driver, cancelReasonOnCancelPopUp, cancelReasonDDLValue, "Cancel Type");
        selectDropdownByValue(driver, cancelMethodOnCancelPopUp, cancelMethodDLValue, "Cancel Type");
        enterTextIn(driver, cancelCommentsCancelPopUp, financePageDTO.cancelComment, "Comments");
        clickButton(driver, cancelBtnOnCancelPopUp, "Cancel");
    }

    // verify Alert to change coverage limit while canceling coverage is
    // displayed.
    public void verifyChangeremoveCoverageAlertdisplayes() {
        if (getAlertText(driver).contains("reset the owner of limit sharing groups")) {
            PolicyBinderPage pbp = new PolicyBinderPage(driver);
            acceptAlert(driver);
            switchToParentWindowfromframe(driver);
            pbp.endorsementFromActionDropDownwithoutBackupPolicy();
            pbp.endorsePolicy(financePageDTO.policyNum);
            sleep(3000);
            clickButton(driver, policyTab, "Policy Tab");
            invisibilityOfLoader(driver);
            sleep(2000);
            clickButton(driver, limitSharingBtn, "limit Sharing");
            switchToFrameUsingElement(driver, driver
                    .findElement(By.xpath("//iframe[contains(@src,'policyNo=" + financePageDTO.policyNum + "')]")));

            WebElement sharedGrpDescription_N = driver.findElement(By.xpath("//div[text()='UMB Shared']"));
            clickButton(driver, sharedGrpDescription_N, "Shared Group Description[UMB Shared]");

            WebElement coverageName_UMB_CGL_INS = driver
                    .findElement(By.xpath("//div[text()='" + financePageDTO.coveragenameumbcglins + "']"));
            clickButton(driver, coverageName_UMB_CGL_INS, "coverageName_UMB_CGL_INS");
            sleep(2000);
            selectDropdownByValue(driver, sharedOwner, financePageDTO.OwnerDDLValue_Y, "Owner");

            clickButton(driver, LimitSharingSaveBtn, "Limit Sharing windows Save");
            invisibilityOfLoader(driver);
            sleep(2000);
            WebElement sharedGrpDescription = driver.findElement(By.xpath("//div[text()='UMB Shared']"));
            clickButton(driver, sharedGrpDescription, "Shared Group Description[UMB Shared]");
            WebElement coverageName_UMB_PL_INS = driver.findElement(By.xpath("//div[text()='"
                    + financePageDTO.coverageNameumbplins + "']/parent::td/following-sibling::td/div[text()='Yes']"));
            clickButton(driver, coverageName_UMB_PL_INS, "Coverage name");

            sleep(2000);
            selectDropdownByValue(driver, sharedOwner, financePageDTO.OwnerDDLValue_N, "Owner");

            clickButton(driver, LimitSharingSaveBtn, "Limit Sharing windows Save");
            isAlertPresent(driver);
            invisibilityOfLoader(driver);
            sleep(2000);
            clickButton(driver, LimitSharingCloseBtn, "Limit Sharing windows Close");
            invisibilityOfLoader(driver);
            sleep(3000);
            switchToParentWindowfromframe(driver);
            pbp.saveOption(financePageDTO.policyNum);
            cancelwindowHandle();
        }
    }

    // verify Tail coverage window displayed or not.
    public void verifyTailCoverage() {
        try {
            if (tailCloseButton.isDisplayed() == true) {
                ExtentReporter.logger.log(LogStatus.INFO, "Tail coverage window is displayed.");
                clickButton(driver, tailCloseButton, "Tail Coverage page's Close");
                invisibilityOfLoader(driver);
                sleep(2000);
            } else {
                ExtentReporter.logger.log(LogStatus.INFO, "Error while performing action onf Tail coverage window.");
            }
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.INFO, "Tail coverage window is NOT displayed.");
        }
    }

    // this method will perform rate functionality.
    public FinancePage rateFunctionality() {
        RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
        String policyNo = rateapolicypage.policyNo();
        rateapolicypage.rateFunctionality(policyNo);
        return new FinancePage(driver);
    }

    public FinancePage rateFunctionalityWithoutPremiumVerification() {
        PolicyQuotePage policyquotepage = new PolicyQuotePage(driver);
        String policyNumber = policyquotepage.policyNo();
        policyquotepage.rateFunctionalityWithoutPremiumVerification(policyNumber);
        return new FinancePage(driver);
    }

    // this method will click on preview button.
    public PDFReader openPDF(String policyNo) {
        PolicyQuotePage policyquotepage = new PolicyQuotePage(driver);
        policyquotepage.clickPreviewTab(policyNo);
        return new PDFReader(driver);
    }

    // This method will save policy as official.
    public HomePage savePolicyAsofficial() {
        sleep(4000);
        RateApolicyPage rateAPolicyPage = new RateApolicyPage(driver);
        saveOption(driver, saveOptionBtn, saveAsDropDown, saveOptionOkBtn, Exit_Ok, financePageDTO.saveOption,
                rateAPolicyPage.policyNo());
        sleep(2000);
        clickButton(driver, exportExcelLink.get(0), "Export Excel");
        exlUtil.downloadExcel();
        copyFile(alltransactionlistafterpaymentcreditExcelName);
        return new HomePage(driver);
    }

    // This method will open new account page.
    public FinancePage newAccountOpen() {
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click Account>Maintain Account. Verify Find Account pop window opens");
        sleep(2000);
        Actions act = new Actions(driver);
        act.moveToElement(accountMenuTab).build().perform();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", maintainAccount);
        invisibilityOfLoader(driver);
        switchToFrameUsingElement(driver, iFrameElement);
        ExtentReporter.logger.log(LogStatus.INFO, "Click [New Account]. Verify Select Account Type window opens");
        clickButton(driver, newAccountBtn, "New Account");
        invisibilityOfLoader(driver);
        sleep(2000);
        switchToFrameUsingElement(driver, iFrameElement);
        for (int i = 0; i < accountType.size(); i++) {
            if (accountType.get(i).getAttribute("innerText").equals(financePageDTO.AccountType)) {
                ExtentReporter.logger.log(LogStatus.INFO, "Select Account type("
                        + accountType.get(i).getAttribute("innerHTML") + ").Verify Entity select search window opens");
                ExtentReporter.logger.log(LogStatus.INFO, "Click[Done]");
                clickButton(driver, accountType.get(i), accountType.get(i).getAttribute("innerText"));
                break;
            } else {
                ExtentReporter.logger.log(LogStatus.INFO,
                        "Account type(" + accountType.get(i).getAttribute("innerHTML") + " is not found).");
            }
        }
        clickButton(driver, doneBtnOnSelectAccTypePopUp, "Done Button on Select Account Type Pop up");
        return new FinancePage(driver);
    }

    // This method will search organization and select address for organization.
    public FinancePage entitySelectSearch() {
        invisibilityOfLoader(driver);
        String parentWindow = switchToWindow(driver);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Enter: " + financePageDTO.LastOrgName + ", Click Search. Verify Search results are displayed");
        enterTextIn(driver, lastOrOrgNameInputField, financePageDTO.LastOrgName, "Last/Organization Name");
        clickButton(driver, searchBtnEntitySearchPage, "Search");
        sleep(8000);
        invisibilityOfLoader(driver);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Select entity by checking the check box then Click [Select]. Verify Maintain Account displays with selected entity");
        clickButton(driver, searchedEntityChkBox, "Search Entity Check Box");
        sleep(1000);
        clickButton(driver, selectBtnSearchedEntity, "Select");
        sleep(5000);
        invisibilityOfLoader(driver);
        switchToParentWindowfromotherwindow(driver, parentWindow);
        // verifyValueFromField(driver, accountNoFieldValue, ,
        // accountNovalueAttributeName, "Account Number")
        if (!(accountNoFieldValue.getAttribute("value").length() > 0)) {
            Assert.assertTrue(false, "Account Information is NOT automatically populated on Maintain account page.");
        } else {
            ExtentReporter.logger.log(LogStatus.INFO,
                    "Account Information is automatically populated on Maintain account page.");
        }
        return new FinancePage(driver);
    }

    // This method will select issue Company for pre populated account details.
    public FinancePage selectIssueCompany() {
        ExtentReporter.logger.log(LogStatus.INFO, "Select Issue Company.Verify Address List window is displayed");
        selectDropdownByValue(driver, issueCompany, financePageDTO.issueCompanyValue, "Issue Company");

        return new FinancePage(driver);
    }

    // This method will select address for prepopulated account details.
    public FinancePage selectAddress() {

        ExtentReporter.logger.log(LogStatus.INFO,
                "Click Select Address Button. Verify Address List window is displayed");
        clickButton(driver, selectAdressBtn, "Select Adress");
        invisibilityOfLoader(driver);
        switchToFrameUsingElement(driver, iFrameElement);
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Done]. Verify Address is added to account");
        clickButton(driver, DoneBtnOnAddListPopUp, "Done");
        switchToParentWindowfromframe(driver);
        invisibilityOfLoader(driver);
        return new FinancePage(driver);
    }

    // This method will save account details.
    public FinancePage saveAccountDetails() {
        sleep(2000);
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Save].Verify Message displays for account information saved");
        clickButton(driver, SaveBtnOnMaintainActPage, "save");
        invisibilityOfLoader(driver);
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Ok].Verify Message is closed and account is saved");
        if (isAlertPresent(driver) == false) {
            ExtentReporter.logger.log(LogStatus.INFO, "Alert not present.");
        }
        invisibilityOfLoader(driver);
        sleep(2000);
        String[] description = descriptionAfterSave.getAttribute("innerHTML").toLowerCase().split(",", 2);
        System.out.println(description[0]);
        System.out.println(financePageDTO.LastOrgName);
        Assert.assertEquals(description[0], financePageDTO.LastOrgName.toLowerCase(),
                "Account details are not save Sucessfully");
        return new FinancePage(driver);
    }

    public void captureSaveScreenshotofMantainAccountpage() {
        ExtentReporter.logger.log(LogStatus.INFO, "Take screenshot of account, save the name as "
                + financePageDTO.screenShotName + " in folder SmokeTestFM");
        invisibilityOfLoader(driver);
        captureScreenshot(driver, financePageDTO.screenShotName);
        ExtentReporter.logger.log(LogStatus.INFO, "Upload screenshot to Rally");
    }

}
