package com.mm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.dto.PolicySubmissionPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.ExtentReporter;
import com.mm.utils.TestCaseDetails;
import com.relevantcodes.extentreports.LogStatus;

public class PolicySubmissionPage extends CommonAction {

    // Global Assignment/initialization of variables.
    WebDriver driver;
    String indicationPhaseValue = "INDICATION";
    String valueOfPolicyActionCopy = "javascript:copyQuote();";
    PolicyIndicationPage policyIndicationPage;
    PolicySubmissionPageDTO policysubmissionpageDTO;

    // Element repository for Policy Submission page.
    @FindBy(id = "PM_COMMON_TABS_SAVEWIP")
    WebElement saveWIP;

    @FindBy(name = "policyPhaseCode")
    WebElement policyPhase;

    @FindBy(xpath = "//div[@id='globalDropdownActionItems']//select[@class='globalActionItemList']")
    WebElement policyAction;

    @FindBy(name = "policyPhaseCode")
    WebElement Phase;

    @FindBy(name = "organizationTypeCode")
    WebElement Org_Type;

    @FindBy(name = "discoveryPeriodRating")
    WebElement Hosp_Disc_Period_Rating;

    @FindBy(name = "termDesc")
    WebElement Quote_Description;

    // Constructor to initialize driver, page elements and DTO PageObject for
    // PolicySubmissionPage
    public PolicySubmissionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        policysubmissionpageDTO = new PolicySubmissionPageDTO(TestCaseDetails.testDataDictionary);
    }

    // Select Copy from Action value from Action drop down.
    public PolicySubmissionPage copyFromPolicyActionDropDown(String policyNum) {
        sleep(8000);
        ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Copy. Verify Phase is editable.");
        selectDropdownByValue(driver, policyAction, policysubmissionpageDTO.valueOfPolicyActionCopy, "Policy Action");
        sleep(3000);
        // Below code is for QA env.
        PolicyBinderPage pbp = new PolicyBinderPage(driver);
        pbp.verifyCpatureTxnDetailsPageDisplayedOrNot(pbp.policyNo());
        return new PolicySubmissionPage(driver);
    }

    // Change policy phase to indication.
    public PolicySubmissionPage changePhaseToIndicationAndAddQuoteDescription() {
        sleep(9000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Change Policy Phase to Indication. Verify Policy is changed from Submission to Indication");
        selectDropdownByValue(driver, policyPhase, policysubmissionpageDTO.indicationPhaseValue, "Phase");
        ExtentReporter.logger.log(LogStatus.INFO,
                "Enter " + policysubmissionpageDTO.quoteDescription + " in the Quote Description. Verify "
                        + policysubmissionpageDTO.quoteDescription + " is entered in Quote Description.");
        enterTextIn(driver, Quote_Description, policysubmissionpageDTO.quoteDescription, "Quote Description");
        sleep(1000);
        return new PolicySubmissionPage(driver);
    }

    // Save policy / Quote as Work in progress.
    public PolicyQuotePage saveWip() {
        clickButton(driver, saveWIP, "Save WIP");
        sleep(2000);
        return new PolicyQuotePage(driver);
    }

    // Update policy details for a policy and change policy phase from
    // Submission to Indication.
    public PolicyIndicationPage updatePolicyDetails() {
        sleep(10000);
        invisibilityOfLoader(driver);
        // TODO-getPageTitle(driver, expectedPageTitle)
        // Change policy type to Indication and add organization type as
        // Hospital from DDL
        ExtentReporter.logger.log(LogStatus.INFO,
                "In Policy Detail tab,Select/Enter the below information:"
                        + " Phase: Indication, Organization Type: Hospital,Hospital Discovery Period Rating %: 2 "
                        + "Quote Description: Automated Test. Click Save WIP Button. Verify Indication saved as WIP");
        selectDropdownByValue(driver, Phase, policysubmissionpageDTO.policyPhase, "Phase");
        selectDropdownByValue(driver, Org_Type, policysubmissionpageDTO.organisationType, "Organisation Type");
        sleep(2000);
        // Add Discovery period rating, Quote Description and save as WIP
        enterTextIn(driver, Hosp_Disc_Period_Rating, policysubmissionpageDTO.discoveryPeriodRating,
                "Discovery_Period Rating");
        enterTextIn(driver, Quote_Description, policysubmissionpageDTO.quoteDescription, "Quote Description");
        click(driver, saveWIP, "Save WIP button");
        return new PolicyIndicationPage(driver);
    }
}
