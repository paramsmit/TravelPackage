package passenger;

import apis.PassengerAPI;
import data.Activity;
import data.Passenger;
import data.PassengerType;
import data.TravelPackage;
import managers.ActivityManager;
import managers.PassengerManager;
import managers.TravelPackageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassengerAPITest {

    private TravelPackageManager travelPackageManager;
    private PassengerManager passengerManager;
    private ActivityManager activityManager;
    private PassengerAPI passengerAPI;

    @BeforeEach
    void setUp() {
        travelPackageManager = mock(TravelPackageManager.class);
        passengerManager = mock(PassengerManager.class);
        activityManager = mock(ActivityManager.class);
        passengerAPI = new PassengerAPI(travelPackageManager, passengerManager, activityManager);
    }

    @Test
    void createPassengerWithTravelPackage() {
        String name = "John Doe";
        int number = 12345;
        PassengerType passengerType = PassengerType.STANDARD;
        double balance = 100.0;
        String travelPackageID = "TP1";
        String passengerID = "P1";

        TravelPackage travelPackage = new TravelPackage(travelPackageID, "Travel Package", 10);
        when(travelPackageManager.getTravelPackageByID(travelPackageID)).thenReturn(travelPackage);
        when(passengerManager.createPassenger(name, balance, passengerType, number)).thenReturn(passengerID);

        String createdPassengerID = passengerAPI.createPassengerWithTravelPackage(name, number, passengerType, balance, travelPackageID);

        assertNotNull(createdPassengerID);
        assertEquals(9, travelPackage.getCapacity());

        verify(travelPackageManager, times(1)).getTravelPackageByID(travelPackageID);
        verify(passengerManager, times(1)).createPassenger(name, balance, passengerType, number);
        verify(travelPackageManager, times(1)).updateTravelPackage(travelPackage);
    }

    @Test
    void testCreatePassengerWithTravelPackage_InvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> {
            passengerAPI.createPassengerWithTravelPackage(null, 12345, PassengerType.STANDARD, 100.0, "TP1");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            passengerAPI.createPassengerWithTravelPackage("John Doe", 12345, null, 100.0, "TP1");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            passengerAPI.createPassengerWithTravelPackage("John Doe", 12345, PassengerType.STANDARD, -100.0, "TP1");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            passengerAPI.createPassengerWithTravelPackage("John Doe", 12345, PassengerType.STANDARD, 100.0, null);
        });
    }

    @Test
    void testCreatePassengerWithTravelPackage_TravelPackageCapacityExceeded() {
        String name = "John Doe";
        int number = 12345;
        PassengerType passengerType = PassengerType.STANDARD;
        double balance = 100.0;
        String travelPackageID = "TP1";

        TravelPackage travelPackage = new TravelPackage(travelPackageID, "Travel Package", 0);
        when(travelPackageManager.getTravelPackageByID(travelPackageID)).thenReturn(travelPackage);

        assertThrows(RuntimeException.class, () -> {
            passengerAPI.createPassengerWithTravelPackage(name, number, passengerType, balance, travelPackageID);
        });

        verify(travelPackageManager, times(1)).getTravelPackageByID(travelPackageID);
        verify(passengerManager, never()).createPassenger(anyString(), anyDouble(), any(PassengerType.class), anyInt());
        verify(travelPackageManager, never()).updateTravelPackage(any(TravelPackage.class));
    }

    @Test
    void testAddActivityToPassenger() {
        String passengerID = "P1";
        String activityID = "A1";

        Passenger passenger = new Passenger(passengerID, "John Doe", 12345, 100.0, PassengerType.STANDARD);
        Activity activity = new Activity(activityID, "Activity", "Description", 50.0, 10, "D1");

        when(passengerManager.getPassengerByID(passengerID)).thenReturn(passenger);
        when(activityManager.getActivityByID(activityID)).thenReturn(activity);

        passengerAPI.addActivityToPassenger(passengerID, activityID);

        verify(passengerManager, times(1)).getPassengerByID(passengerID);
        verify(activityManager, times(1)).getActivityByID(activityID);
    }

    @Test
    void testAddActivityToPassenger_InvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> {
            passengerAPI.addActivityToPassenger(null, "A1");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            passengerAPI.addActivityToPassenger("P1", null);
        });
    }
}
