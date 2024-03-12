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
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = app.login(username,password);
        if (user != null) {
            System.out.println("Login successful!");
            // DISPLAY MENU AFTER
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
    }

    private void createAccount() {
        System.out.println("Are you an advisor or student? (A = Advisor | S = Student)");
        boolean isAdvisor = false;
        String choice = scanner.nextLine(); // Reads user input once
        if (choice.equals("S")) {
            isAdvisor = false;
        } else if (choice.equals("A")) {
            isAdvisor = true;
        } else {
            System.out.println("Incorrect choice.");
            return; // Exit method if choice is incorrect
        }
    
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Only ask for major if it's a student
        String major = "";
        if (!isAdvisor) {
            System.out.print("Enter your major: ");
            major = scanner.nextLine();
            System.out.println(major);
            if (major.equals("Computer Science")) {
                DisplayStudentMenu(); // TODO: DISPLAY MENU
            } else {
                System.out.println("Incorrect major, exiting program!");
                System.exit(0);
            }
        }
    
        User user = app.createAccount(isAdvisor, firstName, lastName, username, password, major);
        if (user != null) {
            System.out.println("Account created successfully. Please login.");
        } else {
            System.out.println("Failed to create account.");
        }
    }

    public void DisplayStudentMenu() {
        System.out.println("********Main Menu********");
        System.out.println("1. View Degree Progress");
        System.out.println("2. Register For Classes");
        System.out.println("3. Drop Classes");
        System.out.println("4. Log Out");

        int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    DisplayDegreeProgress();
                    break;
                case 2:
                    DisplayRegisterForClasses();
                    break;
                case 3:
                    DisplayDropClasses();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    mainMenu();
                default:
                    System.out.println("Invalid option, please try again.");
            }
    }

    public void DisplayDegreeProgress() {
        System.out.println("********Degree Progress********");
        System.out.println("Compeleted Credits:" + );
        System.out.println("Total Credits Required:" + );
        System.out.println("Remaining Credits:" + );
        System.out.println("Major:" + );

        System.out.println("Completed Courses:");

        System.out.println("Enrolled Courses:");

        System.out.println("Remaining Courses:");

        System.out.println("Current GPA:" +);

        System.out.println("Enter B to go back:");
    }

    public void DisplayRegisterForClasses() {
        // TODO
    }

    public void DisplayDropClasses() {
        // TODO;
    }

    public static void main(String[] args) {
        DegreeWorksApplication app = new DegreeWorksApplication();
        DegreeWorksUI ui = new DegreeWorksUI(app);
        ui.mainMenu();
    }
}
