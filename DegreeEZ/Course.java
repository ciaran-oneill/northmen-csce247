package DegreeEZ; 

import java.util.ArrayList;

public class Course {
   private Subject subject;
    private int number;
    private String name;
   // private ArrayList<HashMap<Course, String>> prerequisites;
   // private String minGrade;
   // private int creditHours;
    
   
/* 
    public Course(Subject subject, int number, String name) {    
        this.number = number;
        this.name = name;
    }
*/
    Course(String name, int number) {
        this.number = number;
        this.name = name;
    }
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
/* 
    public String getSubject(Subject subject) {
        return subject;
    }

    public String addPrerequisites() {
        return prerequisites;
    }

    public int minGrade(int grade) {
        return grade;
    }
    */
}
