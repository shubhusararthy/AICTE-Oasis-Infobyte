import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {
        initializeUsers();
        if (login()) {
            showMenu();
        } else {
            System.out.println("Login failed. Exiting program.");
        }
    }

    private static void initializeUsers() {
        users.add(new User("user1", "1234", 1000.0));
        users.add(new User("user2", "5678", 500.0));
    }

    private static boolean login() {
        System.out.println("Welcome to the ATM System");
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getPin().equals(pin)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    private static void showMenu() {
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : currentUser.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    private static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && currentUser.withdraw(amount)) {
            System.out.println("Withdrawal successful. Your new balance is " + currentUser.getBalance());
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    private static void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            currentUser.deposit(amount);
            System.out.println("Deposit successful. Your new balance is " + currentUser.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private static void transfer() {
        System.out.print("Enter recipient user ID: ");
        scanner.nextLine();
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        User recipient = null;
        for (User user : users) {
            if (user.getUserId().equals(recipientId)) {
                recipient = user;
                break;
            }
        }

        if (recipient != null && amount > 0 && currentUser.withdraw(amount)) {
            recipient.deposit(amount);
            System.out.println("Transfer successful. Your new balance is " + currentUser.getBalance());
        } else {
            System.out.println("Transfer failed. Insufficient funds, invalid amount, or recipient not found.");
        }
    }
}
