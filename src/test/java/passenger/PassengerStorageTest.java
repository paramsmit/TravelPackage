package passenger;

import data.Passenger;
import data.PassengerType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.PassengerStorage;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PassengerStorageTest {

    Passenger passenger;

    @BeforeEach
    public void setup(){
        passenger = new Passenger("id", "name", 123, 1, PassengerType.STANDARD);
        PassengerStorage.save(passenger);
    }

    @Test
    public void success(){
        Assertions.assertEquals(passenger, PassengerStorage.get("id"));
    }

    @Test
    public void failure(){
        assertThrows(RuntimeException.class, () -> {
            PassengerStorage.get("id1");
        });
    }
}
