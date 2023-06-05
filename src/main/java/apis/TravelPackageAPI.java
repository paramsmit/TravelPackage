package apis;

import managers.TravelPackageManager;

public class TravelPackageAPI {

    final TravelPackageManager travelPackageManager = new TravelPackageManager();

    public String createTravelPackage(String name, int capacity){

        // validations
        if(name == null || capacity <= 0){
            throw new IllegalArgumentException("arguments provided are illegal");
        }

        return travelPackageManager.createTravelPackage(name, capacity);
    }

}
