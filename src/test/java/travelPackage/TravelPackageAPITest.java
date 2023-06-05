package travelPackage;
import apis.TravelPackageAPI;
import data.TravelPackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.TravelPackageStorage;

import static org.junit.jupiter.api.Assertions.*;

class TravelPackageAPITest {

    private TravelPackageAPI travelPackageAPI;

    @BeforeEach
    void setUp() {
        travelPackageAPI = new TravelPackageAPI();
    }

    @Test
    void testCreateTravelPackage() {
        String name = "Travel Package 1";
        int capacity = 10;

        String travelPackageID = travelPackageAPI.createTravelPackage(name, capacity);
        assertNotNull(travelPackageID);

        TravelPackage storedTravelPackage = TravelPackageStorage.get(travelPackageID);
        assertEquals(capacity, storedTravelPackage.getCapacity());
        assertEquals(name, storedTravelPackage.getName());
    }

    @Test
    void testCreateTravelPackageWithInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () ->
                travelPackageAPI.createTravelPackage(null, 10));
        assertThrows(IllegalArgumentException.class, () ->
                travelPackageAPI.createTravelPackage("Travel Package 1", 0));
        assertThrows(IllegalArgumentException.class, () ->
                travelPackageAPI.createTravelPackage("Travel Package 1", -5));
    }
}
