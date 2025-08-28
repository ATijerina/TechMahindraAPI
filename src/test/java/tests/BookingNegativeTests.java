package tests;


import api.BookingClient;
import base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
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

        // Validamos que el status code sea 400
        Assert.assertEquals(response.getStatusCode(), 400, "El status code debería ser 400");

        // Extraemos el array de errores del body JSON
        List<String> errors = response.jsonPath().getList("errors");

        // Validamos que contenga el error de firstname
        Assert.assertTrue(errors.contains("Firstname should not be blank"),
                "La respuesta debería indicar que falta 'firstname'. Errores: " + errors);
    }

    // Caso negativo 2: Fechas inválidas (checkout antes que checkin)

    @Test
    public void createBooking_InvalidDates_ShouldFail() {
        Map<String, Object> invalidBooking = new HashMap<>();
        invalidBooking.put("firstname", "John");
        invalidBooking.put("lastname", "Doe");
        invalidBooking.put("totalprice", 150);
        invalidBooking.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2025-08-20");
        bookingDates.put("checkout", "2025-08-15"); // checkout antes que checkin
        invalidBooking.put("bookingdates", bookingDates);

        Response response = bookingClient.createBooking(invalidBooking, token);

        // Imprimir el status code en consola
        int statusCode = response.getStatusCode();
        System.out.println("Status code recibido: " + statusCode);

        // Validar que el status code sea 400 o 500
        Assert.assertTrue(statusCode == 400 || statusCode == 500,
                "El status code debería ser 400 o 500 pero fue: " + statusCode);
    }

}


