package com.utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utilities.ExtentManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ExtentReports {
    private static ExtentTest test;

    @BeforeSuite
    public void setUp() {
        // Initialize ExtentReports
        ExtentManager.getReporter();
    }

    @Test
    public void testExample() {
        // Create a test entry in the report
        test = ExtentManager.getReporter().createTest("Example Test");

        try {
            // Your test logic here
            // e.g., perform some actions and assertions

            test.log(Status.PASS, "Test Passed Successfully");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test Failed: " + e.getMessage());
        }
    }

    @AfterSuite
    public void tearDown() {
        // Flush the ExtentReports
        ExtentManager.flush();
    }
}
