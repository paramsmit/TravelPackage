package apis;

import data.Destination;
import data.TravelPackage;
import managers.DestinationManager;
import managers.TravelPackageManager;

public class DestinationAPI {

    final DestinationManager destinationManager;
    final TravelPackageManager travelPackageManager;

    public DestinationAPI(DestinationManager destinationManager, TravelPackageManager travelPackageManager){
        this.destinationManager = destinationManager;
        this.travelPackageManager = travelPackageManager;
    }

    public String addDestination(String name){
       return destinationManager.createDestination(name);
    }

    public void addDestinationToTravelPackage(String destinationID, String travelPackageId){

        if(destinationID == null || travelPackageId == null || destinationID.isBlank() || travelPackageId.isBlank()){
            throw new IllegalArgumentException();
        }

        Destination destination = destinationManager.getDestinationById(destinationID);
        TravelPackage travelPackage = travelPackageManager.getTravelPackageByID(travelPackageId);

        if(travelPackage.getDestinations().contains(destination.getId())){
            throw new IllegalArgumentException("destination already exist in the travel package");
        }

        travelPackage.getDestinations().add(destination.getId());
        travelPackageManager.updateTravelPackage(travelPackage);

    }
}
