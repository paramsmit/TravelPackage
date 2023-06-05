package managers;

import data.Destination;
import storage.DestinationStorage;
import util.IDGenerator;

public class DestinationManager {
    public String createDestination(String name){
        Destination destination = new Destination(IDGenerator.getRandomId(), name);
        DestinationStorage.save(destination);
        return destination.getId();
    }

    public Destination getDestinationById(String id){
        return DestinationStorage.get(id);
    }

    public void updateDestination(Destination destination){
        DestinationStorage.save(destination);
    }
}
