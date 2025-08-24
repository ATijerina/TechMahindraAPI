package tests;


import api.BookingClient;
import base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class BookingNegativeTests extends BaseApiTest {

    private final BookingClient bookingClient = new BookingClient();

    //Caso negativo 1: Faltan campos obligatorios (ej: falta "firstname")

    @Test
    public void createBooking_MissingRequiredFields_ShouldFail() {
        Map<String, Object> invalidBooking = new HashMap<>();
        invalidBooking.put("lastname", "Doe");
        invalidBooking.put("totalprice", 100);
        invalidBooking.put("depositpaid", true);

        Response response = bookingClient.createBooking(invalidBooking, token);

        Assert.assertEquals(response.getStatusCode(), 400, "El status code debería ser 400");
        Assert.assertTrue(response.getBody().asString().contains("firstname"),
                "La respuesta debería indicar que falta 'firstname'");
    }

    /**
     * Caso negativo 2: Fechas inválidas (checkout antes que checkin)
     */
    @Test
    public void createBooking_InvalidDates_ShouldFail() {
        Map<String, Object> invalidBooking = new HashMap<>();
        invalidBooking.put("firstname", "John");
        invalidBooking.put("lastname", "Doe");
        invalidBooking.put("totalprice", 150);
        invalidBooking.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2025-08-20");
        bookingDates.put("checkout", "2025-08-15"); // ❌ checkout antes que checkin
        invalidBooking.put("bookingdates", bookingDates);

        Response response = bookingClient.createBooking(invalidBooking, token);

        Assert.assertEquals(response.getStatusCode(), 400, "El status code debería ser 400");
        Assert.assertTrue(response.getBody().asString().contains("checkout"),
                "La respuesta debería indicar un error con la fecha de checkout");
    }

    /**
     * Caso negativo 3: Crear dos reservas idénticas (duplicado)
     */
    @Test
    public void createBooking_ValidBooking_ShouldSucceed() {
        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", "John");
        booking.put("lastname", "Doe");
        booking.put("totalprice", 200);  // >= 1
        booking.put("depositpaid", true);

        // Le paso el RoomID anteriormente sin esto me falla
        booking.put("roomid", 1);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2025-09-01");
        bookingDates.put("checkout", "2025-09-05");
        booking.put("bookingdates", bookingDates);

        // Primer intento
        Response firstResponse = bookingClient.createBooking(booking, token);
        firstResponse.then().log().all(); // Muestra la respuesta completa
        Assert.assertEquals(firstResponse.getStatusCode(), 201, "La primera reserva debería ser creada con éxito");

        // Segundo intento  (duplicado)
        Response secondResponse = bookingClient.createBooking(booking, token);
        secondResponse.then().log().all();
        Assert.assertEquals(secondResponse.getStatusCode(), 409, "El status code debería ser 409 por duplicado");
        Assert.assertTrue(secondResponse.getBody().asString().contains("duplicate"),
                "La respuesta debería indicar que es un booking duplicado");
    }
}