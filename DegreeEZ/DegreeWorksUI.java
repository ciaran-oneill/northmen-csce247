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
                DisplayStudentMenu(); 
                /*
                 * TODO: DISPLAY MENU
                 */
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
        System.out.println("******** Main Menu ********");
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
                    DisplayDropClassesMenu();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    mainMenu();
                default:
                    System.out.println("Invalid option, please try again.");
            }
    }

    public void DisplayDegreeProgress() {
        System.out.println("******** Degree Progress ********");
        System.out.println("Compeleted Credits:" + );
        System.out.println("Total Credits Required:" + );
        System.out.println("Remaining Credits:" + );
        System.out.println("Major:" + );

        System.out.println("Completed Courses:" + );
        System.out.println("Enrolled Courses:" + );
        System.out.println("Remaining Courses:" + );
        System.out.println("Current GPA:" + );

        System.out.println("Enter B to go back:");
        /*
         * TODO: TEST THE SWITCH METHOD
         */
        String choice = scanner.nextLine();
        scanner.nextLine();
        switch (choice) {
            case "B":
                DisplayStudentMenu();
                break;
            default:
                System.out.println("Invalid option, please try again.");
        }
    }

    public void DisplayRegisterForClasses() {
        System.out.println("******** Register for Classes ********");
        System.out.println("Click the corresponding number for the class to view availability.");
        System.out.println("(LIST OF REQUIRED, NOT YET TAKEN CLASSES, FOR EXAMPLE:)");
        /*
         * TODO: DISPLAY REQUIRED CLASSES, NOT YET TAKEN CLASSES
         */
        System.out.println("Choose class number or enter 0 to go back:");
        /*
         * TODO: Switch statement?
         */
    }

    public void DisplayDropClassesMenu() {
        System.out.println("******** Drop Classes ********");
        System.out.println("List of Currently Enrolled Classes:");
        /*
         * TODO: DISPLAY CURRENTLY ENROLLED CLASSES
         */
        System.out.println("Select the course you want to drop or enter 0 to go back:");
        /*
         * TODO: Switch statement?
         */
    }

    public void DisplayClassIsAvailable() {
        System.out.println("******** Register for Classes ********");
        System.out.println("Class Name:" + );
        System.out.println("Class Subject:" + );
        System.out.println("Class Number:" + );
        System.out.println("Availability:" + );
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Register for Class");
        System.out.println("2. Back to Classes");
        System.out.println("3. Back to Main Menu");

        /*
         * TODO: TEST THE SWITCH METHOD
         */
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                /*
                 * TODO: Register for class code
                 */
                break;
            case 2:
                DisplayRegisterForClasses();
                break;
            case 3:
                DisplayStudentMenu();
                break;
            default:
                System.out.println("Invalid option, please try again.");
        } 
    }

    public void DisplayClassRegistered() {
        System.out.println("Class Registered!");
        DisplayRegisterForClasses();
    }

    public void DisplayDropClass(){
        System.out.println("******** Drop Classes ********");
        System.out.println("Are you sure you want to drop " + " ?");
        System.out.println("Enter Y/N?");
        
        String choice = scanner.nextLine();
        scanner.nextLine();
        switch (choice) {
            case "Y":
                /*
                * TODO: Code to drop class
                */
                System.out.println( + " has been dropped!");
                DisplayDropClassesMenu();
                break;
            case "N":
                DisplayDropClassesMenu();
                break;
            default:
                System.out.println("Invalid option, please try again.");
        }   
    }

    public void DisplayAdvisorMenu() {
        System.out.println("******** Main Menu ********");
        System.out.println("1. View Student Degree Progress");
        System.out.println("2. Change Student's Major");
        System.out.println("3. Perform Student Degree Audit");
        System.out.println("4. Log Out");

        int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    DisplayStudentDegreeProgress();
                    break;
                case 2:
                    DisplayChangeStudentsMajorMenu();
                    break;
                case 3:
                    DisplayPerformStudentDegreeAuditMenu();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    mainMenu();
                default:
                    System.out.println("Invalid option, please try again.");
            }
    }

    public void DisplayStudentDegreeProgress() {
        System.out.println("******** View Student Degree Progress ********");
        System.out.println("Choose Student:");
        /*
         * TODO: SHOW ADVISOR'S STUDENTS 
         */
        System.out.println("Choose student's corresponding number:");

        int choice = scanner.nextInt();
            scanner.nextLine();
    }

    public void DisplayChangeStudentsMajorMenu() {
        System.out.println("******** Change Student's Major ********");
        System.out.println("Choose Student:");
        /*
         * TODO: SHOW ADVISOR'S STUDENTS
         */
        System.out.println("Choose student's corresponding number:");

        int choice = scanner.nextInt();
            scanner.nextLine();
    }

    public void DisplayChangeStudentsMajor() {
        System.out.println("******** Change + " firstName + " " + lastName + " Major ********");
        System.out.println("\nCurrent Major: " + ); // add a varaible to store old major
        System.out.println("New Major:");

        String newMajor = scanner.nextLine();
        scanner.nextLine();
        /*
         * TODO: Add code to check if correct major is chosen
         */

        System.out.println(firstName + " " + lastName + "'s major has been changed from" + oldMajor + " to " + newMajor);
        

    }

    public void DisplayPerformStudentDegreeAuditMenu() {
        System.out.println("******** Degree Audit ********");
        System.out.println("List of Students:");
        /*
         * TODO: SHOW ADVISOR'S STUDENTS
         */
        System.out.println("Choose student or enter 0 to go back:");
    }

    public void DisplayPerformStudentDegreeAudit() {
        System.out.println("******** Degree Audit ********");
        System.out.println("Student: " + " " +);
        System.out.println("Major: " +);
        System.out.println("\n1. Add Compeleted Course");
        System.out.println("2. Register Class");
        System.out.println("3. Add Required Course");
        System.out.println("4. Back to Students");
        System.out.println("5. Back to Main Menu");

        int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    //FILL IN
                    break;
                case 2:
                    //FILL IN
                    break;
                case 3:
                    //FILL IN
                    break;
                case 4:
                    DisplayPerformStudentDegreeAuditMenu();
                    break;
                case 5:
                    DisplayAdvisorMenu();
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
    }

    public static void main(String[] args) {
        DegreeWorksApplication app = new DegreeWorksApplication();
        DegreeWorksUI ui = new DegreeWorksUI(app);
        ui.mainMenu();
    }
}
