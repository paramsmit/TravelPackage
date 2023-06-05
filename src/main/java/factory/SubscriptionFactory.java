package factory;

import data.Activity;
import data.Passenger;
import data.PassengerType;
import subscription.GoldSubscription;
import subscription.PassengerSubscription;
import subscription.PremiumSubscription;
import subscription.StandardSubscription;

public class SubscriptionFactory {

    private SubscriptionFactory(){};

    public static PassengerSubscription create(Activity activity, Passenger passenger){

        if(passenger.getPassengerType().equals(PassengerType.STANDARD)){
            return new StandardSubscription(activity, passenger);
        }

        else if(passenger.getPassengerType().equals(PassengerType.GOLD)){
            return new GoldSubscription(activity, passenger);
        }

        else if(passenger.getPassengerType().equals(PassengerType.PREMIUM)){
            return new PremiumSubscription(activity, passenger);
        }

        throw new RuntimeException();

    }
}
