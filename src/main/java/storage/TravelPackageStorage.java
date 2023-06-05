package storage;

import data.TravelPackage;

import java.util.HashMap;
import java.util.Map;

public class TravelPackageStorage {

    final static Map<String, TravelPackage> idToTravelPackage = new HashMap<>();

    public static TravelPackage get(String id){
        if(idToTravelPackage.containsKey(id)){
            return idToTravelPackage.get(id);
        }
        throw new RuntimeException("record not found");
    }

    public static void save(TravelPackage travelPackage){
        idToTravelPackage.put(travelPackage.getId(),travelPackage);
        // we can create the deep copy of the object and then store it into the Map
        // this way it will be isolated from the outside codebase
    }

}
