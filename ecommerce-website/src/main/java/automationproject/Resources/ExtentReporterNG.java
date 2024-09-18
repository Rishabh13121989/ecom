package automationproject.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports reportGenerator()
	{
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Ecommerce Automation Results");
		report.config().setDocumentTitle("Test results");

		ExtentReports extentReport= new ExtentReports();
		extentReport.attachReporter(report);
		extentReport.setSystemInfo("Tester", "Sankar");
		return extentReport;
	}
}
