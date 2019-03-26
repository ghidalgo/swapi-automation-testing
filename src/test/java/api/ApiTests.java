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
        String character = "boba fett";
        String expectedPlanet = "kamino";
        HashMap searchResult = common.searchForCharacter(character);

        if (searchResult.isEmpty()) {
            fail("Character Not Found");
        } else {
            logger.info("films: " + searchResult.get("films"));
            String homeUrl = searchResult.get("homeworld").toString();
            Response getPlanet = request.getPlanets(common.getRegMatch(homeUrl));
            String planetName = getPlanet.path("name");
            assertEquals(planetName.toLowerCase(), expectedPlanet);
        }
    }
}