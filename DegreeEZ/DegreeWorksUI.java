package DegreeEZ;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class DegreeWorksUI {
    private Scanner scanner;
    private DegreeWorksApplication application;

    public DegreeWorksUI(DegreeWorksApplication app) {
        this.application = app;
        this.scanner = new Scanner(System.in);
    }

    public void mainMenu() {
        while (true) {
            if (application.getUser() == null) {
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
            } else if (!application.getUser().isAdvisor()) {
                DisplayStudentMenu();
            } else {
                DisplayAdvisorMenu();
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = application.login(username,password);
        if (user != null) {
            System.out.println("Login successful!");
            if (user.isAdvisor()) {
                DisplayAdvisorMenu();
            } else {
                DisplayStudentMenu();
            }
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
        Major major = null;
        if (!isAdvisor) {
            System.out.print("Enter your major: ");
            do {
                major = MajorList.getMajorByName(scanner.nextLine());
                if (major == null) {
                    System.err.println("Invalid major!");
                }
            } while (major == null);
        }
    
        User user = application.createAccount(isAdvisor, firstName, lastName, username, password, major);
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
                    application.logout();
                    mainMenu();
                default:
                    System.out.println("Invalid option, please try again.");
            }
    }

    public void DisplayDegreeProgress() {
        Student student = (Student) application.getUser();
        int completedCredits = student.getCompletedCredits();
        int totalRequiredCredits = student.getTotalRequiredCredits();
        System.out.println("********Degree Progress********");
        System.out.println("Compeleted Credits: " + completedCredits);
        System.out.println("Total Credits Required:" + totalRequiredCredits);
        System.out.println("Remaining Credits:" + (totalRequiredCredits - completedCredits));
        System.out.println("Major:" + student.getMajor().getMajorName());

        System.out.println("Completed Courses:");
        for (CompletedCourse c : student.getCompletedCourses()) {
            System.out.printf("\tCourse: %s\n Grade: %s\n\n", c.getCourse(), c.getGrade());
        }

        System.out.println("Enrolled Courses:");

        for (Course c : student.getEnrolledCourses()) {
            System.out.println("\t" + c);
        }

        System.out.println("Remaining Courses:");
        for (Course c : student.getMajor().getRequiredCourses()) {
            if (student.courseNeededInFuture(c)) {
                System.out.println("\t" + c);
            }
        }

        System.out.println("Current GPA:" + 0.0);

        System.out.println("Enter B to go back:");
    }

    public void DisplayRegisterForClasses() {
        Student student = (Student) application.getUser();
        ArrayList<Course> availableCourses = new ArrayList<>();
        ArrayList<Course> unvailableCourses = new ArrayList<>();
        for (Course c : CourseList.getCourses()) {
            if (c.prerequisitesSatisfied(student.getCompletedCourses())) {
                availableCourses.add(c);
            }
        }
        System.out.println("Available Courses: (Select the number to register)");
        for (int i = 0; i < availableCourses.size(); i++) {
            System.out.printf("%d) %s\n", i+1, availableCourses.get(i));
        }
        System.out.println("%d) Show unavailable courses");
        int selectedCourse = scanner.nextInt();
        if (selectedCourse < 0 || selectedCourse > availableCourses.size() + 1) {
            System.out.println("Invalid course choice");
        } else if (selectedCourse == availableCourses.size() + 1) {
            for (Course c : unvailableCourses) {
                System.out.println("\t"+c.getName());
            }
        } else {
            Course c = availableCourses.get(selectedCourse - 1);
            student.getEnrolledCourses().add(c);
            System.out.println("Sucessfully Enrolled in " + c.getName());
        }

    }

    public void DisplayDropClasses() {
        Student student = (Student) application.getUser();
        System.out.println("Select a class to drop:");
        for (int i = 0; i < student.getEnrolledCourses().size(); i++) {
            System.out.printf("%d) %s\n", i+1, student.getEnrolledCourses().get(i).getName());
        }
        int dropindex = scanner.nextInt() - 1;
        if (dropindex < 0 || dropindex > student.getEnrolledCourses().size()) {
            System.out.println("Invalid choice of class to drop");
        } else {
            Course c = student.getEnrolledClasses().remove(dropindex);
            System.out.println("Dropped " + c);
        }
    }

    public void DisplayAdvisorMenu() {
        Advisor advisor = (Advisor) application.getUser();
        System.out.println("1) View Students");
        System.out.println("2) Edit Student");
        System.out.println("3) Logout");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                advisor.viewStudents();
                break;
            case 2:
                advisor.viewStudents();
                System.out.println("Select the student to audit");
                int studentIndex = scanner.nextInt() - 1;
                try {
                    advisor.performDegreeAudit(advisor.getStudents().get(studentIndex), scanner);
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("Invalid choice for student.");
                }
                break;
            case 3:
                System.out.println("Logging out...");
                application.logout();
                mainMenu();
                break;
            default:
                System.out.println("Invalid option, please try again.");
        }
    }

    public static void main(String[] args) {
        CourseList.getInstance();
        MajorList.getInstance();
        StudentList.getInstance();
        AdvisorList.getInstance();

        DegreeWorksApplication app = new DegreeWorksApplication();
        DegreeWorksUI ui = new DegreeWorksUI(app);
        ui.mainMenu();
    }
}
