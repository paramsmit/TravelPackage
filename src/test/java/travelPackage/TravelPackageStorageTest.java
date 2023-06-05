package travelPackage;

import data.TravelPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.TravelPackageStorage;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TravelPackageStorageTest {

    TravelPackage travelPackage;

    @BeforeEach
    public void setup(){
        travelPackage = new TravelPackage("id", "name", 123);
        TravelPackageStorage.save(travelPackage);
    }

    @Test
    public void success(){
        Assertions.assertEquals(travelPackage, TravelPackageStorage.get("id"));
    }

    @Test
    public void failure(){
        assertThrows(RuntimeException.class, () -> {
            TravelPackageStorage.get("id1");
        });
    }
}
