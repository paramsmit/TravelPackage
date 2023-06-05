package storage;

import data.Destination;
import java.util.HashMap;
import java.util.Map;

public class DestinationStorage {

    final static Map<String, Destination> idToDestination = new HashMap<>();

    public static Destination get(String id){
        if(idToDestination.containsKey(id)){
            return idToDestination.get(id);
        }
        throw new RuntimeException("record not found");
    }

    public static void save(Destination destination){
        idToDestination.put(destination.getId(),destination);
        // we can create the deep copy of the object and then store it into the Map
        // this way it will be isolated from the outside codebase
    }

}

