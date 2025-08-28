package tests;

import api.BookingClient;
import base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTests extends BaseApiTest {


    //DELETE requiere un bookingId real que solo la API puede generar.va a fallar
    @Test
    public void deleteBookingTest() {
        int bookingId = 12345; // ID SOS Y.Y

        BookingClient bookingClient = new BookingClient();
        Response response = bookingClient.deleteBooking(token, bookingId);

        Assert.assertEquals(response.getStatusCode(), 200, "El status code no es correcto");
        System.out.println("Delete Response: " + response.getBody().asString());
    }
}
