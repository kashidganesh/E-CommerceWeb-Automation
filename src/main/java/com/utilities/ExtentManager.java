package com.utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            try {
                // Create a new ExtentSparkReporter instance with the report file path
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
                sparkReporter.config().setReportName("E-Commerce Web Automation Test Report");

                // Initialize ExtentReports and attach the reporter
                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);
                extent.setSystemInfo("Environment", "QA");
                extent.setSystemInfo("User Name", "Your Name");

                // Optionally set more system info or report configurations here
            } catch (Exception e) {
                e.printStackTrace(); // Handle exceptions as necessary
            }
        }
        return extent;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}


