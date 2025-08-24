package tests;

import api.BookingClient;
import base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingByIdTests extends BaseApiTest {

    @Test
    public void getBookingByIdTest() {
        int bookingId = 12345; // No logro sacar el ID de reservacion SOS Y.Y

        BookingClient bookingClient = new BookingClient();
        Response response = bookingClient.getBookingById(token, bookingId);

        Assert.assertEquals(response.getStatusCode(), 200, "El status code no es correcto");
        System.out.println("Booking details: " + response.getBody().asString());
    }
}
