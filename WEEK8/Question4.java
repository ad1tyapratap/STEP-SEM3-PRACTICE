
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

abstract class Room {
    protected String roomNumber;

    Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    abstract double calculatePrice(long nights);
}

class StandardRoom extends Room {
    StandardRoom(String number) {
        super(number);
    }

    double calculatePrice(long nights) {
        return nights * 100.0;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String number) {
        super(number);
    }

    double calculatePrice(long nights) {
        return nights * 180.0;
    }
}

class Suite extends Room {
    Suite(String number) {
        super(number);
    }

    double calculatePrice(long nights) {
        return nights * 300.0;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {
    Room room;
    Customer customer;
    LocalDate start;
    LocalDate end;
    LocalDate cancellationDeadline;
    boolean active = true;

    Reservation(Room room, Customer customer,
                LocalDate start, LocalDate end,
                LocalDate cancellationDeadline) {
        this.room = room;
        this.customer = customer;
        this.start = start;
        this.end = end;
        this.cancellationDeadline = cancellationDeadline;
    }

    boolean overlaps(LocalDate newStart, LocalDate newEnd) {
        return active && start.isBefore(newEnd)
                && end.isAfter(newStart);
    }

    void cancel(LocalDate today) {
        if (!active) {
            System.out.println("Reservation is already inactive.");
        } else if (today.isAfter(cancellationDeadline)) {
            System.out.println("Cancellation deadline has passed.");
        } else {
            active = false;
            System.out.println("Reservation for " + customer.name
                    + ", Room " + room.getRoomNumber()
                    + " cancelled successfully.");
        }
    }
}

class Hotel {
    private List<Reservation> reservations = new ArrayList<>();

    boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        if (!start.isBefore(end)) {
            return false;
        }

        for (Reservation r : reservations) {
            if (r.room == room && r.overlaps(start, end)) {
                return false;
            }
        }

        return true;
    }

    Reservation book(Room room, Customer customer,
                     LocalDate start, LocalDate end,
                     LocalDate cancellationDeadline) {
        if (!isAvailable(room, start, end)) {
            System.out.println("Room " + room.getRoomNumber()
                    + " is not available from " + start + " to " + end);
            return null;
        }

        Reservation reservation = new Reservation(
                room, customer, start, end, cancellationDeadline);

        reservations.add(reservation);

        long nights = ChronoUnit.DAYS.between(start, end);

        System.out.println("Reservation confirmed for " + customer.name
                + ", Room " + room.getRoomNumber()
                + " (" + start + " to " + end + ").");

        System.out.println("Price: $"
                + room.calculatePrice(nights));

        return reservation;
    }
}

public class Question4 {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        Room standard = new StandardRoom("101");
        Room deluxe = new DeluxeRoom("201");

        LocalDate jan1 = LocalDate.of(2027, 1, 1);
        LocalDate jan5 = LocalDate.of(2027, 1, 5);
        LocalDate jan3 = LocalDate.of(2027, 1, 3);
        LocalDate jan7 = LocalDate.of(2027, 1, 7);

        System.out.println("Room 101 available: "
                + hotel.isAvailable(standard, jan1, jan5));

        Reservation r = hotel.book(
                standard, a, jan1, jan5,
                LocalDate.of(2026, 12, 25));

        hotel.book(standard, b, jan3, jan7,
                LocalDate.of(2026, 12, 25));

        if (r != null) {
            r.cancel(LocalDate.of(2026, 12, 20));
        }

        hotel.book(
                deluxe, c,
                LocalDate.of(2027, 2, 10),
                LocalDate.of(2027, 2, 12),
                LocalDate.of(2027, 2, 1));
    }
}
