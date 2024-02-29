package DegreeEZ;
import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users = new ArrayList<>();

    public UserList() {
        users = DataLoader.getUsers();
    }

    public static UserList getInstance() {
        if(userList==null) {
            userList = new UserList();
        }
        return userList;
    }

    public boolean haveUser(String username) {
        return true;
    }
    
    public User getUser(String userName) {
        if(!haveUser(userName)) {
            return null;
        } else {
            return g
        }


    }

    public boolean addUser(User user) {
        // Check if a user with same username already exists
        for (User existingUser : users) {
            if (existingUser.getUserName().equals(user.getUserName())) {
                // User with same username found, do not add new user
                System.out.println("User already exists.");
                return false;
            }
        }
        // No existing user found, add user
        users.add(user);
        return true;
    }

    public User getUserByUserName(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        System.out.println("Username not found");
        return null;
    }

    public boolean usernameExists(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }
    
}
