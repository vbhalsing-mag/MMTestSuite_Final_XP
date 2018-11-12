package com.mm.browsers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IEWebDriver implements DriverFactory {
    WebDriver driver;

    @SuppressWarnings("deprecation")
    public WebDriver getDriver() {
        String iepath = System.getProperty("user.dir") + "\\src\\main\\resources\\IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", "C:\\Drivers\\IEDriverServer.exe");
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability("EnableNativeEvents", false);
        caps.setCapability("ignoreZoomSetting", true);
        driver = new InternetExplorerDriver(caps);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
        return driver;
    }
}
