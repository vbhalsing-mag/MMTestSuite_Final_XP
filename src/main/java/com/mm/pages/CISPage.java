package com.mm.pages;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.dto.CISPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mm.utils.ExtentReporter;
import com.mm.utils.TestCaseDetails;
import com.relevantcodes.extentreports.LogStatus;

public class CISPage extends CommonAction {

    WebDriver driver;
    CISPageDTO oCISPageDTO;
    String EntitySearchPageTitle = "Entity Search";
    String EntityListPageTitle = "Entity List";
    String AddOrganizationPageTitle = "Add Organization Quick Entry";

    // Locators for TC42253 Verify CIS Page Displays

    @FindBy(name = "entitySearch_lastOrOrgName")
    WebElement clientLastName;

    @FindBy(name = "entity_firstName")
    WebElement clientFirstName;

    @FindBy(id = "CI_ENSRC_SEARCH")
    WebElement searchButton;

    @FindBy(xpath = ("//span[@id='CCLIENT_NAME'] | //a[@id='entityListGrid_CCLIENT_NAME_0_HREF']"))
    List<WebElement> clientNameEntityList;

    @FindBy(xpath = "//div[@id='CCLIENT_ID'] | //div[@id='row0entityListGrid']//div[3]")
    List<WebElement> clientIDEntityList;

    @FindBy(id = "panelTitleForEntityList")
    WebElement entityListPanelTitle;

    @FindBy(id = "CI_SUMMARY_TG")
    WebElement summaryMainTab;

    @FindBy(xpath = "//span[@class='selectedTabWithDropDownImage']")
    WebElement selectedMainTab;

    @FindBy(xpath = "//ul[@id='tabSubMenuForCI_SUMMARY_TG']//li//a//span")
    List<WebElement> summaryDDMenu;

    @FindBy(id = "CI_SUMMARY_MI")
    WebElement summaryTab;

    @FindBy(xpath = "//td[@class='tabTitle']//b")
    WebElement displayedWindowTitle;

    @FindBy(id = "CI_DEMO_TG")
    WebElement demographicMainTab;

    @FindBy(xpath = "//ul[@id='tabSubMenuForCI_DEMO_TG']//li//a//span")
    List<WebElement> demographicDDMenu;

    @FindBy(xpath = "//span[@class='selectedTabWithDropDownImage']")
    WebElement selectedDemographicTab;

    @FindBy(id = "CI_BG_TG")
    WebElement backgroundMainTab;

    @FindBy(xpath = "//ul[@id='tabSubMenuForCI_BG_TG']//li//a//span")
    List<WebElement> backgroundDDMenu;

    @FindBy(xpath = "//span[@class='selectedTabWithDropDownImage']")
    WebElement selectedBackgroundTab;

    @FindBy(id = "CI_REL_TG")
    WebElement relationMainTab;

    @FindBy(xpath = "//ul[@id='tabSubMenuForCI_REL_TG']//li//a//span")
    List<WebElement> relationDDMenu;

    @FindBy(xpath = "//span[@class='selectedTabWithDropDownImage']")
    WebElement selectedRelationTab;

    @FindBy(id = "CI_VENDOR_TG")
    WebElement vendorMainTab;

    @FindBy(xpath = "//a[@id='CI_MNT_AGENT']//span")
    WebElement agencyMainTab;

    @FindBy(xpath = "//ul[@id='tabSubMenuForCI_VENDOR_TG']//li//a//span")
    List<WebElement> vendorDDMenu;

    @FindBy(xpath = "//span[@class='selectedTabWithDropDownImage']")
    WebElement selectedVendorTab;

    @FindBy(xpath = "//span[@class='txtOrange']")
    WebElement pageLoader;

    @FindBy(id = "pageTitleForpageHeader")
    WebElement entityListPageTitle;

    @FindBy(id = "pageTitleForpageHeader")
    WebElement pageTitleForPersonInfoPage;

    // Quick Add Organization locators

    @FindBy(xpath = "//a[@class='fNiv isParent']")
    WebElement quickAdd;

    @FindBy(xpath = "//li[@id='CI_QUICKADDORG_MI']//a/span")
    WebElement addOrg;

