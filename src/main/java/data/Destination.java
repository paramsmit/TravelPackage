package data;

import java.util.HashSet;
import java.util.Set;

public class Destination {
    final String id;
    final String name;
    final Set<String> activities;

    public Destination(String id, String name) {
        this.id = id;
        this.name = name;
        this.activities = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getActivities() {
        return activities;
    }

}
