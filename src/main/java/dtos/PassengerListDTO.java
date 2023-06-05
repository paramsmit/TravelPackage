package dtos;

import java.util.HashSet;
import java.util.Set;

public class PassengerListDTO {
    final String packageName;
    final int capacity;
    final int enrolledPassengers;
    final Set<PassengerLiteDTO> passengers;

    public PassengerListDTO(String packageName, int capacity, int enrolledPassengers) {
        this.packageName = packageName;
        this.capacity = capacity;
        this.enrolledPassengers = enrolledPassengers;
        this.passengers = new HashSet<>();
    }

    public String getPackageName() {
        return packageName;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledPassengers() {
        return enrolledPassengers;
    }

    public Set<PassengerLiteDTO> getPassengers() {
        return passengers;
    }

    public void print(){
        System.out.println("package name: " + packageName);
        System.out.println("capacity: " + capacity);
        System.out.println("enrolled passengers: " + enrolledPassengers);

        for(PassengerLiteDTO passengerLiteDTO: passengers){
            System.out.println("passenger name" + passengerLiteDTO.getName());
            System.out.println("passenger number" + passengerLiteDTO.getNumber());
        }

        System.out.println("************************************************************************************");
    }



}
