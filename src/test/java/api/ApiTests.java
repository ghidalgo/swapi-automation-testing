package api;

import base.BaseTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ApiTests extends BaseTest {

    static Logger logger = LogManager.getLogger(ApiTests.class);

    @Test
    public void lukeSkywalkerTest() {

        Response getLuke = request.getPeople("/1");
        assertEquals(getLuke.getStatusCode(), 200);
        String name = getLuke.path("name");
        assertEquals(name.toLowerCase(), "luke skywalker");

        String lukesHomeworld = getLuke.path("homeworld");
        logger.warn("homeworld: " + lukesHomeworld);
        String planet = common.getRegMatch(lukesHomeworld);

        Response getPlanet = request.getPlanets(planet);
        String pn = getPlanet.path("name");
        assertEquals(pn, "Tatooine");
    }

    @Test
    public void bobaFettTest() {


        HashMap searchResult = searchForCharacter("boba fett");

        logger.info("jsonObject: " + searchResult);
        if (searchResult.isEmpty()) {
            fail("Character Not Found");
        } else {

            logger.info("films: " + searchResult.get("films"));
            String getPlanet = request.getPlanets(common.getRegMatch(searchResult.get("homeworld").toString())).path("name");

            logger.info(getPlanet);
            assertEquals(getPlanet.toLowerCase(), "kamino");
        }
    }


    public HashMap searchForCharacter(String character) {

        boolean next = true;
        int p = 1;
        HashMap<String, String> characterResult = new HashMap<>();

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
                    logger.warn(name);

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