package tests;

import request.LocationRequest;
import response.LocationResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static utils.Constants.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

public class LocationTests extends BaseTest{

    @Test(description = "Get location by country and postal code - deserialized Java response object")
    public void getLocationByCountryAndPostalCodeDeserializedResponseTest() {

        LocationRequest request = new LocationRequest(COUNTRY_ABBREVIATION_US, POSTAL_CODE_BEVERLY_HILLS);

        LocationResponse response = performGetOperation(request.getEndpointPath(), LocationResponse.class);

        Assert.assertNotNull(response, "Response should not be null!");
        Assert.assertEquals(response.getCountry(), COUNTRY_UNITED_STATES);
        Assert.assertEquals(response.getCountryAbbreviation(), COUNTRY_ABBREVIATION_US);
        Assert.assertEquals(response.getPostCode(), POSTAL_CODE_BEVERLY_HILLS);

        Assert.assertNotNull(response.getPlaces());
        Assert.assertFalse(response.getPlaces().isEmpty());
        Assert.assertEquals(response.getPlaces().get(0).getPlaceName(), PLACE_NAME_BEVERLY_HILLS);
        Assert.assertEquals(response.getPlaces().get(0).getLongitude(), LONGITUDE_BEVERLY_HILLS);
        Assert.assertEquals(response.getPlaces().get(0).getLatitude(), LATITUDE_BEVERLY_HILLS);
        Assert.assertEquals(response.getPlaces().get(0).getState(), STATE_CALIFORNIA);
        Assert.assertEquals(response.getPlaces().get(0).getStateAbbreviation(), STATE_ABBREVIATION_CA);
    }

    @Test(description = "Get location by country and postal code - JSON response validation using JsonPath and Hamcrest")
    public void getLocationByCountryAndPostalCodeJsonPathValidationTest() {

        LocationRequest request = new LocationRequest(COUNTRY_ABBREVIATION_US, POSTAL_CODE_BEVERLY_HILLS);

        LocationResponse response = performGetOperation(request.getEndpointPath(), LocationResponse.class);

        List<String> placeNames = response.getPlaces().stream()
                .map(LocationResponse.Place::getPlaceName)
                .toList();

        assertThat(placeNames, hasItem(PLACE_NAME_BEVERLY_HILLS));
        assertThat(placeNames, not(hasItem("Belmont")));
        assertThat(placeNames, hasSize(1));
    }

    String baseUrl = "http://zippopotam.us/";


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
    public void validateAndExtractPlaceNameForUSZipCode90210() {

        String placeName =

        given().
        when().
                get(baseUrl + "us/90210").
        then().
                extract().
                path("places[0].'place name'");

        Assert.assertEquals(placeName, "Beverly Hills");
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
