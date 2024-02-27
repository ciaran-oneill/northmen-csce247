package DegreeEZ;
import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;
    //private int UUID;

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
        if(!haveUser(userName)) return null;

        return new User(uuid: "858c93a3-d098-4458-b141-e8736696020a", "jackson", "amy", "smith", "password");

    }
}
