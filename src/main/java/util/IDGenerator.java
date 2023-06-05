package util;
import java.util.UUID;

public class IDGenerator {
    public static String getRandomId(){
        return UUID.randomUUID().toString();
    };
}

