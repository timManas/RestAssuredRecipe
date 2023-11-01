package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestConfig {

    private static String HEADER_TYPE = "json";  // This will modify what type of response we get back (json or xml)

    @BeforeClass
    public static void setupBeforeClass() {
        System.out.println("----- Execute BeforeClass Setup ----- ");
        RestAssured.baseURI = "https://videogamedb.uk/";         // Set base URI
        RestAssured.basePath = "api/v2";                                      // Set base path ... This is /api/v2
        RestAssured.port = 443;                     // Set base Port

        // Specify the request specification
        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        RestAssured.requestSpecification =  reqSpecBuilder.setContentType("application/json").
                addHeader("Accept", "application/" + HEADER_TYPE).
                build();

        // Specify the response specification. This checks if we have a status code 200
        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();
        RestAssured.responseSpecification = resSpecBuilder.expectStatusCode(200).build();

    }

    @Before
    public void setupBefore() {
        System.out.println("----- Execute Before Setup ----- ");

    }


}
