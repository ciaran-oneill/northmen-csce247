package DegreeEZ;
import java.util.ArrayList;

public class MajorList {
    private static User instance;
    private MajorList majorList;
    private ArrayList<Major> majors;

    private MajorList() {
<<<<<<< HEAD
        
=======
        if (instance == null) instance = new MajorList(DataLoader.loadMajors("majors.json"));
        return instance;
        // WIP
>>>>>>> 71d0db7200ad1d516c66a288626896c41b75665c
    }

    public static MajorList getInstance() {
        if (instance == null) instance = new Users(DataLoader.getUsers("users.json"));
        return instance;
        // WIP
    }

    public ArrayList<Major> getMajors() {
        if (instance == null) instance = new Major(DataLoader.getMajors("majors.json"));
        return instance;
        //WIP
    } 
}
