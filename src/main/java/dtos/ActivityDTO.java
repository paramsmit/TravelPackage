package dtos;

public class ActivityDTO {
    final String name;
    final String description;
    double cost;
    int capacity;

    public ActivityDTO(String name, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void print(){
        System.out.println("name: " + name);
        System.out.println("description: " + description);
        System.out.println("cost: " + cost);
        System.out.println("capacity: " + capacity);
    }
}
