package api;

import base.BaseTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
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
        JSONObject jsonObject = new JSONObject(searchResult);


        logger.info("jsonObject: " + jsonObject);
        if (jsonObject.isEmpty()) {
            fail("Character Not Found");
        } else {
            // add next if search contains https://swapi.co/ then send to common.regex stuff and pull it out


        }
    }


    public HashMap searchForCharacter(String character) {

        boolean next = true;
        int p = 1;
        HashMap<String, String> characterResult = new HashMap<>();
        logger.info("Search for a character named: " + character);

        do {
            String nextPageUrl;
            Response all = request.getPeople("/?page=" + p);
            nextPageUrl = all.path("next");

            for (int i = 0; i <= 9; i++) {
                String name = all.path("results[" + i + "].name");
                if (name == null) {
                    logger.warn("A character named " + character + " was NOT found...");
                    break;
                }
                if (name.toLowerCase().equals(character)) {
                    characterResult = all.path("results[" + i + "]");
                    logger.info("*** Found a character named " + name + " ***");
                    return characterResult;
                }
                if (nextPageUrl == null) {
                    next = false;
                }
            }
            p++;
        } while (next);
        return characterResult;
    }
}