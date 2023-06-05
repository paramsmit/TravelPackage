package destination;
import data.Destination;
import managers.DestinationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.DestinationStorage;

import static org.junit.jupiter.api.Assertions.*;

class DestinationManagerTest {

    private DestinationManager destinationManager;

    @BeforeEach
    void setUp() {
        destinationManager = new DestinationManager();
    }

    @Test
    void createDestination() {
        String name = "Destination 1";

        String destinationId = destinationManager.createDestination(name);

        assertNotNull(destinationId);

        Destination storedDestination = destinationManager.getDestinationById(destinationId);
        assertNotNull(storedDestination);
        assertEquals(name, storedDestination.getName());
    }

    @Test
    void getDestinationById() {
        String name = "Destination 1";
        String destinationId = "D1";
        Destination destination = new Destination(destinationId, name);

        DestinationStorage.save(destination); // Save the destination to the storage

        Destination retrievedDestination = destinationManager.getDestinationById(destinationId);

        assertNotNull(retrievedDestination);
        assertEquals(destination, retrievedDestination);
    }

    @Test
    void updateDestination() {
        String name = "Destination 1";
        String destinationId = "D1";
        Destination destination = new Destination(destinationId, name);

        DestinationStorage.save(destination); // Save the destination to the storage

        destination.getActivities().add("A1");
        destinationManager.updateDestination(destination);

        Destination updatedDestination = destinationManager.getDestinationById(destinationId);
        assertNotNull(updatedDestination);
        assertEquals(destination, updatedDestination);
        assertTrue(updatedDestination.getActivities().contains("A1"));
    }
}
