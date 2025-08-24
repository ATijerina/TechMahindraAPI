package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataBuilders {

    public static Map<String, Object> createBooking(String firstName, String lastName) {
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", Helpers.getTodayDate());
        bookingDates.put("checkout", Helpers.getTomorrowDate());

        Map<String, Object> booking = new HashMap<>();
        booking.put("roomid", 101);
        booking.put("firstname", firstName);
        booking.put("lastname", lastName);
        booking.put("depositpaid", false);
        booking.put("bookingdates", bookingDates);
        booking.put("additionalneeds", "Breakfast");

        return booking;
    }

    //  builder para Rooms
    public static Map<String, Object> buildRoom(String roomName, String type, boolean accessible,
                                                String description, String image, String roomPrice) {
        Map<String, Object> room = new HashMap<>();
        room.put("roomName", roomName);
        room.put("type", type);
        room.put("accessible", accessible);
        room.put("description", description);
        room.put("image", image);
        room.put("roomPrice", roomPrice);

        // Lista de features
        List<String> features = Arrays.asList("WiFi", "TV", "Radio", "Refreshments", "Safe", "Views");
        room.put("features", features);

        return room;
    }

          //builder para crear una nueva habitacion

        public static Map<String, Object> nuevaHabitacion() {
            Map<String, Object> roomData = new HashMap<>();
            roomData.put("roomName", "104");
            roomData.put("type", "Double");
            roomData.put("accessible", true);
            roomData.put("description", "Luxury double room with city view");
            roomData.put("image", "https://example.com/room104.jpg");
            roomData.put("roomPrice", 200);
            roomData.put("features", new String[]{"TV", "WiFi", "Air Conditioning"});
            return roomData;
        }
    }


