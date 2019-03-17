package base;

import api.Requests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.TestResultListener;
import utilities.Time;
import utilities.Urls;

@Listeners(value = TestResultListener.class)
public class BaseTest {

    private static Logger logger = LogManager.getLogger(BaseTest.class);
    protected static Time timeObj;
    protected static Requests apiRequestObj;
    protected static Urls urls;

    @BeforeSuite
    public void startUp() {
        logger.info("Setup");
        urls = new Urls();
        timeObj = new Time();
        apiRequestObj = new Requests();
    }

    @Test
    public void getSuiteName(ITestContext context) {
        String suiteName = context.getCurrentXmlTest().getSuite().getName();
        logger.info("suiteName: " + suiteName);
    }
}
