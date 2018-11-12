package com.mm.utils;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class FailedTCRerun {

    public void reRunFailedTC() {
        TestNG runner = new TestNG();
        List<String> list = new ArrayList<String>();
        list.add("C:\\Users\\saccount\\git\\MagMutual_Sprint7_Latest\\MagMutual_POM\\test-output\\testng-failed.xml");
        runner.setTestSuites(list);
        runner.run();
    }
}
