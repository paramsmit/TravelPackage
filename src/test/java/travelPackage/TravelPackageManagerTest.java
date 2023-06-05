package travelPackage;

import data.TravelPackage;
import managers.TravelPackageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.TravelPackageStorage;
import static org.junit.jupiter.api.Assertions.*;

class TravelPackageManagerTest {

    private TravelPackageManager travelPackageManager;

    @BeforeEach
    void setUp() {
        travelPackageManager = new TravelPackageManager();
    }

    @Test
    void testCreateTravelPackage() {
        String name = "Travel Package 1";
        int capacity = 10;

        String travelPackageID = travelPackageManager.createTravelPackage(name, capacity);

        assertNotNull(travelPackageID);

        TravelPackage travelPackage = TravelPackageStorage.get(travelPackageID);
        assertNotNull(travelPackage);
        assertEquals(name, travelPackage.getName());
        assertEquals(capacity, travelPackage.getCapacity());
    }

    @Test
    void testGetTravelPackageById() {
        String travelPackageID = "TP1";
        String name = "Travel Package 1";
        int capacity = 10;

        TravelPackage expectedTravelPackage = new TravelPackage(travelPackageID, name, capacity);
        TravelPackageStorage.save(expectedTravelPackage);

        TravelPackage travelPackage = travelPackageManager.getTravelPackageByID(travelPackageID);

        assertNotNull(travelPackage);
        assertEquals(expectedTravelPackage, travelPackage);
    }

    @Test
    void testUpdateTravelPackage() {
        String travelPackageID = "TP1";
        String name = "Travel Package 1";
        int capacity = 10;

        TravelPackage travelPackage = new TravelPackage(travelPackageID, name, capacity);

        TravelPackageStorage.save(travelPackage);

        TravelPackage modifiedTravelPackage = new TravelPackage("TP1", name, 15);
        travelPackageManager.updateTravelPackage(modifiedTravelPackage);

        TravelPackage updatedTravelPackage = TravelPackageStorage.get(travelPackageID);
        assertNotNull(updatedTravelPackage);
        assertEquals(15, updatedTravelPackage.getCapacity());
    }
}
