package dtos;
import java.util.HashSet;
import java.util.Set;

public class ItineraryDetailsDTO {

    final String packageName;
    final Set<DestinationDTO> destinations;

    public ItineraryDetailsDTO(String packageName) {
        this.packageName = packageName;
        this.destinations = new HashSet<>();
    }

    public String getPackageName() {
        return packageName;
    }

    public Set<DestinationDTO> getDestinations() {
        return destinations;
    }

    public void print(){
        System.out.println("package name: " + this.packageName);
        for(DestinationDTO destinationDTO: this.destinations){
            System.out.println("destination name: " + destinationDTO.getName());
            for(ActivityDTO activityDTO: destinationDTO.getActivities()){
                System.out.println("activity name: " + activityDTO.getName());
                System.out.println("activity description: " + activityDTO.getDescription());
                System.out.println("activity capacity: " + activityDTO.getCapacity());
                System.out.println("activity cost: " + activityDTO.getCost());
            }
        }
        System.out.println("**************************************************************************************************");
    }
}
