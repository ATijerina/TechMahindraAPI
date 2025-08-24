package tests;

import api.AuthClient;
import api.BookingClient;

import base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class RetrieveAllRoomsTests extends BaseApiTest {

    @Test
    public void retrieveAllRoomsTest() {


        BookingClient bookingClient = new BookingClient();

        AuthClient authClient = new AuthClient();
        Response auth = authClient.login("admin", "password");
        String token = auth.jsonPath().getString("token");

        Response response = bookingClient.getAllRooms(token);

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());

        // Imprimir por consola
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Obtener la lista de habitaciones
        List<Map<String, Object>> rooms = response.jsonPath().getList("rooms");

        // Validación básica: al menos 3 habitaciones
        Assert.assertTrue(rooms.size() >= 3, "No se obtuvieron suficientes habitaciones");

        // Ejemplo de validar la primera habitación
        Assert.assertEquals(rooms.get(0).get("roomid"), 1);
        Assert.assertEquals(rooms.get(0).get("roomName"), "101");

    }
}



