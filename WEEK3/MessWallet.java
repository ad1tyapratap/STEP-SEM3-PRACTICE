import java.util.*;
public class MessWallet {
    private double balance;

    MessWallet(double initialBalance) {
        this.balance = initialBalance;
    }

    void topUp(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Balance after top-up: " + balance);
        } else {
            System.out.println("Invalid top-up amount.");
        }
    }

    void deduct(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Balance after deduction: " + balance);
        } else {
            System.out.println("Deduct Rejected: insufficient balance.");
            System.out.println("Final balance: " + balance);
        }
    }
    double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        MessWallet wallet = new MessWallet(initialBalance);

        System.out.print("Enter top-up amount: ");
        double topUpAmount = scanner.nextDouble();
        wallet.topUp(topUpAmount);

        System.out.print("Enter deduction amount: ");
        double deductionAmount = scanner.nextDouble();
        wallet.deduct(deductionAmount);
    }
}
