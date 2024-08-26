package com.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        //  WebDriver driver = ((BaseTestMethod) testClass).getDriver();

        // Capture and embed screenshot in the test report
        TakesScreenshot TakesScreenshot = null;
        TakesScreenshot takesScreenshot = (TakesScreenshot);
        String src = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        // Utilize a reporting tool to embed the screenshot - Example for ExtentReports
        //ExtentTest test = ExtentManager.getReporter().createTest(result.getName());
        // test.addScreenCaptureFromBase64String(src, "Failure screenshot");
        // test.fail(result.getThrowable());
    }

//    private static ExtentReports extentReports;
//    private ExtentTest test;
//
//    static {
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
//        extentReports = new ExtentReports();
//        extentReports.attachReporter(sparkReporter);
//    }


    public void onStart(ITestContext context) {

    }


    public void onFinish(ITestContext context) {

    }
}