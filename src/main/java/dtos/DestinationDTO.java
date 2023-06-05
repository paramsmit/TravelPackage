package dtos;

import java.util.HashSet;
import java.util.Set;

public class DestinationDTO {
    final String name;
    final Set<ActivityDTO> activities;

    public DestinationDTO(String name) {
        this.name = name;
        this.activities = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<ActivityDTO> getActivities() {
        return activities;
    }
}
