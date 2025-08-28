package api;

import io.restassured.response.Response;
import utils.EndpointUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingClient {

    //Crear reserva
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

    //obtener las reservas (admin)
    public Response getAllBookings(String token) {
        return given()
                .header("Cookie", "token=" + token)
                .when()
                .get(EndpointUtils.ADMIN_BOOKINGS)
                .then()
                .extract().response();
    }



        // Obtener booking por ID
        public Response getBookingById(String token, int bookingId) {
            return given()
                    .header("Cookie", "token=" + token)
                    .when()
                    .get("/"+EndpointUtils.BOOKINGS+"/" + bookingId) // Path parameter
                    .then()
                    .extract().response();
        }


        // Metodo para eliminar por ID (El cual no lo envia el request)
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

