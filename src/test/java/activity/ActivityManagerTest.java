package activity;

import data.Activity;
import managers.ActivityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ActivityStorage;

import static org.junit.jupiter.api.Assertions.*;

class ActivityManagerTest {

    private ActivityManager activityManager;

    @BeforeEach
    void setUp() {
        activityManager = new ActivityManager();
    }

    @Test
    void createActivityWithDestination() {
        String name = "Activity 1";
        String description = "This is activity 1";
        double cost = 50.0;
        int capacity = 10;
        String destinationID = "D1";

        String activityID = activityManager.createActivityWithDestination(name, description, cost, capacity, destinationID);

        assertNotNull(activityID);
        Activity storedActivity = activityManager.getActivityByID(activityID);

        assertNotNull(storedActivity);
        assertEquals(name, storedActivity.getName());
        assertEquals(description, storedActivity.getDescription());
        assertEquals(cost, storedActivity.getCost());
        assertEquals(capacity, storedActivity.getCapacity());
        assertEquals(destinationID, storedActivity.getDestinationID());
    }

    @Test
    void getActivityByID() {
        String activityID = "A1";
        Activity activity = new Activity(activityID, "Activity 1", "Description 1", 50.0, 10, "D1");
        ActivityStorage.save(activity);

        Activity retrievedActivity = activityManager.getActivityByID(activityID);

        assertNotNull(retrievedActivity);
        assertEquals(activityID, retrievedActivity.getId());
        assertEquals("Activity 1", retrievedActivity.getName());
        assertEquals("Description 1", retrievedActivity.getDescription());
        assertEquals(50.0, retrievedActivity.getCost());
        assertEquals(10, retrievedActivity.getCapacity());
        assertEquals("D1", retrievedActivity.getDestinationID());
    }

    @Test
    void update() {
        Activity activity = new Activity("A1", "Activity 1", "Description 1", 50.0, 10, "D1");

        activityManager.updateActivity(activity);

        Activity retrievedActivity = activityManager.getActivityByID("A1");
        assertNotNull(retrievedActivity);
        assertEquals("Activity 1", retrievedActivity.getName());
        assertEquals("Description 1", retrievedActivity.getDescription());
        assertEquals(50.0, retrievedActivity.getCost(), 0.01);
        assertEquals(10, retrievedActivity.getCapacity());
        assertEquals("D1", retrievedActivity.getDestinationID());
    }
}