    @FindBy(xpath = "//li[@id='CI_QUICKADDPER_MI']//a/span")
    WebElement addPerson;

    @FindBy(name = "entity_organizationName")
    WebElement orgName;

    @FindBy(name = "entity_dateOfBirth")
    WebElement dateOfBirth;

    @FindBy(name = "entityClass_entityClassCode")
    WebElement classification;

    @FindBy(name = "entityClass_effectiveToDate")
    WebElement effectiveToDate;

    @FindBy(name = "address_addressTypeCode")
    WebElement addressType1;

    @FindBy(name = "address_addressLine1")
    WebElement addrLine1;

    @FindBy(name = "address_city")
    WebElement City;

    @FindBy(name = "address_stateCode")
    WebElement State;

    @FindBy(name = "address_stateCode")
    WebElement zipcode;

    @FindBy(name = "address_zipCode")
    WebElement addressZipCode;

    @FindBy(xpath = "//input[@type='button' and @value='OK']")
    WebElement OkButton;

    @FindBy(name = "phoneNumber_phoneNumberTypeCode")
    WebElement phoneNumType;

    @FindBy(name = "phoneNumber_areaCode")
    WebElement areaCode;

    @FindBy(name = "phoneNumber_phoneNumber_DISP_ONLY")
    WebElement phoneNum;

    @FindBy(id = "CI_QCKADDORG_SAVE")
    WebElement Save;

    @FindBy(xpath = "//li[@id='CI_HOME_SEARCH']//a//span")
    WebElement HomeSearchTab;

    @FindBy(name = "entitySearch_lastOrOrgName")
    WebElement lastNameORorgName;

    @FindBy(id = "CI_ENSRC_SEARCH")
    WebElement searchEntity;

    @FindBy(xpath = "//a[@id='entityListGrid_CCLIENT_NAME_0_HREF']| //span[@id='CCLIENT_NAME']")
    List<WebElement> orgNameList;

    @FindBy(xpath = "//div[@id='CDATE_OF_BIRTH']|//div[@id='row0entityListGrid']/div[8]/div")
    List<WebElement> DOBList;

