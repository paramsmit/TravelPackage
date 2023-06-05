package managers;

import data.TravelPackage;
import storage.TravelPackageStorage;
import util.IDGenerator;

public class TravelPackageManager {
    public String createTravelPackage(String name, int capacity){
        TravelPackage travelPackage = new TravelPackage(IDGenerator.getRandomId(), name, capacity);
        TravelPackageStorage.save(travelPackage);
        return travelPackage.getId();
    }

    public TravelPackage getTravelPackageByID(String id){
        return TravelPackageStorage.get(id);
    }

    public void updateTravelPackage(TravelPackage travelPackage){
        TravelPackageStorage.save(travelPackage);
    }
}
