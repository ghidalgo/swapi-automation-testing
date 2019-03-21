package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Common {
    private static Logger logger = LogManager.getLogger(Common.class);

    public String getRegMatch(String type) {

        Pattern p = Pattern.compile("\\/\\d+\\/$");
        Matcher m = p.matcher(type);
        String hw = "";
        if (m.find()) {
            hw = m.group(0);
            logger.warn(hw);
        }
        return hw;
    }
}
