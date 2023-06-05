package destination;

import apis.DestinationAPI;
import data.Destination;
import data.TravelPackage;
import managers.DestinationManager;
import managers.TravelPackageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DestinationAPITest {

    private DestinationManager destinationManager;
    private TravelPackageManager travelPackageManager;
    private DestinationAPI destinationAPI;

    @BeforeEach
    void setUp() {
        destinationManager = mock(DestinationManager.class);
        travelPackageManager = mock(TravelPackageManager.class);
        destinationAPI = new DestinationAPI(destinationManager, travelPackageManager);
    }


    @Test
    void testAddDestination() {
        String name = "Destination 1";
        String destinationID = "D1";

        when(destinationManager.createDestination(name)).thenReturn(destinationID);

        String createdDestinationID = destinationAPI.addDestination(name);

        assertNotNull(createdDestinationID);
        assertEquals(destinationID, createdDestinationID);

        verify(destinationManager, times(1)).createDestination(name);
    }

    @Test
    void testAddDestinationToTravelPackage() {
        String destinationID = "D1";
        String travelPackageID = "TP1";

        Destination destination = new Destination(destinationID, "Destination 1");
        TravelPackage travelPackage = new TravelPackage(travelPackageID, "Travel Package 1", 10);

        when(destinationManager.getDestinationById(destinationID)).thenReturn(destination);
        when(travelPackageManager.getTravelPackageByID(travelPackageID)).thenReturn(travelPackage);

        destinationAPI.addDestinationToTravelPackage(destinationID, travelPackageID);

        assertTrue(travelPackage.getDestinations().contains(destinationID));
        verify(destinationManager, times(1)).getDestinationById(destinationID);
        verify(travelPackageManager, times(1)).getTravelPackageByID(travelPackageID);
        verify(travelPackageManager, times(1)).updateTravelPackage(travelPackage);
    }

    @Test
    void addDestinationToTravelPackage_ArgumentValidations() {
        String destinationID = "D1";
        String travelPackageID = "TP1";

        Destination destination = new Destination(destinationID, "Destination 1");
        TravelPackage travelPackage = new TravelPackage(travelPackageID, "Travel Package 1", 10);
        travelPackage.getDestinations().add(destinationID); // Add the destination ID to the travel package

        when(destinationManager.getDestinationById(destinationID)).thenReturn(destination);
        when(travelPackageManager.getTravelPackageByID(travelPackageID)).thenReturn(travelPackage);

        assertThrows(IllegalArgumentException.class,
                () -> destinationAPI.addDestinationToTravelPackage(null, travelPackageID));
        assertThrows(IllegalArgumentException.class,
                () -> destinationAPI.addDestinationToTravelPackage(destinationID, null));
        assertThrows(IllegalArgumentException.class,
                () -> destinationAPI.addDestinationToTravelPackage("", travelPackageID));
        assertThrows(IllegalArgumentException.class,
                () -> destinationAPI.addDestinationToTravelPackage(destinationID, ""));

        verify(destinationManager, never()).getDestinationById(destinationID);
        verify(travelPackageManager, never()).getTravelPackageByID(travelPackageID);
        verify(travelPackageManager, never()).updateTravelPackage(Mockito.any(TravelPackage.class));
    }
}
