package destination;

import data.Destination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.DestinationStorage;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DestinationStorageTest {

    Destination destination;

    @BeforeEach
    public void setup(){
        destination = new Destination("id", "name");
        DestinationStorage.save(destination);

    }

    @Test
    public void success(){
        Assertions.assertEquals(destination, DestinationStorage.get("id"));
    }

    @Test
    public void failure(){
        assertThrows(RuntimeException.class, () -> {
            DestinationStorage.get("id1");
        });
    }
}
