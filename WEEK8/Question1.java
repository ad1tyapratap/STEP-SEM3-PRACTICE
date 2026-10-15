
abstract class Vehicle {
    protected String name;
    protected boolean available = true;

    Vehicle(String name) {
        this.name = name;
    }

    abstract double calculateCharge(int days);

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }
}

class Sedan extends Vehicle {
    Sedan(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 50.0;
    }
}

class SUV extends Vehicle {
    SUV(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 80.0;
    }
}

class Truck extends Vehicle {
    Truck(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 120.0;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    private boolean active;

    Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.active = true;
        vehicle.available = false;
    }

    void returnVehicle(Customer customer) {
        if (active && this.customer == customer) {
            vehicle.available = true;
            active = false;
            System.out.println(vehicle.getName()
                    + " returned by " + customer.name);
        } else {
            System.out.println("Invalid return request.");
        }
    }

    void showCharge() {
        System.out.println("Rental charge: $"
                + vehicle.calculateCharge(days));
    }
}

class RentalSystem {
    Rental rentVehicle(Vehicle vehicle, Customer customer, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getName()
                    + " is currently unavailable.");
            return null;
        }

        if (days <= 0) {
            System.out.println("Rental days must be positive.");
            return null;
        }

        Rental rental = new Rental(vehicle, customer, days);
        System.out.println(vehicle.getName()
                + " rented successfully by " + customer.name);
        rental.showCharge();
        return rental;
    }
}

public class Question1 {
    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();

        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Rental rental1 = system.rentVehicle(sedan, c1, 3);

        system.rentVehicle(sedan, c2, 2);

        rental1.returnVehicle(c1);

        system.rentVehicle(suv, c3, 5);
    }
}
