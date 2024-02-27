<<<<<<< HEAD
package DegreeEZ;
=======
import java.util.UUID;
>>>>>>> b7b0392ee9cf58a19dbf89fd294340188ed9e835

public class User {
        private final UUID uuid;
        private String firstName;
        private String lastName;
        private String password;
        private String userName;
        
        public User(UUID uuid, String userName, String firstName, String lastName, String password) {
            this.uuid = uuid;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.userName = userName;
        }
    
        public UUID getUUID() {
            return uuid;
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

