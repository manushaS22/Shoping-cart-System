import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PurchaseDetails {

    static ArrayList<String> PurchaseCustomers = new ArrayList<>();


    //method to Save purchase users to file
    public void SavePurchaseUsers(){
        try {
            File customerDetails = new File("PurchaseUserDetails.txt");
            FileWriter Details = new FileWriter(customerDetails, true);

            for(int i = 0; i<PurchaseCustomers.size(); i++){

                Details.write(PurchaseCustomers.get(i)); // Write purchase customers to the file
                Details.write("\n");

            }

            Details.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Lord purchase users to the system
    public static void LordPurchaseUsers() {
        try {
            File customerDetails = new File("PurchaseUserDetails.txt");
            Scanner ReadDetails = new Scanner(customerDetails);

            while (ReadDetails.hasNextLine()) { // read all the lines in the text file
                String data = ReadDetails.nextLine();

                PurchaseCustomers.add(data);
            }
            ReadDetails.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
