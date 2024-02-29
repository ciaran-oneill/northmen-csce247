package DegreeEZ;
import java.util.ArrayList;
import java.util.UUID;

public class DegreeWorksApplication {
    private User user;
    private CourseList courseList;
    private UserList userList;
    private ArrayList<User> users;

    public DegreeWorksApplication() {
        this.users = new ArrayList<>();
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return user; // Login successful
            }
        }
        return null; // Login failed
    }

    public User createAccount(boolean isAdvisor, String firstName, String lastName, String username, String password, String major) {
        if (users.stream().anyMatch(user -> user.getUserName().equals(username))) {
            return false; // Username already exists
        }
        
        UUID uuid = UUID.randomUUID();
        User newUser;
        if (!isAdvisor) {
            newUser = new Student(uuid, firstName, lastName, username, password,UUID null, new ArrayList<Course>(), new ArrayList<Course>(), null);
            userList.add(newUser);
        } else if (isAdvisor) {
            newUser = new Advisor(uuid, firstName, lastName, username, password, new ArrayList<>());
            userList.add(newUser);
        } else {
            return false; // Invalid user type
        }
        
        users.add(newUser);
        return true; // Account creation successful
    }
    
}
