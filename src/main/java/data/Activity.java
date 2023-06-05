package data;

public class Activity {
    final String id;
    final String name;
    final String description;
    double cost;
    int capacity;
    final String destinationID;

    public Activity(String id, String name, String description, double cost, int capacity, String destinationID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destinationID = destinationID;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getDescription() {
        return description;
    }
    public double getCost() {
        return cost;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getDestinationID() {
        return destinationID;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
