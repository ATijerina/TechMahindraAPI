package tests;

import api.AuthClient;
import base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTests extends BaseApiTest { //agregue aqui la extension

    @Test
    public void loginTest  () {
        // Crear instancia del cliente
        AuthClient authClient = new AuthClient();


        // Llamar al método login
        Response response = authClient.login("admin", "password");

        // Validar status code
        Assert.assertEquals(response.statusCode(), 200);

        // Validar que token existe
        String token = response.jsonPath().getString("token");
        Assert.assertNotNull(token, "Token no debe ser null");
    }
}

