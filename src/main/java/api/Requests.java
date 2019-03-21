package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.Time;
import utilities.Urls;

import static io.restassured.RestAssured.given;

public class Requests {

    private Logger logger = LogManager.getLogger(Requests.class);
    private RequestSpecification requestSpec;
    private Urls url = new Urls();
    private Time timeObj;

    public Requests() {
    }

    public void requestSpec() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(url.BASE)
                .setContentType("application/json")
                .build();
    }

    public Response getFilms(String filmNumber) {
        logger.info("*** /film/{number} ***\n");

        requestSpec();
        Response response = given()
                .body(requestSpec)
                .when()
                .get(url.FILM + filmNumber)
                .prettyPeek()
                .then()
                .extract()
                .response();
        return response;
    }


    public Response getPlanets(String planetNumber) {
        logger.info("*** GET Planet ***\n");

        requestSpec();
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(url.PLANETS + planetNumber)
                .prettyPeek()
                .then()
                .extract()
                .response();
        return response;
    }

    public Response getPeople(String peopleNumber) {
        logger.info("*** GET People ***\n");

        requestSpec();
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(url.PEOPLE + peopleNumber)
                //.prettyPeek()
                .then()
                .extract()
                .response();
        return response;
    }

    public Response getSpecies(String speciesNumber) {
        logger.info("*** GET Species ***\n");

        requestSpec();
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(url.SPECIES + speciesNumber)
                .prettyPeek()
                .then()
                .extract()
                .response();
        return response;
    }

    public Response getStarships(String shipNumber) {
        logger.info("*** GET Starships ***\n");

        requestSpec();
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(url.STARSHIPS + shipNumber)
                .prettyPeek()
                .then()
                .extract()
                .response();
        return response;
    }


    public Response getVehicles(String vehicleNumber) {
        logger.info("*** GET Vehicles ***\n");

        requestSpec();
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(url.VEHICLES + vehicleNumber)
                .prettyPeek()
                .then()
                .extract()
                .response();
        return response;
    }
}


