package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultListener implements ITestListener {
    private Logger logger = LogManager.getLogger(TestResultListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("*** Test Success: '" + result.getName() + "' test has passed ***");
        String methodName = result.getName().toString().trim();

    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("*** Test Failure: '" + result.getName() + "' test has failed ***");
        String methodName = result.getName().toString().trim();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}
