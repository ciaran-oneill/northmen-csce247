import java.util.ArrayList;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;
    //private int UUID;

    public UserList() {
        users = DataLoader.getUserList();
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
        if(!haveUser(userName)) return null;

        return new User(userName, "amy", "smith", "password");

    }
}
