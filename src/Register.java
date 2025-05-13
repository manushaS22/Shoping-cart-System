import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Register extends JFrame implements ActionListener {
    //Register page components
    JTextField UserNameField;
    JTextField PasswordField;
    JButton RegisterButton;
    public static String UserName; // static variable save the username
    public Register() { //constructor
        setLayout(null); // set JFrame layout to null

        JLabel NameLabel = new JLabel("Westminster Shopping Center");
        NameLabel.setBounds(150,50,200,50);
        this.add(NameLabel);

        JLabel UserNameLabel = new JLabel("User Name : ");
        UserNameLabel.setBounds(80,150,200,50);
        this.add(UserNameLabel);

        JLabel PasswordLabel = new JLabel("Password : ");
        PasswordLabel.setBounds(80,200,200,50);
        this.add(PasswordLabel);

        UserNameField = new JTextField();
        UserNameField.setBounds(200,160,200,30);
        this.add(UserNameField);

        PasswordField = new JTextField();
        PasswordField.setBounds(200,210,200,30);
        this.add(PasswordField);

        RegisterButton = new JButton("Register");
        RegisterButton.setBounds(200,300,100,40);
        this.add(RegisterButton);

        RegisterButton.addActionListener(this); //add action listener to the Register Button

        this.setTitle("Register"); //set JFrame Title
        this.setResizable(false); //Making the JFrame size not changeable
        this.setSize(500, 400); // set JFrame size
        this.setVisible(true); // Visible the frame
    }

    public void actionPerformed(ActionEvent evt) {
        String EnteredUserName = UserNameField.getText(); //get the entered username
        String EnteredPassword = PasswordField.getText(); // get the entered password

        UserName = EnteredUserName; //Saving the username to use in other UIs
        ShoppingCartGui.Username = UserName;

        User GetUserData = new User(); // create an instance using user class

        User NewUser = new User(EnteredUserName,EnteredPassword); //Creat new user object
        GetUserData.getUserList().add(NewUser); // add user to the UserList
        WestminsterShoppingCentreUserGUI Run = new WestminsterShoppingCentreUserGUI(); //Open the WestminsterShoppingCentreGUI
        SaveUsers(GetUserData);//save users to file

        dispose();//Close the previous window

    }


    //method to save users to the textFile
    public void SaveUsers(User userData) {
        try {
            File customerDetails = new File("UserDetails.txt");
            FileWriter details = new FileWriter(customerDetails, true); // true for append mode

            for (int i = 0; i < userData.getUserList().size(); i++) {
                details.write(userData.getUserList().get(i).toString());
                details.write("\n");
                System.out.println(userData.getUserList().get(i).toString());
            }

            details.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
