import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WestminsterShoppingManager implements ShoppingManager {

    ArrayList<Product> ListOfAllProducts = new ArrayList<>(); //Array list to store all the products
    String ProductId; //variable  to store the product ID

    //method for the menu
    public void Menu(){
        ListOfAllProducts.clear();
        LordData();

        while (true) {
            Scanner input1 = new Scanner(System.in);

            System.out.println("\n");
            System.out.println("-------MENU-------");
            System.out.println("1.Add a new product\n2.Delete a product\n3.Print the list of the products\n4.Open GUI\n5.Save to file\n6.Exit");
            System.out.println("\n");
            System.out.print("Enter a Option: ");
            int MenuInput = input1.nextInt();

            switch (MenuInput) {
                case 1 -> AddProduct();
                case 2 -> DeleteProduct();
                case 3 -> PrintProduct();
                case 4 -> new Login();
                case 5 -> SaveToFile();
                case 6 -> {
                    SaveToFile();
                    System.exit(0);
                }
                default -> System.out.println("Invalid Input");
            }
        }
    }

    //method to get product details
    public void GetProductDetails(){
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);

        String ProductName; //variable to store Product Names
        double Price; //variable to store Product price
        String Category; //variable to store Product category
        String Brand; //variable to store Product Brand
        String WarrantyPeriod; //variable to store Product Warranty Period

        String Size; //variable to store Product size
        String Color; //variable to store Product colour

        int NumberOfAvailableItems;

        // Validate user input
        do {
            System.out.print("Enter the ProductName: ");
            ProductName = input2.nextLine();
        } while (ProductName.trim().isEmpty());

        // Validate user input
        do {
            System.out.print("Enter the Price: ");
            String InputLine = input2.nextLine().trim();

            if (InputLine.isEmpty()) {
                System.out.println("Invalid input. Please enter a valid price.");
                continue;
            }

            try {
                Price = Double.parseDouble(InputLine);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid price.");
            }
        } while (true);

        // Validate user input
        do {
            System.out.print("Enter the product type (Electronics - e/Clothing - c): ");
            Category = input3.nextLine().toLowerCase();
        } while (!Category.equals("e") && !Category.equals("c"));

        if (Category.equals("e")) {

            Category = "Electronics";
            // Validate user input
            do {
                System.out.print("Enter the Brand: ");
                Brand = input3.nextLine();
            }while (Brand.trim().isEmpty());
            // Validate user input
            do {
                System.out.print("Enter the Warranty Period: ");
                WarrantyPeriod = input3.nextLine();
            }while (WarrantyPeriod.trim().isEmpty());
            // Validate user input
            do {
                System.out.print("How many items do yo want to add: ");
                String InputLine = input2.nextLine().trim();

                if (InputLine.isEmpty()) {
                    System.out.println("Invalid input. Please enter a valid value.");
                    continue;
                }

                try {
                    NumberOfAvailableItems = Integer.parseInt(InputLine);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid value.");
                }
            }while (true);

            Electronics e1 = new Electronics(ProductId, ProductName, Category, NumberOfAvailableItems, Price, Brand, WarrantyPeriod); //creat electronic object

            ListOfAllProducts.add(e1); // add the product
            System.out.println("Product added");

        } else if (Category.equals("c")) {

            Category = "Clothing";
            // Validate user input
            do {
                System.out.print("Enter the Size: ");
                Size = input3.nextLine();
            }while (Size.trim().isEmpty());
            // Validate user input
            do {
                System.out.print("Enter the Color: ");
                Color = input3.nextLine();
            }while (Color.trim().isEmpty());
            // Validate user input
            do {
                System.out.print("How many items do yo want to add: ");
                String InputLine = input2.nextLine().trim();

                if (InputLine.isEmpty()) {
                    System.out.println("Invalid input. Please enter a valid value.");
                    continue;
                }

                try {
                    NumberOfAvailableItems = Integer.parseInt(InputLine);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid value.");
                }
            }while (true);

            Clothing c1 = new Clothing(ProductId, ProductName, Category, NumberOfAvailableItems, Price, Size, Color); //creat clothing object

            ListOfAllProducts.add(c1); // add the product
            System.out.println("Product added");
        }
    }

    //method to check product ID
    public static boolean CheckProductId(String productId) {
        // Regular expression to check if the string contains at least one letter and one number
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d).+$";

        // Check if the productId matches the regular expression
        return productId.matches(regex);
    }

    //method to add the products
    @Override
    public void AddProduct(){
        Scanner input2 = new Scanner(System.in);
        // Validate user input
        do {
            System.out.print("Enter the ProductId: ");
            ProductId = input2.nextLine();
        }while (!CheckProductId(ProductId) && ProductId != null);

        if (ListOfAllProducts.size() <= 50) { //set maximum products to 50

            if (ListOfAllProducts.isEmpty()) {

                GetProductDetails();

            } else {

                boolean ItemExistence = false;

                for (int i = 0; i < ListOfAllProducts.size(); i++) {
                    String CheckId = ListOfAllProducts.get(i).getProductId();

                    if (ProductId.equals(CheckId)) {

                        System.out.print("How many items do yo want to add: ");
                        int AddCount = input2.nextInt();

                        ListOfAllProducts.get(i).setNumberOfAvailableItems(ListOfAllProducts.get(i).getNumberOfAvailableItems() + AddCount);
                        System.out.println("Product already exist");
                        System.out.println("Number Of Available Items Updated");
                        ItemExistence = true;
                        break;

                    }

                }

                if (!ItemExistence) {
                    GetProductDetails();
                }
            }
        }else {
            System.out.println("Product count reach to maximum count");
        }

    }

    //method to delete product
    @Override
    public void DeleteProduct(){
        Scanner input4 = new Scanner(System.in);
        String productId;
        // Validate user input
        do {
            System.out.print("Enter the ProductId: ");
            productId = input4.nextLine();
        }while (!CheckProductId(ProductId) && ProductId != null);

        for (int i = 0; i < ListOfAllProducts.size(); i++) {
            String CheckId = ListOfAllProducts.get(i).getProductId();

            if (productId.equals(CheckId)){

                System.out.println(ListOfAllProducts.get(i).toString()); // print the product details
                ListOfAllProducts.remove(i);
                System.out.println("Product removed Successfully");
                System.out.println(ListOfAllProducts.size() + " Item left in the system");
            }

        }
    }

    //method to print product
    @Override
    public void PrintProduct(){

        // Define a custom comparator
        Comparator<Product> nameComparator = Comparator.comparing(Product::getProductName);

        // Sort the list using the custom comparator
        ListOfAllProducts.sort(nameComparator);

        for (int i = 0; i < ListOfAllProducts.size(); i++) {

            System.out.println(ListOfAllProducts.get(i).toString());

        }

    }

    //method to save data to the file
    @Override
    public void SaveToFile(){


        try {
            File customerDetails = new File("OnlineShoppingSystemDetails.txt");
            FileWriter Details = new FileWriter(customerDetails);

            for(int i=0; i<ListOfAllProducts.size(); i++){

                Details.write(ListOfAllProducts.get(i).toString());
                Details.write("\n");

            }

            Details.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data stored successfully");
    }

    //method to lord data from file
    public void LordData(){

        try {

            File customerDetails = new File("OnlineShoppingSystemDetails.txt");

            Scanner ReadDetails = new Scanner(customerDetails);

            while (ReadDetails.hasNextLine()) {
                String data = ReadDetails.nextLine();

                if (data.contains("Clothing")) {

                    Pattern pattern = Pattern.compile("ProductId = ([a-zA-Z0-9_]+), ProductName = (\\w+), NumberOfAvailableItems = (\\d+), Price= ([\\d.]+), Size= (\\w+), Colour= (\\w+), Category = (\\w+)"); //check the pattern
                    Matcher matcher = pattern.matcher(data);

                    if (matcher.find()) {
                        String productId = (matcher.group(1));
                        String productName = matcher.group(2);
                        int numberOfAvailableItems = Integer.parseInt(matcher.group(3));
                        double price = Double.parseDouble(matcher.group(4));
                        String size = (matcher.group(5));
                        String colour = matcher.group(6);
                        String category = matcher.group(7);


                        Clothing c2 = new Clothing(productId, productName, category, numberOfAvailableItems, price, size, colour);
                        ListOfAllProducts.add(c2);


                    }

                }else if (data.contains("Electronics")){



                    Pattern pattern = Pattern.compile("ProductId = ([a-zA-Z0-9_]+), ProductName = (\\w+), NumberOfAvailableItems = (\\d+), Price= ([\\d.]+), Brand= (\\w+), WarrantyPeriod= (\\d+ \\w+), Category = (\\w+)"); //check the pattern
                    Matcher matcher = pattern.matcher(data);

                    if (matcher.find()) {

                        String productId = matcher.group(1);
                        String productName = matcher.group(2);
                        int numberOfAvailableItems = Integer.parseInt(matcher.group(3));
                        double price = Double.parseDouble(matcher.group(4));
                        String brand = (matcher.group(5));
                        String warrantyPeriod = matcher.group(6);
                        String category = matcher.group(7);


                        Electronics e2 = new Electronics(productId, productName, category, numberOfAvailableItems, price, brand, warrantyPeriod);
                        ListOfAllProducts.add(e2);

                    }


                }

            }
            ReadDetails.close();

        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
