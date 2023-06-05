package data;

import java.util.HashSet;
import java.util.Set;

public class TravelPackage {
    final String id;
    final String name;
    int capacity;
    final Set<String> destinations;
    final Set<String> passengers;

    public TravelPackage(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.destinations = new HashSet<>();
        this.passengers = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<String> getDestinations() {
        return destinations;
    }

    public Set<String> getPassengers() {
        return passengers;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


}
