import java.util.*;

class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 1);
        rooms.put("Suite", 1);
    }

    public boolean isAvailable(String roomType) {
        return rooms.getOrDefault(roomType, 0) > 0;
    }

    public void addRoom(String roomType) {
        rooms.put(roomType, rooms.getOrDefault(roomType, 0) + 1);
    }

    public void bookRoom(String roomType) {
        if (isAvailable(roomType)) {
            rooms.put(roomType, rooms.get(roomType) - 1);
        }
    }
}

class CancellationService {
    private Stack<String> releasedRoomIds;
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    public void cancelBooking(String reservationId, RoomInventory inventory) {
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid reservation ID");
            return;
        }

        String roomType = reservationRoomTypeMap.remove(reservationId);
        inventory.addRoom(roomType);
        releasedRoomIds.push(reservationId);

        System.out.println("Booking cancelled: " + reservationId);
    }

    public void showRollbackHistory() {
        System.out.println("Rollback History:");
        for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
            System.out.println(releasedRoomIds.get(i));
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        String res1 = "RES201";
        String res2 = "RES202";

        inventory.bookRoom("Standard");
        inventory.bookRoom("Deluxe");

        cancellationService.registerBooking(res1, "Standard");
        cancellationService.registerBooking(res2, "Deluxe");

        cancellationService.cancelBooking(res1, inventory);
        cancellationService.cancelBooking(res2, inventory);

        cancellationService.showRollbackHistory();
    }
}