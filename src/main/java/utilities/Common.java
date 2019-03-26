package utilities;

import api.Requests;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public HashMap searchForCharacter(String character) {
        boolean next = true;
        int p = 1;
        HashMap<String, String> characterResult = new HashMap<>();
        Requests request = new Requests();

        if (character.equals("")) {
            logger.info("No character provided. Exiting...");
        } else {
            logger.info("Search for a character named: " + character);

            do {
                Response all = request.getPeople("/?page=" + p);
                String nextPageUrl = all.path("next");
                String name = "";

                for (int i = 0; i <= 9; i++) {
                    name = all.path("results[" + i + "].name");

                    if (name == null) {
                        logger.warn("A character named " + character + " was NOT found...");
                        break;
                    } else if (name.toLowerCase().equals(character) && name != null) {
                        characterResult = all.path("results[" + i + "]");
                        logger.info("*** Found a character named " + name + " ***");
                        break;
                    }
                }
                if (nextPageUrl == null || name.toLowerCase().equals(character)) {
                    next = false;
                }
                p++;
            }
            while (next);
        }
        return characterResult;
    }
}
