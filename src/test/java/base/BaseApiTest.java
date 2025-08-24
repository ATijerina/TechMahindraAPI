package base;

import api.AuthClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    public RequestSpecification requestSpec;
    public String token ; // Token

    @BeforeClass
    public void setup() {
        // URI base de la API
        RestAssured.baseURI = "https://automationintesting.online/api";

        AuthClient authClient = new AuthClient();
        Response response = authClient.login("admin", "password");
        String token = response.jsonPath().getString("token");

        // RequestSpecification común para todos los tests
        requestSpec = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token);



    }
}



