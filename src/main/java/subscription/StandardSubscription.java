package subscription;

import data.Activity;
import data.ActivitySubscription;
import data.Passenger;
import managers.ActivityManager;
import managers.PassengerManager;

public class StandardSubscription implements PassengerSubscription{

    final Activity activity;
    final Passenger passenger;
    double charge;

    final ActivityManager activityManager = new ActivityManager();
    final PassengerManager passengerManager = new PassengerManager();

    public StandardSubscription(Activity activity, Passenger passenger) {
        this.activity = activity;
        this.passenger = passenger;
        this.charge = calculateSubscriptionCharge();
    }

    @Override
    public void subscribeToActivity() {
        validate();
        this.activity.setCapacity(this.activity.getCapacity() - 1);
        this.passenger.setBalance(this.passenger.getBalance() - this.charge);
        this.passenger.getActivities().add(new ActivitySubscription(this.activity.getId(), this.charge));
        update();
    }

    private double calculateSubscriptionCharge(){
        return this.activity.getCost();
    }

    private void validate(){
        if(activity.getCapacity() == 0){
            throw new RuntimeException();
        }

        if(passenger.getBalance() - this.charge < 0){
            throw new RuntimeException();
        }
    }

    private void update(){
        activityManager.updateActivity(this.activity);
        passengerManager.update(this.passenger);
    }

}
