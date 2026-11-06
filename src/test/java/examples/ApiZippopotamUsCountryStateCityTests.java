package examples;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiZippopotamUsCountryStateCityTests {

    String baseUrl = "http://zippopotam.us/";

    /*
    Structure: api.zippopotam.us/country/state/city
    Example: api.zippopotam.us/us/ma/belmont
     */
    @Test
    public void validateBodyResponseForUSStateMACityBelmont() {
        given().
        when().
                get(baseUrl + "us/ma/belmont").
        then().
                assertThat().
                body("country", equalTo("United States")).
                body("'country abbreviation'", equalTo("US")).
                body("state", equalTo("Massachusetts")).
                body("'state abbreviation'", equalTo("MA")).
                body("'place name'", equalTo("Belmont")).
                body("places[0].'place name'", equalTo("Belmont")).
                body("places[0].longitude", equalTo("-71.4594")).
                body("places[0].latitude", equalTo("42.4464")).
                body("places[0].'post code'", equalTo("02178")).
                body("places[1].'place name'", equalTo("Belmont")).
                body("places[1].longitude", equalTo("-71.2044")).
                body("places[1].latitude", equalTo("42.4128")).
                body("places[1].'post code'", equalTo("02478"));

    }

    @Test
    public void validateStatusCodeForUSStateMACityBelmont() {
        given().
        when().
                get(baseUrl + "us/ma/belmont").
        then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validateContentTypeForUSStateMACityBelmont() {
        given().
        when().
                get(baseUrl + "us/ma/belmont").
        then().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    public void getLogRequestAndResponseForUSStateMACityBelmont() {
        given().
                log().all().
        when().
                get(baseUrl + "us/ma/belmont").
        then().
                log().body();
    }

    @Test
    public void validatePlaceNameForUSStateMACityBelmont() {
        given().
        when().
                get(baseUrl + "us/ma/belmont").
        then().
                assertThat().
                body("'place name'", equalTo("Belmont")).
                body("places[0].'place name'", equalTo("Belmont")).
                body("places[1].'place name'", equalTo("Belmont"));
    }

    @Test
    public void validateStateForUSStateMACityBelmont() {
        given().
        when().
                get(baseUrl + "us/ma/belmont").
        then().
                assertThat().
                body("state", equalTo("Massachusetts"));
    }

    @Test
    public void validateListOfPlaceNamesUSStateMACityBelmont() {
        given().
        when().
                get(baseUrl + "us/ma/belmont").
        then().
                assertThat().
                body("places.'place name'", hasItem("Belmont")).
                body("places.'place name'", not(hasItem("Beverly Hills")));
    }

    @Test
    public void validateNumberOfPlaceNamesUSStateMACityBelmont() {
        given().
        when().
                get(baseUrl + "us/ma/belmont").
        then().
                assertThat().
                body("places.'place name'", hasSize(2));
    }

    @Test(dataProvider = "countryCodeStateCodeCityAndPlaceName")
    public void validatePlaceName(String countryCode, String stateCode, String city, String expectedPlaceName) {
        given().
                pathParam("countryCode", countryCode).
                pathParam("stateCode", stateCode).
                pathParam("city", city).
        when().
                get(baseUrl + "{countryCode}/{stateCode}/{city}").
        then().
                assertThat().
                body("'place name'", equalTo(expectedPlaceName)).
                body("places[0].'place name'", equalTo(expectedPlaceName)).
                body("places[1].'place name'", equalTo(expectedPlaceName));
    }

    @DataProvider
    public static Object[][] countryCodeStateCodeCityAndPlaceName(){
        return new Object[][]{
                {"us", "ma", "belmont", "Belmont"},
                {"us", "ma", "boston", "Boston"},
                {"us", "ny", "albany", "Albany"}
        };
    }

}
