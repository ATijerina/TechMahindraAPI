package api;

import io.restassured.response.Response;
import utils.EndpointUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingClient {

    /**
     * Crear reserva
     */
    public Response createBooking(Map<String, Object> bookingData, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .body(bookingData)
                .when()
                .post(EndpointUtils.BOOKINGS)
                .then()
                .extract().response();
    }

    /**
     * Obtener todas las reservas (admin)
     */
    public Response getAllBookings(String token) {
        return given()
                .header("Cookie", "token=" + token)
                .when()
                .get(EndpointUtils.ADMIN_BOOKINGS)
                .then()
                .extract().response();
    }

    /**
     * Obtener reserva por ID
     */
    public Response getBookingById(int bookingId, String token) {
        return given()
                .header("Cookie", "token=" + token)
                .when()
                .get(EndpointUtils.BOOKINGS + "/" + bookingId)
                .then()
                .extract().response();
    }


        // Obtener booking por ID
        public Response getBookingById(String token, int bookingId) {
            return given()
                    .header("Cookie", "token=" + token)
                    .when()
                    .get("/booking/" + bookingId) // Path parameter
                    .then()
                    .extract().response();
        }


        // Método para eliminar booking por ID
        public Response deleteBooking(String token, int bookingId) {
            return given()
                    .header("Cookie", "token=" + token)
                    .when()
                    .delete("/booking/" + bookingId)
                    .then()
                    .extract().response();
    }


    // Obtener todas las habitaciones
    public Response getAllRooms(String token) {
        return given()
                .header("Cookie", "token=" + token)
                .when()
                .get("/room")
                .then()
                .extract().response();
    }

}

