package activity;

import apis.ActivityAPI;
import data.Destination;
import managers.ActivityManager;
import managers.DestinationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ActivityAPITest {

    private DestinationManager destinationManager;
    private ActivityManager activityManager;
    private ActivityAPI activityAPI;

    @BeforeEach
    void setUp() {
        destinationManager = mock(DestinationManager.class);
        activityManager = mock(ActivityManager.class);
        activityAPI = new ActivityAPI(activityManager, destinationManager);
    }

    @Test
    void testCreateActivityWithDestination() {
        String name = "Activity 1";
        String description = "This is activity 1";
        double cost = 50.0;
        int capacity = 10;
        String destinationID = "D1";
        String activityID = "A1";

        Destination destination = new Destination(destinationID, "Destination 1");
        when(destinationManager.getDestinationById(destinationID)).thenReturn(destination);
        when(activityManager.createActivityWithDestination(name, description, cost, capacity, destinationID)).thenReturn(activityID);

        String createdActivityID = activityAPI.createActivityWithDestination(name, description, cost, capacity, destinationID);

        assertNotNull(createdActivityID);
        assertEquals(activityID, createdActivityID);

        verify(destinationManager, times(1)).getDestinationById(destinationID);
        verify(activityManager, times(1)).createActivityWithDestination(name, description, cost, capacity, destinationID);
        verify(destinationManager, times(1)).updateDestination(destination);
    }

    @Test
    void testCreateActivityWithDestination_InvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Invalid input: name is null
            activityAPI.createActivityWithDestination(null, "Description", 50.0, 10, "D1");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            // Invalid input: description is null
            activityAPI.createActivityWithDestination("Activity", null, 50.0, 10, "D1");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            // Invalid input: cost is negative
            activityAPI.createActivityWithDestination("Activity", "Description", -50.0, 10, "D1");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            // Invalid input: capacity is zero
            activityAPI.createActivityWithDestination("Activity", "Description", 50.0, 0, "D1");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            // Invalid input: destinationID is null
            activityAPI.createActivityWithDestination("Activity", "Description", 50.0, 10, null);
        });
    }

}
