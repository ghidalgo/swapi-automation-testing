package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.Urls;

import static io.restassured.RestAssured.given;

public class Requests {

    private Logger logger = LogManager.getLogger(Requests.class);
    private RequestSpecification requestSpec;
    private Urls url = new Urls();

    public void requestSpec() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(url.BASE)
                .setContentType("application/json")
                .build();
    }

    public Response getFilms(String filmNumber) {
        requestSpec();
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(url.FILM + filmNumber)
                .prettyPeek()
                .then()
                .extract()
                .response();
        return response;
    }


    public Response getPlanets(String planetNumber) {
        requestSpec();
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(url.PLANETS + planetNumber)
                .then()
                .extract()
                .response();
        return response;
    }

    public Response getPeople(String peopleNumber) {
        requestSpec();
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(url.PEOPLE + peopleNumber)
                .then()
                .extract()
                .response();
        return response;
    }

    public Response getSpecies(String speciesNumber) {
        requestSpec();
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(url.SPECIES + speciesNumber)
                .then()
                .extract()
                .response();
        return response;
    }

    public Response getStarships(String shipNumber) {
        requestSpec();
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(url.STARSHIPS + shipNumber)
                .then()
                .extract()
                .response();
        return response;
    }


    public Response getVehicles(String vehicleNumber) {
        requestSpec();
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(url.VEHICLES + vehicleNumber)
                .then()
                .extract()
                .response();
        return response;
    }
}


