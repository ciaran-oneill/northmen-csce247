package DegreeEZ;
import java.util.Scanner;

public class DegreeWorksUI {
    private Scanner scanner;
    private DegreeWorksApplication application;

    public DegreeWorksUI() {
        this.scanner = new Scanner(System.in);
        this.application = new DegreeWorksApplication();
    }

    public void run() {
        // TODO
    }

    private void displayMenu() {
        System.out.println("Welcome to DegreeEZ");
        System.out.println("********* Log In *********");
        System.out.println("1. Log In");
        System.out.println("2. Create Account");
    }

    private int getUserChoice() {
        int choice = 0;
        while (choice < 1 || choice > 2) {
            // TODO
        }
        return choice;
    }

    public static void main(String[] args) {
        // TODO
    }
}
