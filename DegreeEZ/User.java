package DegreeEZ;

public class User {
        private String firstName;
        private String lastName;
        private String password;
        private String userName;
        
        public User(String userName, String firstName, String lastName, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.userName = userName;
        }
    
        public String getFirstName() {
            return firstName;
        }
    
        public String getLastName() {
            return lastName;
        }
    
        public String getPassword() {
            return password;
        } 

        public String getUserName() {
            return userName;
        }
    }

