package BaseClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mm.utils.ExtentReporter;
import com.relevantcodes.extentreports.ExtentReports;

public class BaseTest {

    public static String reportFolderPath;
    public static String screenshotfolderpath;
    public static String filePath;

    public void createReportFolder(String suiteName) {
        // Method to generate Report Folder and File to given FilePath using
        // Framework.properties

        String startDateTime = new SimpleDateFormat("ddMMYYHHmmss").format(new Date());
        reportFolderPath = "C:\\SmokeTestFM\\".concat(suiteName).concat("_BTS_").concat(startDateTime);
        // configProperties.getProperty("ReportFolder")
        // Create report folder for every run session
        File reportFolder = new File(reportFolderPath.concat("\\Screenshots"));
        screenshotfolderpath = reportFolderPath.concat("\\Screenshots\\");
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();

        }
        filePath = reportFolderPath + "\\SmokeTestReport.html";
        // new ExtentReports(filePath);
        ExtentReporter.report = new ExtentReports(filePath);
        // extent.loadConfig(new File("extent-config.xml"));
    }

}
