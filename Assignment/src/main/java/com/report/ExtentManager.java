package com.report;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter()
    {
        if(extent == null)
        {
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir+"\\ExtentReports\\TestReport.html", true);
            extent.loadConfig(new File(workingDir+"\\extent-config.xml"));
            extent.addSystemInfo("Company Name","RFPIO");
            extent.addSystemInfo("Application URL","https://www.flipkart.com/");
            extent.addSystemInfo("Environment", "Production");
            extent.addSystemInfo("Author","Balaji G");	
        }
        return extent;
    }
}
