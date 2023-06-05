package managers;

import data.Activity;
import storage.ActivityStorage;
import util.IDGenerator;

public class ActivityManager {

    public String createActivityWithDestination(String name, String description, double cost, int capacity, String destinationID){
        Activity activity = new Activity(IDGenerator.getRandomId(), name, description, cost, capacity, destinationID);
        ActivityStorage.save(activity);
        return activity.getId();
    }

    public Activity getActivityByID(String activityID){
        return ActivityStorage.get(activityID);
    }

    public void updateActivity(Activity activity){
        ActivityStorage.save(activity);
    }

}
