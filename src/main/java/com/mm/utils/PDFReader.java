package com.mm.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.mm.dto.pdfReaderDTO;
import com.mm.pages.PolicyBinderPage;
import com.mm.pages.PolicyQuotePage;
import com.relevantcodes.extentreports.LogStatus;

public class PDFReader extends CommonAction {
    CommonUtilities comUtil = new CommonUtilities();
    WebDriver driver;
    pdfReaderDTO pdfreaderdto;
    int i = 0;
    String Blank = "";
    String ExcelPath = System.getProperty("user.dir") + "\\src\\main\\resources\\Form_Data.xlsx";

    // AUTOIT script execution to save PDF.
    public PDFReader savePDF(String FileName) {
        sleep(6000);
        ExtentReporter.logger.log(LogStatus.INFO,
                "Click the Save button on the PDF to save the results & verify PDF is saved");
        String[] executionPath = { System.getProperty("user.dir") + "\\src\\main\\java\\autoItScripts\\savePdf.exe" };
        try {

            Runtime.getRuntime().exec(executionPath).waitFor(20, TimeUnit.SECONDS);
            if (copyPDFFile(FileName).equals("false")) {
                switchToParentWindowfromframe(driver);
                Process processkillpdf = Runtime.getRuntime().exec(
                        "TASKKILL /F /FI \"USERNAME eq " + System.getProperty("user.name") + "\" /IM savePdf.exe");
                PolicyQuotePage pqp = new PolicyQuotePage(driver);
                PolicyBinderPage policyBinderPage = new PolicyBinderPage(driver);
                pqp.clickPreviewTab(policyBinderPage.policyNo());
                Runtime.getRuntime().exec(executionPath).waitFor(20, TimeUnit.SECONDS);
                isAlertPresent(driver);
                copyPDFFile(FileName);
            } else {
                if (isAlertPresent(driver) == false) {
                    ExtentReporter.logger.log(LogStatus.INFO, "Alert[Optional] is NOT present.");
                }
                sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.FAIL, e.getMessage());
        }
        sleep(2000);
        switchToParentWindowfromframe(driver);
        return new PDFReader(driver);
    }

    public PDFReader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pdfreaderdto = new pdfReaderDTO(TestCaseDetails.testDataDictionary);
    }

    // Logic to verify PDF content.
    public PolicyBinderPage verifyPdfContent(String testcaseFormattedID) {
        sleep(15000);
        try {
            // getPageTitle(driver, "Policy Folder "+PolicyNo);
            boolean flag = false;
            PDFTextStripper pdfStripper = null;
            PDDocument pdDoc = null;
            COSDocument cosDoc = null;
            String parsedText = null;
            int noOfPDFPages = 0;

            try {
                File file = new File("C:\\savePDF\\verifyPDF.pdf");
                String pdfPath = ExtentReporter.reportFolderPath + "\\" + testcaseFormattedID + ".pdf";
                pdDoc = PDDocument.load(file);
                noOfPDFPages = pdDoc.getNumberOfPages();
                pdfStripper = new PDFTextStripper();
                parsedText = pdfStripper.getText(pdDoc);
            } catch (MalformedURLException e2) {
                System.err.println("URL string could not be parsed " + e2.getMessage());
            } catch (IOException e) {
                System.err.println("Unable to open PDF Parser. " + e.getMessage());
                try {
                    if (cosDoc != null)
                        cosDoc.close();
                    if (pdDoc != null)
                        pdDoc.close();
                } catch (Exception e1) {
                    e.printStackTrace();
                }
            }
            for (i = 0; i < pdfreaderdto.verifyPDFcontent.size(); i++) {
                try {
                    if (pdfreaderdto.verifyPDFcontent.get(i).equals(Blank)) {
                        break;
                    } else if (parsedText.toLowerCase().contains(pdfreaderdto.verifyPDFcontent.get(i).toLowerCase())) {
                        ExtentReporter.logger.log(LogStatus.PASS,
                                "PDF displays value- '" + pdfreaderdto.verifyPDFcontent.get(i) + "'.");
                    } else {
                        ExtentReporter.logger.log(LogStatus.FAIL,
                                pdfreaderdto.verifyPDFcontent.get(i) + " - value  is not present in PDF.");
                        // Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS_PERCENTAGE_FAILURE);
                    }
                } catch (Exception e) {
                    ExtentReporter.logger.log(LogStatus.FAIL,
                            pdfreaderdto.verifyPDFcontent.get(i) + " - value  is not present in PDF.");
                }
            }
            pdDoc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        switchToParentWindowfromframe(driver);
        return new PolicyBinderPage(driver);
    }

}
