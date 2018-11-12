package com.mm.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReporter extends CommonAction {

    public static ExtentTest logger;
    public static String reportFolderPath;
    public static String screenshotfolderpath = "";
    public static String filePath;
    public static ExtentReports report;
    public static String excelPath;

    public String createReportFolder(String suiteName) {
        // Method to generate Report Folder and File to given FilePath using
        // Framework.properties

        String startDateTime = new SimpleDateFormat("ddMMYYHHmmss").format(new Date());
        reportFolderPath = "C:\\SmokeTestFM\\".concat(suiteName + "_").concat(startDateTime);
        // configProperties.getProperty("ReportFolder")
        // Create report folder for every run session
        File reportFolder = new File(reportFolderPath.concat("\\Screenshots"));
        screenshotfolderpath = reportFolderPath.concat("\\Screenshots\\");
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();

        }
        filePath = reportFolderPath + "\\SmokeTestReport.html";
        // new ExtentReports(filePath);
        report = new ExtentReports(filePath);
        // extent.loadConfig(new File("extent-config.xml"));
        return reportFolderPath;
    }

    public void verifyFolderExistOrNot(String fileName) {
        File reportFolder = new File("C:\\".concat(fileName));
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        }
    }
}
