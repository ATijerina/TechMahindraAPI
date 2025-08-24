package utils;

import java.util.HashMap;
import java.util.Map;

public class RoomData {

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
