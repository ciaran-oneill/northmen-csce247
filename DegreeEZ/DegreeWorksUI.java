package DegreeEZ;
import java.util.Scanner;

public class DegreeWorksUI {
    private Scanner scanner;
    private DegreeWorksApplication app;

    public DegreeWorksUI(DegreeWorksApplication app) {
        this.app = app;
        this.scanner = new Scanner(System.in);

    }

    public void mainMenu() {
        while (true) {
            System.out.println("Welcome to DegreeEZ");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }

    private void login() {
        System.out.print("Enter username :");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = app.login(username,password);
        if (user != null) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please check your credentials.")
        }
    }

    private void createAccount() {
        System.out.println("Are you an advisor or student? (A=Advisor | S=Student)");
        boolean isAdvisor = false;
        if (scanner.nextLine().equals("S")) {
            isAdvisor = false;
        } else if (scanner.nextLine().equals("A")) {
            isAdvisor = true;
        } else {
            System.out.println("Incorrect choice.");
        }
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your major:");
        String major = scanner.nextLine();
        // TODO: Ensure that the major is in the major list, and convert to UUID
       

        User user = app.createAccount(isAdvisor, firstName, lastName, username, password, major);
        if (user != null) {
            System.out.println("Account created successfully. Please login.");
        } else {
            System.out.println("Failed to create account.");
        }
    }

    public static void main(String[] args) {
        DegreeWorksApplication app = new DegreeWorksApplication();
        DegreeWorksUI ui = new DegreeWorksUI(app);
        ui.mainMenu();
    }

}
