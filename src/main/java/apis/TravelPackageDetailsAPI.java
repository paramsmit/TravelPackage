package apis;

import data.*;
import dtos.*;
import managers.ActivityManager;
import managers.DestinationManager;
import managers.PassengerManager;
import managers.TravelPackageManager;

import java.util.HashSet;
import java.util.Set;

public class TravelPackageDetailsAPI {

    final TravelPackageManager travelPackageManager = new TravelPackageManager();
    final DestinationManager destinationManager = new DestinationManager();
    final ActivityManager activityManager = new ActivityManager();
    final PassengerManager passengerManager = new PassengerManager();

    public ItineraryDetailsDTO getItineraryDetails(String travelPackageID){

        TravelPackage travelPackage = travelPackageManager.getTravelPackageByID(travelPackageID);

        ItineraryDetailsDTO itineraryDetails = new ItineraryDetailsDTO(travelPackage.getName());

        Set<DestinationDTO> destinationDTOS = itineraryDetails.getDestinations();

        for(String destinationID: travelPackage.getDestinations()){

            Destination destination = destinationManager.getDestinationById(destinationID);
            DestinationDTO destinationDTO = new DestinationDTO(destination.getName());

            Set<ActivityDTO> activityDTOS = destinationDTO.getActivities();

            for(String activityID: destination.getActivities()){
                Activity activity = activityManager.getActivityByID(activityID);
                ActivityDTO activityDTO = new ActivityDTO(activity.getName(), activity.getDescription(), activity.getCost(), activity.getCapacity());
                activityDTOS.add(activityDTO);
            }

            destinationDTOS.add(destinationDTO);
        }

        return itineraryDetails;

    }

    public PassengerListDTO getPassengerList(String travelPackageID){

        TravelPackage travelPackage = travelPackageManager.getTravelPackageByID(travelPackageID);
        PassengerListDTO passengerListDTO = new PassengerListDTO(travelPackage.getName(), travelPackage.getCapacity(), travelPackage.getPassengers().size());

        Set<PassengerLiteDTO> passengerLiteDTOS = passengerListDTO.getPassengers();

        for(String passengerID: travelPackage.getPassengers()){
            Passenger passenger = passengerManager.getPassengerByID(passengerID);
            PassengerLiteDTO passengerLiteDTO = new PassengerLiteDTO(passenger.getName(), passenger.getNumber());
            passengerLiteDTOS.add(passengerLiteDTO);
        }

        return passengerListDTO;
    }

    public PassengerDTO getPassengerDetails(String passengerID){

        Passenger passenger = passengerManager.getPassengerByID(passengerID);

        PassengerDTO passengerDTO = new PassengerDTO(passenger.getName(), passenger.getNumber(), passenger.getBalance());

        Set<PassengerDTO.ActivityDTO> activityDTOS = passengerDTO.getActivities();

        for(ActivitySubscription activitySubscription: passenger.getActivities()){
            Activity activity = activityManager.getActivityByID(activitySubscription.getActivityID());
            Destination destination = destinationManager.getDestinationById(activity.getDestinationID());

            PassengerDTO.ActivityDTO activityDTO = new PassengerDTO.ActivityDTO(activity.getName(), activity.getDescription(), activity.getCost(), activitySubscription.getAmountPaid(), destination.getName());
            activityDTOS.add(activityDTO);
        }

        return passengerDTO;
    }

    public Set<ActivityDTO> getActivitiesWithSpacesAvailable(String travelPackageID){

        TravelPackage travelPackage = travelPackageManager.getTravelPackageByID(travelPackageID);
        Set<ActivityDTO> activityDTOS = new HashSet<>();

        for(String destinationID: travelPackage.getDestinations()){
            Destination destination = destinationManager.getDestinationById(destinationID);

            for(String activityID: destination.getActivities()){

                Activity activity = activityManager.getActivityByID(activityID);

                if(activity.getCapacity() > 0){
                    ActivityDTO activityDTO = new ActivityDTO(activity.getName(), activity.getDescription(), activity.getCost(), activity.getCapacity());
                    activityDTOS.add(activityDTO);
                }
            }
        }

        return activityDTOS;

    }

}
