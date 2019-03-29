package api;

import base.BaseTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ApiTests extends BaseTest {

    static Logger logger = LogManager.getLogger(ApiTests.class);

    @Test (groups= "people")
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

    @Test (groups= "people")
    public void bobaFettTest() {
        String character = "boba fett";
        String expectedPlanet = "kamino";
        JSONObject searchResult = common.searchForCharacter(character);
        if (searchResult.isEmpty()) {
            fail("Character Not Found");
        } else {
            String homeUrl = searchResult.get("homeworld").toString();
            Response getPlanet = request.getPlanets(common.getRegMatch(homeUrl));
            String planetName = getPlanet.path("name");
            assertEquals(planetName.toLowerCase(), expectedPlanet);
        }
    }

    @Test (groups= "film")
    public void jabbaTheHuttTest() {
        String character = "jabba desilijic tiure";
        String planet = "nal hutta";
        String film1 = "the phantom menace";
        JSONObject searchResult = common.searchForCharacter(character);

        Object waahh = searchResult.getJSONArray("films").get(0);
        logger.warn(waahh);

        if (searchResult.isEmpty()) {
            fail("Character Not Found");
        } else {
            String homeUrl = searchResult.get("homeworld").toString();
            Response getPlanet = request.getPlanets(common.getRegMatch(homeUrl));
            String planetName = getPlanet.path("name").toString().toLowerCase();
            logger.warn("planetName: " + planetName);
            assertEquals(planetName, planet);

            String firstFilm = searchResult.getJSONArray("films").get(0).toString();
            Response getFilms = request.getFilms(common.getRegMatch(firstFilm));
            String filmsTitle = getFilms.path("title").toString().toLowerCase();
            logger.warn("firstFilm: " + filmsTitle);
            assertEquals(filmsTitle, film1);
        }
    }
}