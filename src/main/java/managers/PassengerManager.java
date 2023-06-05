package managers;

import data.Passenger;
import data.PassengerType;
import storage.PassengerStorage;
import util.IDGenerator;

public class PassengerManager {
    public String createPassenger(String name, double balance, PassengerType passengerType, int number){
        Passenger passenger = new Passenger(IDGenerator.getRandomId(), name, number, balance, passengerType);
        PassengerStorage.save(passenger);
        return passenger.getId();
    }

    public Passenger getPassengerByID(String passengerID){
        return PassengerStorage.get(passengerID);
    }

    public void update(Passenger passenger){
        PassengerStorage.save(passenger);
    }

}
