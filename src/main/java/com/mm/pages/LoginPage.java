
package com.mm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.CommonAction;
import com.mm.utils.ExtentReporter;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends CommonAction {

    // Global Assignment/initialization of variables.
    WebDriver driver;

    // Element repository for Login page.
    @FindBy(name = "j_username")
    WebElement userName;

    @FindBy(name = "j_password")
    WebElement password;

    @FindBy(name = "btnSearch")
    WebElement loginBtn;

    // Constructor to initialize elements on Login page.
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    // Code to login to eOasis application.
    public HomePage loginToeOasis(String UserName, String PassWord) {
        // Pass the URL to browser
        driver.get(CommonAction.url);
        // "http://oasiscloud2017t:8081/oas17bts/CS/login.jsp");
        // driver.get("http://172.18.1.107:9080/magsoasisqa/CS/login.jsp");
        ExtentReporter.logger.log(LogStatus.INFO, "Accessing the URL - " + CommonAction.url);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        ExtentReporter.logger.log(LogStatus.PASS,
                "Log into QA Environment Enter Username: Enter Password: And verifyt user sucessfully loggedIn.");
        // Entering User Name.
        try {
            enterTextIn(driver, userName, UserName, "User Name");
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.FAIL, " Error while entering data into username field.");
        }

        // Entering Password.
        try {
            sleep(1000);
            enterTextIn(driver, password, PassWord, "Password");
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.FAIL, " Error while entering data into password field.");
        }

        // Clicking on login button.
        try {
            clickButton(driver, loginBtn, "Submit");
        } catch (Exception e) {
            ExtentReporter.logger.log(LogStatus.FAIL, "Issue with login button.");
        }
        return new HomePage(driver);
    }
}
