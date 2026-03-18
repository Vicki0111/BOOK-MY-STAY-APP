import java.util.*;

class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

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

    public void bookRoom(String roomType) {
        if (isAvailable(roomType)) {
            rooms.put(roomType, rooms.get(roomType) - 1);
        }
    }
}

class RoomAllocationService {
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        if (inventory.isAvailable(reservation.getRoomType())) {
            inventory.bookRoom(reservation.getRoomType());
            System.out.println("Allocated " + reservation.getRoomType() +
                    " to " + reservation.getGuestName());
        } else {
            System.out.println("No rooms available for " + reservation.getRoomType() +
                    " for " + reservation.getGuestName());
        }
    }
}

class ConcurrentBookingProcessor implements Runnable {
    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService
    ) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {
        while (true) {
            Reservation reservation;

            synchronized (bookingQueue) {
                if (bookingQueue.isEmpty()) {
                    break;
                }
                reservation = bookingQueue.getNextRequest();
            }

            if (reservation == null) continue;

            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        bookingQueue.addRequest(new Reservation("R1", "Vignesh", "Standard"));
        bookingQueue.addRequest(new Reservation("R2", "Arun", "Deluxe"));
        bookingQueue.addRequest(new Reservation("R3", "Priya", "Suite"));
        bookingQueue.addRequest(new Reservation("R4", "John", "Standard"));

        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }
    }
}