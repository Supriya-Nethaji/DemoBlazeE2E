package utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.ProjectSpecificationMethods;

public class Listener extends ProjectSpecificationMethods implements ITestListener {
	
	ExtentTest test;
	
	ExtentReports ext = ExtentDemo.getReport();

	@Override
	public void onTestStart(ITestResult result) {
		 test = ext.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		test.log(Status.PASS, "Test Passed");
		String filepath = null;
		try {
			filepath = TakeScreenshot(Screenshotname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Failed");
		test.fail(result.getThrowable());
	}
	

	@Override
	public void onFinish(ITestContext context) {
		ext.flush();
	}

	

}
