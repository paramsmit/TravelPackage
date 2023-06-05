package apis;

import data.Activity;
import data.Passenger;
import data.PassengerType;
import data.TravelPackage;
import factory.SubscriptionFactory;
import managers.ActivityManager;
import managers.PassengerManager;
import managers.TravelPackageManager;
import subscription.PassengerSubscription;

public class PassengerAPI {

    final TravelPackageManager travelPackageManager;
    final PassengerManager passengerManager;
    final ActivityManager activityManager;

    public PassengerAPI(TravelPackageManager travelPackageManager, PassengerManager passengerManager, ActivityManager activityManager){
        this.travelPackageManager = travelPackageManager;
        this.passengerManager = passengerManager;
        this.activityManager = activityManager;
    }

    public String createPassengerWithTravelPackage(String name, int number, PassengerType passengerType, double balance, String travelPackageID){
        // validations

        if(name == null || number <= 0 || passengerType == null || balance < 0 || travelPackageID == null){
            throw new IllegalArgumentException();
        }

        TravelPackage travelPackage = travelPackageManager.getTravelPackageByID(travelPackageID);

        if(travelPackage.getCapacity() == 0){
            throw new RuntimeException();
        }

        String passengerID = passengerManager.createPassenger(name, balance, passengerType, number);
        travelPackage.getPassengers().add(passengerID);
        travelPackage.setCapacity(travelPackage.getCapacity() - 1);
        travelPackageManager.updateTravelPackage(travelPackage);

        return passengerID;
    }

    public void addActivityToPassenger(String passengerID, String activityID){
        // validations

        if(passengerID == null || activityID == null){
            throw new IllegalArgumentException();
        }

        Passenger passenger = passengerManager.getPassengerByID(passengerID);
        Activity activity = activityManager.getActivityByID(activityID);
        PassengerSubscription passengerSubscription = SubscriptionFactory.create(activity, passenger);
        passengerSubscription.subscribeToActivity();
    }

}
