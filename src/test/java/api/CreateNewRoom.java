package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CreateNewRoom {

    // Crear nueva habitación
    public static Response createRoom(RequestSpecification requestSpec, Object roomData, String token) {
        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(roomData)
                .when()
                .post("/room");
    }


    // Método para obtener todas las habitaciones
    public Response getAllRooms(RequestSpecification requestSpec, String token) {
        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/rooms"); // o la ruta exacta que tenga tu API
    }

}
