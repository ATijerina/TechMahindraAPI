package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Helpers {

    public static String getTodayDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String getTomorrowDate() {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
