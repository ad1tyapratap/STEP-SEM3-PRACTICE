public class Student {
    String name;
    int attendance;
    static String collegeName = "SRM";
    static int totalStudents = 0;
    
    Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        totalStudents++;
    }

    static void printCollegeInfo() {
        System.out.println("College Name: " + collegeName);
        System.out.println("Total Students: " + totalStudents);
    }
    public static void main(String[] args) {
        Student s1 = new Student("Alice", 90);
        Student s2 = new Student("Bob", 85);        
        Student.printCollegeInfo();
    }
}
