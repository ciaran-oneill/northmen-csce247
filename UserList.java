import java.util.ArrayList;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;
    private int UUID;

    public UserList() {
        userList = UserList.getUser();
    }

    public static UserList getInstance() {
        if(userList==null) {
            userList = new UserList();
        }
        return userList;
    }
    
    public User getUser(String userName, String firstName, String lastName, String password) {
        return new User(userName, firstName, lastName, password);

    }
}
