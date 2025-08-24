package tests;

import api.CreateNewRoom;
import base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RoomData;

public class NuevaHabitacionTests extends BaseApiTest {

    @Test
    public void crearNuevaHabitacionTest() {
        // Llamar al body desde RoomData
        Response response = new CreateNewRoom().createRoom(requestSpec, RoomData.nuevaHabitacion(), token);

        // Validaciones
        Assert.assertEquals(response.getStatusCode(), 200, "El status code no es correcto");
        Assert.assertTrue(response.jsonPath().getBoolean("success"), "La habitación no se creó correctamente");

        System.out.println("Response Body: " + response.getBody().asString());
    }
}