    // Constructor to initialize driver, page elements and DTO PageObject for
    // CISPage
    public CISPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        oCISPageDTO = new CISPageDTO(TestCaseDetails.testDataDictionary);
    }

    // Search and Select the client name from the Entity List
    public CISPage searchAndSelectAClientName() {

        waitForElementToLoad(driver, 10, clientLastName);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Enter " + oCISPageDTO.clientLastName + " in the Last Name field. Enter " + oCISPageDTO.clientFirstName
                        + " in the First Name field"

                        + "Click search. Verify Search results returned");
        enterTextIn(driver, clientLastName, oCISPageDTO.clientLastName, "Last/Org Name");
        enterTextIn(driver, clientFirstName, oCISPageDTO.clientFirstName, "First Name");
        sleep(2000);
        click(driver, searchButton, "Search");
        invisibilityOfLoader(driver);
        sleep(3000);
        getPageTitle(driver, EntityListPageTitle);
        // Get list of client IDs based on environment 'BTS OR QA' and store in
        // List WebElement
        List<WebElement> clientIDEntityList = driver.findElements(
                By.xpath("//div[@id='CCLIENT_ID'] | //div[@title='" + oCISPageDTO.clientIDValue + "']//div"));
        try {
            visibilityOfElement(driver, clientNameEntityList.get(0), "Client name");
            visibilityOfElement(driver, clientIDEntityList.get(0), "Client ID");
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
        // Searching the client using client name and matching id
        boolean flag = false;
        try {
            for (int i = 0; i < clientNameEntityList.size(); i++) {

                // compare the client name with the client id and select client
                // name whose id matches
                if (clientNameEntityList.get(i).getAttribute("innerHTML").trim().equals(oCISPageDTO.clientNameValue)
                        && clientIDEntityList.get(i).getAttribute("innerHTML").trim()
                                .equals(oCISPageDTO.clientIDValue)) {
                    ExtentReporter.logger.log(LogStatus.INFO,
                            "Click on " + oCISPageDTO.clientNameValue + ". Verify CIS Demographic Client screen opens");
                    click(driver, clientNameEntityList.get(i), oCISPageDTO.clientNameValue);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                throw new Exception("Client Name is not listed in search result");
            }
        } catch (Exception exception) {
            ExtentReporter.logger.log(LogStatus.FAIL,
                    "Client name with matching ID is not available in the Entity List");
        }
        return new CISPage(driver);
    }

    // Verify that all main tabs having menu options are opened and page title
    // is verified
    public CISPage verifyPagesHavingMenuOnPersonPageAreDisplayed() {
        sleep(3000);
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        // Get list of main tabs from Excel sheet column
        for (int i = 0; i < oCISPageDTO.mainTabList.size(); i++) {
            // Get the list of all sub tabs under the selected main tab from
            // application
            List<WebElement> tabMenuOption = driver.findElements(
                    By.xpath("//ul[@id='tabSubMenuForCI_" + oCISPageDTO.mainTabList.get(i).trim() + "_TG']//span"));

            // Get the count of all sub tabs under selected main tab
            for (int j = 0; j < tabMenuOption.size(); j++) {
                // Get list of all sub tabs from excel sheet
                for (int k = 0; k < oCISPageDTO.allMenuOptions.size(); k++) {

                    // verify if the main tab displayed is selected
                    if (verifyElementDisplays(selectedMainTab) == true) {
                        // Reinitialize the tab menu options elements to avoid
                        // stale element
                        List<WebElement> tabMenuOption1 = driver.findElements(By.xpath(
                                "//ul[@id='tabSubMenuForCI_" + oCISPageDTO.mainTabList.get(i).trim() + "_TG']//span"));

                        // compare if tab menu text is equal to menu option from
                        // excel sheet
                        if (tabMenuOption1.get(j).getAttribute("innerHTML")
                                .equalsIgnoreCase(oCISPageDTO.allMenuOptions.get(k))) {
                            waitForElementToLoad(driver, 10, selectedMainTab);
                            ExtentReporter.logger.log(LogStatus.INFO,
                                    "Click on " + oCISPageDTO.mainTabList.get(i) + " drop down and Select "
                                            + oCISPageDTO.allMenuOptions.get(k) + ". Verify "
                                            + oCISPageDTO.allMenuOptions.get(k) + " window opens");
                            JavascriptExecutor executor1 = (JavascriptExecutor) driver;
                            // click on tab main tab and then click on tab menu
                            executor1.executeScript("arguments[0].click();", selectedMainTab);
                            executor1.executeScript("arguments[0].click();", tabMenuOption1.get(j));
                            invisibilityOfLoader(driver);
                            sleep(8000);
                            // verifying the page title of an open window
                            verifyPageTitleForTheOpenWindow(displayedWindowTitle,
                                    oCISPageDTO.windowTitlesForSubMenuTabs.get(k) + " " + oCISPageDTO.clientNameValue,
                                    oCISPageDTO.allMenuOptions.get(k));
                        }
                    } else {
                        if (tabMenuOption.get(j).getAttribute("innerHTML").equals(oCISPageDTO.allMenuOptions.get(k))) {
                            // click on tab menu option (when main tab is not in
                            // selected mode)
                            ExtentReporter.logger.log(LogStatus.INFO,
                                    "Click on " + oCISPageDTO.mainTabList.get(i) + " drop down and Select "
                                            + oCISPageDTO.allMenuOptions.get(k) + ". Verify "
                                            + oCISPageDTO.allMenuOptions.get(k) + " window opens");
                            executor.executeScript("arguments[0].click();", tabMenuOption.get(j));
                            invisibilityOfLoader(driver);
                            sleep(2000);
                            // verifying the page title of an open window
                            verifyPageTitleForTheOpenWindow(displayedWindowTitle,
                                    oCISPageDTO.windowTitlesForSubMenuTabs.get(k) + " " + oCISPageDTO.clientNameValue,
                                    oCISPageDTO.allMenuOptions.get(k));
                        }
                    }
                }
            }
        }
        return new CISPage(driver);
    }

    // Verify that main tab not having menu options is opened and page title is
    // verified
    public CISPage verifyPagesWithoutSubMenu() {

        sleep(2000);
        // Get the list and count of all menus from excel sheet list
        for (int i = 0; i < oCISPageDTO.allMenuOptions.size(); i++) {

            // compare tab name with names from excel column
            sleep(1000);
            if (agencyMainTab.getAttribute("innerHTML").equalsIgnoreCase(oCISPageDTO.allMenuOptions.get(i))) {
                try {
                    // click on tab name when it matches with name from excel
                    // sheet
                    ExtentReporter.logger.log(LogStatus.INFO, "Click on " + oCISPageDTO.allMenuOptions.get(i)
                            + ". Verify " + oCISPageDTO.allMenuOptions.get(i) + " window opens");

                    JavascriptExecutor executor1 = (JavascriptExecutor) driver;
                    executor1.executeScript("arguments[0].click();", agencyMainTab);
                    sleep(2000);
                    // verify the page title when the tab window opens
                    verifyPageTitleForTheOpenWindow(displayedWindowTitle,
                            oCISPageDTO.windowTitlesForSubMenuTabs.get(i) + " " + oCISPageDTO.clientNameValue,
                            oCISPageDTO.allMenuOptions.get(i));
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    assertTrue(false, e.getMessage());
                }
            }
        }
        return new CISPage(driver);
    }

    // Verify the page title for the open window, this method is called after
    // each tab that is selected
    public void verifyPageTitleForTheOpenWindow(WebElement tabTitleElement, String tabTitle, String tabName) {
        sleep(5000);
        waitForElementToLoad(driver, 10, tabTitleElement);
        // verify if actual tab title matches with tab title from excel sheet
        // and log message
        if (tabTitleElement.getAttribute("innerHTML").trim().equals(tabTitle)) {
            ExtentReporter.logger.log(LogStatus.PASS,
                    tabName + " page title is as expected i.e. " + tabTitleElement.getAttribute("innerHTML").trim());
        } else {

            // Files tab title changes if the claim is available, so below if
            // handles changed title
            String filesTabPartialTitle = "Claims for ";
            if (tabTitleElement.getAttribute("innerHTML").contains(filesTabPartialTitle)) {

                if (tabTitleElement.getAttribute("innerHTML").trim()
                        .equals(filesTabPartialTitle + oCISPageDTO.clientNameValue))

                    ExtentReporter.logger.log(LogStatus.PASS, tabName + " page title is as expected i.e. "
                            + tabTitleElement.getAttribute("innerHTML").trim());

            } else {
                ExtentReporter.logger.log(LogStatus.FAIL, tabName + " page title did not match, expected was "
                        + tabTitle + " but found " + tabTitleElement.getAttribute("innerHTML").trim());
            }
        }
    }

    // verify that Menu tab is displayed and return boolean value
    public Boolean verifyElementDisplays(WebElement pageElement) {
        try {
            if (pageElement.isEnabled())
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    // Note- Quick Add organization new code

    // Move to Quick Add button and select Add Organization menu
    public CISPage navigateToAddOrgPage() {
        sleep(2000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click Quick Add Dropdown and  then select Add Organization. Add Organization Quick Entry window displays");
        Actions action = new Actions(driver);
        action.moveToElement(quickAdd).build().perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", addOrg);
        sleep(2000);
        return new CISPage(driver);
    }

    // Move to Quick Add button and select Add Person menu
    public CISPage navigateToAddPersonPage() {
        sleep(2000);
        ExtentReporter.logger.log(LogStatus.INFO, "Add Organization Quick Entry window displays");
        Actions action = new Actions(driver);
        action.moveToElement(quickAdd).build().perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", addPerson);
        sleep(2000);
        return new CISPage(driver);
    }

    // Add info like Org. Name, DOI, Classfication, EffectTODate in
    public String addOrganizationInformation() {

        getPageTitle(driver, AddOrganizationPageTitle);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Enter/select below information\n Organization: Enter Org Name (Automated Hospital (date))\n"
                        + "DOI: Enter Today's Date\n Classification: Select Med Facility\n Class Eff. From Date: Today's Date\n Class Eff. To Date: 01/01/3000\n Organization information is added");
        CommonUtilities commUtil = new CommonUtilities();
        String OrganizationName = oCISPageDTO.OrgName + commUtil.getSystemDateMMddyy_hhmmss();
        enterTextIn(driver, orgName, OrganizationName, "Org Name");
        enterTextIn(driver, dateOfBirth, oCISPageDTO.dateOfBirth, "DOI");
        selectDropdownByVisibleText(driver, classification, oCISPageDTO.Classification, "Classfication");
        sleep(1000);
        enterTextIn(driver, effectiveToDate, oCISPageDTO.classEffctToDate, "Effective To Date");

        return OrganizationName;
    }

    // Add org address in Address section
    public CISPage addOrgAddress() {
        ExtentReporter.logger.log(LogStatus.INFO, "Enter below information under Address criteria Select\n"
                + "Country: USA Address\n Address Type: Policy Address\n Address) Line 1: Generate random number and street name City: Atlanta (zip code pop-up screen displays)"
                + "State: GA\n County: Select any county from DDL\n Zip: Select any Zip Code from pop-up screen\n Addr. Eff From Date: Select Today's date (defaults)\n Address is entered");

        selectDropdownByValue(driver, addressType1, oCISPageDTO.addrType, "Add_Type1");
        enterTextIn(driver, addrLine1, oCISPageDTO.addressLine1, "Line1");
        enterTextIn(driver, City, oCISPageDTO.City, "city");
        selectDropdownByValue(driver, State, oCISPageDTO.State, "state code");

        return new CISPage(driver);
    }

    // Select zip code from pop up window and validate on parent window
    public CISPage selectZipCode() {
        sleep(4000);
        // switch to new window using get window handle
        String parentwindow = switchToWindow(driver);
        WebElement zipCode = driver.findElement(By.xpath("//input[@value='" + oCISPageDTO.zipCode + "']"));
        // select zip code from pop up window
        clickButton(driver, zipCode, "ZipCode");
        clickButton(driver, OkButton, "OK button");
        sleep(1000);
        // navigate back to parent window
        switchToParentWindowfromotherwindow(driver, parentwindow);
        // verify value in zip code field is as expected
        verifyValueFromField(driver, addressZipCode, oCISPageDTO.zipCode, "value", "Zip Code");

        return new CISPage(driver);
    }

    // Add phone number in phone section
    public CISPage addPhoneNumber() {
        ExtentReporter.logger.log(LogStatus.INFO, "Enter below information under\n"
                + " Phone Address1-\n Phone No.Type: Enter Office Telephone\n Area Code: 770\n Phone Number: ###-#### format\n Phone number is added");

        enterTextIn(driver, phoneNumType, oCISPageDTO.phoneNumType, "Phone Num Type");
        enterTextIn(driver, areaCode, oCISPageDTO.areaCode, "Area Code");
        enterTextIn(driver, phoneNum, oCISPageDTO.phoneNumber, "Phone number");
        ExtentReporter.logger.log(LogStatus.INFO, "Click [Save and Close]. Organization is added");
        click(driver, Save, "Save button");
        return new CISPage(driver);
    }

    // Validate that added organization is listed in searched list
    public void searchRecentlyAddedOrganisation(String OrganizationName) {
        sleep(2000);
        // Click Search from home tab header
        clickButton(driver, HomeSearchTab, "Search");
        invisibilityOfLoader(driver);
        getPageTitle(driver, EntitySearchPageTitle);
        // Add recently added org name and search
        enterTextIn(driver, lastNameORorgName, OrganizationName, "Last/Org name");
        clickButton(driver, searchEntity, "Search");
        sleep(20000);

        boolean flag = false;
        try {
            // Get organization name list from search result
            for (int i = 0; i < orgNameList.size(); i++) {
                // compare if organization name and DOB are as expected from
                // excel sheet
                if (orgNameList.get(i).getAttribute("innerHTML").trim().equals(OrganizationName)
                        && DOBList.get(i).getAttribute("innerHTML").trim().equals(oCISPageDTO.dateOfBirth)) {
                    ExtentReporter.logger.log(LogStatus.PASS, "New Organization is available in search List");
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                throw new Exception("Org name not listed in search results");
            }
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.FAIL,
                    "Recently added New organization name is not listed in search results");
        }
    }
}
