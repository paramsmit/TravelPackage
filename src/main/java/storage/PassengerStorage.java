package storage;

import data.Passenger;
import java.util.HashMap;
import java.util.Map;

public class PassengerStorage {

    final static Map<String, Passenger> idToPassenger = new HashMap<>();

    public static Passenger get(String id){
        if(idToPassenger.containsKey(id)){
            return idToPassenger.get(id);
        }
        throw new RuntimeException("record not found");
    }

    public static void save(Passenger passenger){
        idToPassenger.put(passenger.getId(), passenger);
    }

}
