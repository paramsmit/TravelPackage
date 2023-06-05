package passenger;

import data.Passenger;
import data.PassengerType;
import managers.PassengerManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.PassengerStorage;

import static org.junit.jupiter.api.Assertions.*;

class PassengerManagerTest {

    private PassengerManager passengerManager;

    @BeforeEach
    void setUp() {
        passengerManager = new PassengerManager();
    }

    @Test
    void createPassenger() {
        double balance = 100.0;
        String name = "John Doe";
        PassengerType passengerType = PassengerType.STANDARD;
        int number = 12345;
        String passengerID = "P1";
        Passenger expectedPassenger = new Passenger(passengerID, name, number, balance, passengerType);

        String createdPassengerID = passengerManager.createPassenger(name, balance, passengerType, number);
        assertNotNull(createdPassengerID);

        Passenger storedPassenger = PassengerStorage.get(createdPassengerID);
        assertNotNull(storedPassenger);
        assertEquals(expectedPassenger.getPassengerType(), storedPassenger.getPassengerType());
        assertEquals(expectedPassenger.getName(), storedPassenger.getName());
        assertEquals(expectedPassenger.getBalance(), storedPassenger.getBalance());
        assertEquals(expectedPassenger.getNumber(), storedPassenger.getNumber());
    }

    @Test
    void getPassengerByID() {
        String passengerID = "P1";
        Passenger expectedPassenger = new Passenger(passengerID, "John Doe", 12345, 100.0, PassengerType.STANDARD);
        PassengerStorage.save(expectedPassenger);

        Passenger passenger = passengerManager.getPassengerByID(passengerID);

        assertNotNull(passenger);
        assertEquals(expectedPassenger.getId(), passenger.getId());
        assertEquals(expectedPassenger.getPassengerType(), passenger.getPassengerType());
        assertEquals(expectedPassenger.getName(), passenger.getName());
        assertEquals(expectedPassenger.getBalance(), passenger.getBalance());
        assertEquals(expectedPassenger.getNumber(), passenger.getNumber());
    }

    @Test
    void update() {
        String passengerID = "P1";
        Passenger passenger = new Passenger(passengerID, "John Doe", 12345, 100.0, PassengerType.STANDARD);
        PassengerStorage.save(passenger);

        Passenger modifiedPassenger = new Passenger(passengerID, "John Doe", 12345, 100.0, PassengerType.GOLD);
        passengerManager.update(modifiedPassenger);

        Passenger updatedPassenger = PassengerStorage.get(passengerID);

        assertNotNull(updatedPassenger);
        assertEquals(updatedPassenger.getPassengerType(), PassengerType.GOLD);
    }
}
