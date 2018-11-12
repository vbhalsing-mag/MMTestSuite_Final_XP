package com.mm.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListner implements IRetryAnalyzer {

    private int retryCount = 1;
    private final int MAX_RETRY_COUNT = 1;

    public boolean retry(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && retryCount <= MAX_RETRY_COUNT) {
            try {
                Thread.sleep(3000);
                System.out.println("Method Name : " + result.getName() + " Retry Count : " + retryCount + "");
                retryCount++;
                return true;
            } catch (Exception e) {

            }
        }

        return false;
    }

}
