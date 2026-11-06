package examples;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiZippopotamUsCountryPostalCodeTests {

    String baseUrl = "http://zippopotam.us/";

    /*
    Structure: api.zippopotam.us/country/postal-code
    Example: api.zippopotam.us/us/90210
     */
    @Test
    public void validateBodyResponseForUSZipCode90210() {
        given().
        when().
                get(baseUrl + "us/90210").
        then().
                assertThat().
                body("country", equalTo("United States")).
                body("'country abbreviation'", equalTo("US")).
                body("'post code'", equalTo("90210")).
                body("places[0].'place name'", equalTo("Beverly Hills")).
                body("places[0].longitude", equalTo("-118.4065")).
                body("places[0].latitude", equalTo("34.0901")).
                body("places[0].state", equalTo("California")).
                body("places[0].'state abbreviation'", equalTo("CA"));
    }

    @Test
    public void validateStatusCodeForUSZipCode90210() {
        given().
        when().
                get(baseUrl + "us/90210").
        then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validateContentTypeForUSZipCode90210() {
        given().
        when().
                get(baseUrl + "us/90210").
        then().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    public void getLogRequestAndResponseForUSZipCode90210() {
        given().
                log().all().
        when().
                get(baseUrl + "us/90210").
        then().
                log().body();
    }

    @Test
    public void validatePlaceNameForUSZipCode90210() {
        given().
        when().
                get(baseUrl + "us/90210").
        then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void validateStateForUSZipCode90210() {
        given().
        when().
                get(baseUrl + "us/90210").
        then().
                assertThat().
                body("places[0].state", equalTo("California"));
    }

    @Test
    public void validateListOfPlaceNamesForUSZipCode90210() {
        given().
        when().
                get(baseUrl + "us/90210").
        then().
                assertThat().
                body("places.'place name'", hasItem("Beverly Hills")).
                body("places.'place name'", not(hasItem("Belmont")));
    }

    @Test
    public void validateNumberOfPlaceNamesForUSZipCode90210() {
        given().
        when().
                get(baseUrl + "us/90210").
        then().
                assertThat().
                body("places.'place name'", hasSize(1));
    }

    @Test(dataProvider = "countryCodeZipCodeAndPlaceName")
    public void validatePlaceName(String countryCode, String zipCode, String expectedPlaceName) {
        given().
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
        when().
                get(baseUrl + "{countryCode}/{zipCode}").
        then().
                assertThat().
                body("places[0].'place name'", equalTo(expectedPlaceName));
    }

    @DataProvider
    public static Object[][] countryCodeZipCodeAndPlaceName(){
        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"},
                {"nl", "1001", "Amsterdam"}
        };
    }
}
