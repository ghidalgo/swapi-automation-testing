package api;

import base.BaseTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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
        boolean found = false;
        String characterSearchResult = searchForCharacter("boba fett");
        logger.info("Search resulted in: " + characterSearchResult);
        // add next if search contains https://swapi.co/ then send to common.regex stuff and pull it out
    }

    public String searchForCharacter(String character) {

        boolean next = true;
        int p = 1;
        String characterUrl = "Character Not Found";

        do {
            String nextPageUrl;
            Response all = request.getPeople("/?page=" + p);
            nextPageUrl = all.path("next");
            logger.info("Next Page url: " + nextPageUrl);

            for (int i = 0; i <= 9; i++) {
                String name = all.path("results[" + i + "].name");
                if (name != null) {
                    logger.warn(name);
                }
                if (name.toLowerCase().equals(character)) {
                    characterUrl = all.path("results[" + i + "].url");
                    logger.info("*** Character Found ***");
                    return characterUrl;
                }
                if (nextPageUrl == null) {
                    next = false;
                }
            }
            p++;
            logger.info("Is there a next page?: " + next);
        } while (next);
        return characterUrl;
    }
}