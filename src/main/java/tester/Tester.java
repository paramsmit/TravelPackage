package tester;

import apis.*;
import data.Activity;
import data.PassengerType;
import dtos.ActivityDTO;
import dtos.ItineraryDetailsDTO;
import dtos.PassengerDTO;
import dtos.PassengerListDTO;
import managers.ActivityManager;
import managers.DestinationManager;
import managers.PassengerManager;
import managers.TravelPackageManager;

import java.util.Set;

public class Tester {

    public static void main(String[] args){

        TravelPackageManager travelPackageManager = new TravelPackageManager();
        DestinationManager destinationManager = new DestinationManager();
        ActivityManager activityManager = new ActivityManager();
        PassengerManager passengerManager = new PassengerManager();

        TravelPackageAPI travelPackageAPI = new TravelPackageAPI();
        DestinationAPI destinationAPI = new DestinationAPI(destinationManager, travelPackageManager);
        PassengerAPI passengerAPI = new PassengerAPI(travelPackageManager, passengerManager, activityManager);
        ActivityAPI activityAPI = new ActivityAPI(activityManager, destinationManager);
        TravelPackageDetailsAPI travelPackageDetailsAPI = new TravelPackageDetailsAPI();


        // create travelPackage
        String travelPackageId1 = travelPackageAPI.createTravelPackage("travelPackage1", 10);

        // create two destinations
        String destinationId1 = destinationAPI.addDestination("destination1");
        String destinationId2 = destinationAPI.addDestination("destination2");

        // add destinations to travel package
        destinationAPI.addDestinationToTravelPackage(destinationId1, travelPackageId1);
        destinationAPI.addDestinationToTravelPackage(destinationId2, travelPackageId1);

        // create activities for destinations
        String activityId1 = activityAPI.createActivityWithDestination("activity1", "description", 10, 1, destinationId1);
        String activityId2 = activityAPI.createActivityWithDestination("activity2", "description", 5, 1, destinationId1);
        String activityId3 = activityAPI.createActivityWithDestination("activity3", "description", 10, 3, destinationId2);
        String activityId4 = activityAPI.createActivityWithDestination("activity4", "description", 5, 3, destinationId2);


        // create passengers
        String passengerId1 = passengerAPI.createPassengerWithTravelPackage("passenger1", 123, PassengerType.STANDARD, 20, travelPackageId1);
        passengerAPI.addActivityToPassenger(passengerId1, activityId1);
        passengerAPI.addActivityToPassenger(passengerId1, activityId2);

        String passengerId2 = passengerAPI.createPassengerWithTravelPackage("passenger2", 123, PassengerType.GOLD, 20, travelPackageId1);
        passengerAPI.addActivityToPassenger(passengerId2, activityId3);
        passengerAPI.addActivityToPassenger(passengerId2, activityId4);

        ItineraryDetailsDTO itineraryDetailsDTO = travelPackageDetailsAPI.getItineraryDetails(travelPackageId1);
        itineraryDetailsDTO.print();

        PassengerListDTO passengerListDTO = travelPackageDetailsAPI.getPassengerList(travelPackageId1);
        passengerListDTO.print();

        PassengerDTO passengerDTO =  travelPackageDetailsAPI.getPassengerDetails(passengerId1);
        passengerDTO.print();

        Set<ActivityDTO> activityDTOS = travelPackageDetailsAPI.getActivitiesWithSpacesAvailable(travelPackageId1);
        for(ActivityDTO activityDTO: activityDTOS){
            activityDTO.print();
        }

    }
}
