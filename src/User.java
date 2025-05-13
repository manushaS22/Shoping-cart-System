import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private ArrayList<User> UserList = new ArrayList<>();
    private String UserName; //variable to store username
    private String Password; //variable to store password

    //default constructor
    public User() {
    }

    // parameterized constructor
    public User(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    //getters
    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public ArrayList<User> getUserList() {
        return UserList;
    }


    @Override
    public String toString() {
        return "User{" +
                "UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    //method to load user data
    public void LordUserData(){

        try {

            File customerDetails = new File("UserDetails.txt");

            Scanner ReadDetails = new Scanner(customerDetails);

            while (ReadDetails.hasNextLine()) {
                String data = ReadDetails.nextLine();


                Pattern pattern = Pattern.compile("UserName='(.*?)', Password='(.*?)'"); //check the pattern from text file
                Matcher matcher = pattern.matcher(data);


                if (matcher.find()) {
                    String EnteredUserName = (matcher.group(1));
                    String EnteredPassword = matcher.group(2);

                    User LoardUser = new User(EnteredUserName,EnteredPassword);
                    UserList.add(LoardUser);

                }

            }
        }catch(FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



    }


}
