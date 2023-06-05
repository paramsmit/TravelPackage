package dtos;

import java.util.HashSet;
import java.util.Set;

public class PassengerDTO {
    final String name;
    final int number;
    final double balance;
    final Set<ActivityDTO> activities;

    public PassengerDTO(String name, int number, double balance) {
        this.name = name;
        this.number = number;
        this.balance = balance;
        this.activities = new HashSet<>();
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

    public Set<ActivityDTO> getActivities() {
        return activities;
    }


    public static class ActivityDTO {
        final String activityName;
        final String description;
        final double cost;
        final double amountPaid;
        final String destinationName;

        public ActivityDTO(String activityName, String description, double cost, double amountPaid, String destinationName) {
            this.activityName = activityName;
            this.description = description;
            this.cost = cost;
            this.amountPaid = amountPaid;
            this.destinationName = destinationName;
        }

        public String getActivityName() {
            return activityName;
        }

        public String getDescription() {
            return description;
        }

        public double getCost() {
            return cost;
        }

        public double getAmountPaid() {
            return amountPaid;
        }

        public String getDestinationName() {
            return destinationName;
        }
    }

    public void print(){
        System.out.println("name: " + name);
        System.out.println("number: " + number);
        System.out.println("balance: " + balance);
        for(ActivityDTO activityDTO : activities){
            System.out.println("activity name: " + activityDTO.getActivityName());
            System.out.println("activity description: " + activityDTO.getDescription());
            System.out.println("activity destination name: " + activityDTO.getDestinationName());
            System.out.println("activity cost: " + activityDTO.getCost());
            System.out.println("activity amount paid: " + activityDTO.getAmountPaid());
        }
        System.out.println("************************************************************************************");
    }
}
