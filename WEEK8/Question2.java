
abstract class Employee {
    protected String name;

    Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days > 0 && days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days > 0 && days <= 10;
    }
}

class Contractor extends Employee {
    Contractor(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days > 0 && days <= 5;
    }
}

class LeaveRequest {
    private Employee employee;
    private String dates;
    private int days;
    private String status = "Pending";

    LeaveRequest(Employee employee, String dates, int days) {
        this.employee = employee;
        this.dates = dates;
        this.days = days;
    }

    public void submit() {
        if (employee.canTakeLeave(days)) {
            System.out.println("Leave request submitted for "
                    + employee.getName() + " (" + dates + ").");
            System.out.println("Status: " + status);
        } else {
            System.out.println("Leave request violates leave policy.");
        }
    }

    public void review(String reviewer, boolean approved) {
        if (!status.equals("Pending")) {
            System.out.println("Request has already been reviewed.");
            return;
        }

        status = approved ? "Approved" : "Rejected";

        System.out.println(employee.getName() + "'s leave request ("
                + dates + ") " + status.toLowerCase()
                + " by " + reviewer + ".");
        System.out.println("Status: " + status);
    }

    public void changeStatus(String newStatus) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from "
                    + status + " to " + newStatus + ".");
            return;
        }

        System.out.println("Status changes must be made through review.");
    }
}

public class Question2 {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest request1 = new LeaveRequest(john, "Jan 1-5", 5);

        LeaveRequest request2 = new LeaveRequest(jane, "Feb 10-11", 2);

        request1.submit();
        request1.review("Alice", true);

        request2.submit();
        request2.review("Bob", false);

        request1.changeStatus("Pending");
    }
}
