import java.util.*;
public class Course {
    String code;
    String title;
    int credits;
    int labCredits;

    Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }   

    Course(String code, String title, int credits) {
        this(code, title, credits, 0);
        labCredits = 0;
    }

    int totalCredits() {
        return credits + labCredits;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter course credits: ");
        int credits = scanner.nextInt();
        Course object = new Course(code, title, credits);
        scanner.nextLine();
        System.out.print("Enter course code: ");
        String code2 = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title2 = scanner.nextLine();
        System.out.print("Enter course credits: ");
        int credits2 = scanner.nextInt();
        System.out.print("Enter lab credits: ");
        int labCredits2 = scanner.nextInt();
        Course object2 = new Course(code2, title2, credits2, labCredits2);
        System.out.println("Total credits for " + object.title + ": " + object.totalCredits());
        System.out.println("Total credits for " + object2.title + ": " + object2.totalCredits());
    }
}
