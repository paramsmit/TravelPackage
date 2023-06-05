package data;

import java.util.HashSet;
import java.util.Set;

public class Passenger {
    final String id;
    final String name;
    final int number;
    double balance;
    final PassengerType passengerType;
    Set<ActivitySubscription> activities;

    public Passenger(String id, String name, int number, double balance, PassengerType passengerType) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.balance = balance;
        this.passengerType = passengerType;
        this.activities = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }


    public double getBalance() {
        return balance;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public Set<ActivitySubscription> getActivities() {
        return activities;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
