package DegreeEZ;
import java.util.ArrayList;

public class MajorList {
   // private static User instance;
    private static MajorList majorList;
    private ArrayList<Major> majors;

    private MajorList() {
        majors = DataLoader.loadMajors("majors.json");
    }
    
    public static MajorList getInstance() {
        if(majorList==null) {
            majorList = new MajorList();
        }
        return majorList;
    }

    public boolean haveMajor(String name ) {
        return true;
    }

    public boolean addMajor(Major major) {
        
    }



        

    

    public ArrayList<Major> getMajors() {
        if (instance == null) instance = new Major(DataLoader.getMajors("majors.json"));
        return instance;
        //WIP
    } 
}
