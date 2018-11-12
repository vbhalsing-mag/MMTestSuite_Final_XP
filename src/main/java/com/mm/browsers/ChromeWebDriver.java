package com.mm.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriver implements DriverFactory{
	
	WebDriver driver;

	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		
		String chromePath = System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromePath);
		driver = new ChromeDriver();
		
		return driver;
	}

}
