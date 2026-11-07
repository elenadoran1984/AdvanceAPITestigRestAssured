package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiZippopotamUsCountryStateCityTests {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestSpecification(){

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://zippopotam.us").
                build();
    }

    @BeforeClass
    public static void createResponseSpecification(){

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    /*
    Structure: api.zippopotam.us/country/state/city
     */
    @Test
    public void validateBodyResponseForUSStateMACityBelmont() {
        given().
                spec(requestSpec).
        when().
                get("us/ma/belmont").
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
                spec(requestSpec).
        when().
                get("us/ma/belmont").
        then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validateContentTypeForUSStateMACityBelmont() {
        given().
                spec(requestSpec).
        when().
                get("us/ma/belmont").
        then().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    public void getLogRequestAndResponseForUSStateMACityBelmont() {
        given().
                spec(requestSpec).
                log().all().
        when().
                get("us/ma/belmont").
        then().
                log().body();
    }

    @Test
    public void validatePlaceNameForUSStateMACityBelmont() {
        given().
                spec(requestSpec).
        when().
                get("us/ma/belmont").
        then().
                spec(responseSpec).
        and().
                assertThat().
                body("'place name'", equalTo("Belmont")).
                body("places[0].'place name'", equalTo("Belmont")).
                body("places[1].'place name'", equalTo("Belmont"));
    }

    @Test
    public void validateStateForUSStateMACityBelmont() {
        given().
                spec(requestSpec).
        when().
                get("us/ma/belmont").
        then().
                assertThat().
                body("state", equalTo("Massachusetts"));
    }

    @Test
    public void validateListOfPlaceNamesUSStateMACityBelmont() {
        given().
                spec(requestSpec).
        when().
                get("us/ma/belmont").
        then().
                assertThat().
                body("places.'place name'", hasItem("Belmont")).
                body("places.'place name'", not(hasItem("Beverly Hills")));
    }

    @Test
    public void validateNumberOfPlaceNamesUSStateMACityBelmont() {
        given().
                spec(requestSpec).
        when().
                get( "us/ma/belmont").
        then().
                assertThat().
                body("places.'place name'", hasSize(2));
    }

    @Test
    public void validateAndExtractPlaceNameForUSStateMACityBelmont() {

         Response response =
         given().
                 spec(requestSpec).
         when().
                get("us/ma/belmont").
         then().
                extract().
                response();

         List<String> placeNames = new ArrayList<>();

         placeNames.add(response.path("'place name'"));
         placeNames.addAll(response.jsonPath().getList("places.'place name'"));

         Assert.assertEquals(placeNames.size(), 3);
         Assert.assertEquals(placeNames.get(0), "Belmont");
         Assert.assertEquals(placeNames.get(1), "Belmont");
         Assert.assertEquals(placeNames.get(2), "Belmont");
         Assert.assertTrue(placeNames.contains("Belmont"));
    }

    @Test(dataProvider = "countryCodeStateCodeCityAndPlaceName")
    public void validatePlaceName(String countryCode, String stateCode, String city, String expectedPlaceName) {
        given().
                spec(requestSpec).
                pathParam("countryCode", countryCode).
                pathParam("stateCode", stateCode).
                pathParam("city", city).
        when().
                get("{countryCode}/{stateCode}/{city}").
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
