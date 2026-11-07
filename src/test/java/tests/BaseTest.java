package tests;

import utils.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class BaseTest {

    protected <T> T performGetOperation(
            String endpoint,
            Class<T> responseClass) {

        Response response = RestAssured.get(Constants.BASE_URL + endpoint);

        Assert.assertEquals(response.statusCode(), 200,
                "Unexpected HTTP status code!");
        Assert.assertTrue(response.contentType().contains(ContentType.JSON.toString()),
                "Unexpected Content-Type!"
        );

        return response.as(responseClass);
    }
}

