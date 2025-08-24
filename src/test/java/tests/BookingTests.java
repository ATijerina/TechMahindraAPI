package tests;
import api.AuthClient;
import api.BookingClient;
import base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataBuilders;

import java.util.Map;

public class BookingTests extends BaseApiTest {
    private BookingClient bookingClient = new BookingClient();

    @Test
    public void createBookingTest() {
        AuthClient authClient = new AuthClient();
        Response auth = authClient.login("admin", "password");
        String token = auth.jsonPath().getString("token");

        Map<String, Object> booking =
                TestDataBuilders.createBooking("Andrea", "Tijerina");
        Response response = bookingClient.createBooking(booking, token);

        Assert.assertEquals(response.statusCode(), 200,
                "BUG: La API falla al crear booking. Response body: " + response.asString());

        String bookingId = response.jsonPath().getString("bookingid");
        Assert.assertNotNull(bookingId,
                "BUG: La API no devuelve bookingid. Response body: " + response.asString());
    }

}