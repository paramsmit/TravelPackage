package subscription;

import data.Activity;
import data.Passenger;
import data.PassengerType;
import factory.SubscriptionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionFactoryTest {

    @Test
    void testCreateStandardSubscription() {
        Activity activity = new Activity("A1", "Activity 1", "Description 1", 10.0, 10, "D1");
        Passenger passenger = new Passenger("P1", "John Doe", 123, 100, PassengerType.STANDARD);

        PassengerSubscription subscription = SubscriptionFactory.create(activity, passenger);

        assertNotNull(subscription);
        assertTrue(subscription instanceof StandardSubscription);
    }

    @Test
    void testCreateGoldSubscription() {
        Activity activity = new Activity("A2", "Activity 2", "Description 2", 20.0, 10, "D2");
        Passenger passenger = new Passenger("P2", "John Doe", 123, 100,  PassengerType.GOLD);

        PassengerSubscription subscription = SubscriptionFactory.create(activity, passenger);

        assertNotNull(subscription);
        assertTrue(subscription instanceof GoldSubscription);
    }

    @Test
    void testCreatePremiumSubscription() {
        Activity activity = new Activity("A3", "Activity 3", "Description 3", 30.0,10, "D3");
        Passenger passenger = new Passenger("P3", "John Doe", 12,11, PassengerType.PREMIUM);

        PassengerSubscription subscription = SubscriptionFactory.create(activity, passenger);
        assertNotNull(subscription);
        assertTrue(subscription instanceof PremiumSubscription);
    }

    @Test
    void testCreateSubscriptionWithUnknownPassengerType() {
        Activity activity = new Activity("A4", "Activity 4", "Description 4", 40.0, 10, "D4");
        Passenger passenger = new Passenger("P4", "John Doe", 12, 11, null);

        assertThrows(RuntimeException.class, () ->
                SubscriptionFactory.create(activity, passenger));
    }
}
