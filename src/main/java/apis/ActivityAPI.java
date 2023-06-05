package apis;

import data.Destination;
import managers.ActivityManager;
import managers.DestinationManager;

public class ActivityAPI {

    final DestinationManager destinationManager;
    final ActivityManager activityManager;

    public ActivityAPI(ActivityManager activityManager, DestinationManager destinationManager){
        this.activityManager = activityManager;
        this.destinationManager = destinationManager;
    }


    public String createActivityWithDestination(String name, String description, double cost, int capacity, String destinationID){

        // validations
        if(name == null || description == null || cost < 0 || capacity <= 0 || destinationID == null){
            throw new IllegalArgumentException();
        }

        Destination destination = destinationManager.getDestinationById(destinationID);
        String activityID = activityManager.createActivityWithDestination(name, description, cost, capacity, destinationID);
        destination.getActivities().add(activityID);
        destinationManager.updateDestination(destination);
        return activityID;
    }


}

