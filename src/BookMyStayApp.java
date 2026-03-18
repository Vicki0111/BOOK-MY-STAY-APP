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

class BookingHistory {
    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

class BookingReportService {
    public void generateReport(BookingHistory history) {
        List<Reservation> reservations = history.getConfirmedReservations();

        System.out.println("----- Booking Report -----");
        for (Reservation r : reservations) {
            System.out.println("Reservation ID: " + r.getReservationId());
            System.out.println("Guest Name: " + r.getGuestName());
            System.out.println("Room Type: " + r.getRoomType());
            System.out.println("--------------------------");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("RES101", "Vignesh", "Deluxe");
        Reservation r2 = new Reservation("RES102", "Arun", "Suite");
        Reservation r3 = new Reservation("RES103", "Priya", "Standard");

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}