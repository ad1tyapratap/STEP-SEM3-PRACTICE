import java.util.*;
public class PlacementRecord {
    String studentName;
    String company;
    double packageLpa;

    PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlacementRecord[] records = new PlacementRecord[3];
        for(int i = 0; i < 3; i++) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter company name: ");
            String company = scanner.nextLine();
            System.out.print("Enter package (LPA): ");
            double packageLpa = scanner.nextDouble();
            scanner.nextLine(); 
            records[i] = new PlacementRecord(name, company, packageLpa);
        }
        for(PlacementRecord record : records) {
            record.printRecord();
        }
    }
}
