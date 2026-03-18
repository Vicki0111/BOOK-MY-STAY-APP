import java.util.HashMap;
import java.util.Map;

class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int change) {
        int current = inventory.getOrDefault(roomType, 0);
        int updated = current + change;

        if (updated < 0) {
            System.out.println("Error: Not enough rooms available for " + roomType);
        } else {
            inventory.put(roomType, updated);
        }
    }

    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.addRoomType("Single Room", 10);
        inventory.addRoomType("Double Room", 5);
        inventory.addRoomType("Deluxe Room", 3);

        inventory.displayInventory();

        System.out.println("\nAvailable Single Rooms: " +
                inventory.getAvailability("Single Room"));

        System.out.println("\nBooking 2 Single Rooms...");
        inventory.updateAvailability("Single Room", -2);

        System.out.println("Adding 1 Deluxe Room...");
        inventory.updateAvailability("Deluxe Room", 1);

        inventory.displayInventory();
    }
}