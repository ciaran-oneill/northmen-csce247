public class UserList {
    private static UserList userList;
    private ArrayList<User> users;
    private int UUID;

    public UserList() {
        userList = dataBase.getUsers();
    }

    public static UserList getInstance() {
        if(userList==null) {
            userList = new UserList();
        }
    }


    
    public User getUser(String userName) {
        return new User (userName, "firstname", "lastName");

    }
}
