package DegreeEZ;
import java.util.ArrayList;

public class MajorList {
   // private static User instance;
    private static MajorList majorList;
    private ArrayList<Major> majors;

    private MajorList() {
<<<<<<< HEAD
        if (instance == null) instance = new MajorList(DataLoader.getMajorList("majorList.java"));
        return instance;
        // WIP
=======
        majors = DataLoader.loadMajors("majors.json");
    }
    
    public static MajorList getInstance() {
        if(majorList==null) {
            majorList = new MajorList();
        }
        return majorList;
>>>>>>> 573f75325be219570882a41078471a4618bb1258
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
