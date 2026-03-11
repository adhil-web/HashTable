import java.util.*;

public class ParkingLotSystem {

    static class ParkingSpot {
        String licensePlate;
        boolean occupied;

        ParkingSpot() {
            this.occupied = false;
        }
    }

    private ParkingSpot[] table;
    private int size;

    public ParkingLotSystem(int size) {
        this.size = size;
        table = new ParkingSpot[size];

        for (int i = 0; i < size; i++) {
            table[i] = new ParkingSpot();
        }
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    public void parkVehicle(String plate) {

        int index = hash(plate);
        int startIndex = index;

        while (table[index].occupied) {

            index = (index + 1) % size;

            if (index == startIndex) {
                System.out.println("Parking lot full!");
                return;
            }
        }

        table[index].licensePlate = plate;
        table[index].occupied = true;

        System.out.println(plate + " parked at spot #" + index);
    }

    public void exitVehicle(String plate) {

        int index = hash(plate);
        int startIndex = index;

        while (table[index].occupied) {

            if (plate.equals(table[index].licensePlate)) {

                table[index].occupied = false;
                table[index].licensePlate = null;

                System.out.println(plate + " exited from spot #" + index);
                return;
            }

            index = (index + 1) % size;

            if (index == startIndex)
                break;
        }

        System.out.println("Vehicle not found.");
    }

    public static void main(String[] args) {

        ParkingLotSystem lot = new ParkingLotSystem(10);

        lot.parkVehicle("ABC1234");
        lot.parkVehicle("XYZ9999");
        lot.parkVehicle("CAR5678");

        lot.exitVehicle("XYZ9999");
    }
}