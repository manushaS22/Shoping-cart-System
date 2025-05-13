import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
 //Login page components
    JTextField UserNameField;
    JTextField PasswordField;
    JButton LoginButton;
    JButton RegisterButton;
    public static String UserName; // static variable save the username
    public Login() { //constructor
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

        LoginButton = new JButton("Login");
        LoginButton.setBounds(200,300,100,40);
        this.add(LoginButton);

        RegisterButton = new JButton("Register");
        RegisterButton.setBounds(300,300,100,40);
        this.add(RegisterButton);

        Action AddAction = new Action(); // Create an instance using Action class

        LoginButton.addActionListener(AddAction);//add the action to the login Button
        RegisterButton.addActionListener(AddAction); //add the action to the Register Button

        this.setTitle("Login"); //set JFrame Title
        this.setResizable(false); //Making the JFrame size not changeable
        this.setSize(500, 400); // set JFrame size
        this.setVisible(true); // Visible the frame
    }

    public class Action implements ActionListener{ //Action class to handle action listeners
        public void actionPerformed(ActionEvent evt) {
            String ButtonPressed = evt.getActionCommand(); //get the button action

            User LordData = new User(); //Creat an instance for user class
            LordData.LordUserData();//Lording User Data

            String EnteredUserName = UserNameField.getText(); //get the entered username
            String EnteredPassword = PasswordField.getText(); // get the entered password

            UserName = EnteredUserName; //Saving the username to use in other UIs
            ShoppingCartGui.Username = UserName;

            if (ButtonPressed.equals("Login")) { //Action for login button

                for (int i = 0; i < LordData.getUserList().size(); i++) {
                    if (EnteredUserName.equals(LordData.getUserList().get(i).getUserName())) { // check the username
                        if (EnteredPassword.equals(LordData.getUserList().get(i).getPassword())) { //check the password
                            WestminsterShoppingCentreUserGUI Run = new WestminsterShoppingCentreUserGUI(); //Open the WestminsterShoppingCentreGUI
                            dispose(); //Close the previous window
                        }else {
                            JOptionPane.showMessageDialog(null,"Invalid Credentials!",null,JOptionPane.ERROR_MESSAGE); //Error message to handle the incorrect password
                        }
                    }
                }

            }else { //action for register button

                Register RunRegister = new Register(); //Open the Register UI
                // Close the Login window
                dispose(); //Close the previous window

            }

        }
    }




}
