package api;

import base.BaseApiTest;
import io.restassured.response.Response;
import utils.EndpointUtils;

import static io.restassured.RestAssured.given;

public class AuthClient {

    //Login
    public Response login(String username, String password) {
        return given()

                .header("Connection","keep-alive")

                .header("Content-Type","application/json")
                .body("{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}")
                .when()
                .post("auth/login")
                .then()
                .extract().response();

    }
}
