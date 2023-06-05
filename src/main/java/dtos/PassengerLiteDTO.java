package dtos;

public class PassengerLiteDTO {
    final String name;
    final int number;

    public PassengerLiteDTO(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

}
