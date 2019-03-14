package api;

import base.BaseTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;

public class ApiTests extends BaseTest {

    private static Logger logger = LogManager.getLogger(ApiTests.class);

    @Test
    public void lukeSkywalkerTest() {
        Response getPeople = apiRequestObj.getPeople("/1");
        String name = getPeople.path("name");
        String homeworld = getPeople.path("homeworld");
        logger.warn("homeworld: " + homeworld);
        Pattern p = Pattern.compile("\\/\\d+\\/$");
        Matcher m = p.matcher(homeworld);
        String hw = "";
        if (m.find()) {
            hw = m.group(0);
            logger.warn(hw);
        }

        assertEquals(getPeople.getStatusCode(), 200);
        assertEquals(name.toLowerCase(), "luke skywalker");

        Response getPlanet = apiRequestObj.getPlanets(hw);
    }
}

