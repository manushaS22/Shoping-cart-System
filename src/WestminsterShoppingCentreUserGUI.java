import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;



public class WestminsterShoppingCentreUserGUI extends JFrame {

    ArrayList<Integer> RedRowList = new ArrayList<>(); // Array list to add the low stock items

    //WestminsterShoppingCentreUserGUI components
    JTable ProductStore;
    JButton ShoppingCartButton;
    JComboBox<String> CategoryBox;
    JLabel ProductIDContainerLabel;
    JLabel CategoryContainerLabel;
    JLabel NameContainerLabel;
    JLabel ProductSpecificContainerLabel1;
    JLabel ProductSpecificContainerLabel2;
    JLabel AvailableItemContainerLabel;
    JLabel ProductSpecificLabel1;
    JLabel ProductSpecificLabel2;
    JButton AddToCartButton;
    JSpinner ItemCount;
    int CartItemMaxValue = 1; //set the minimum buy count to 1


    String[] ColumnNames = {"Product ID", "Name", "Category", "Price £", "Info"}; //Product table column list
    String[][] TableData = new String[50][5]; // Array list to add the table data

    WestminsterShoppingCentreUserGUI() { //constructor
        this.setLayout(new BorderLayout()); //Set border layout for JFrame

        Actions GetActions = new Actions(); // Creat instance of Action class

        //JPanel for Showing the Store details and controls
        JPanel StoreDetails = new JPanel();
        StoreDetails.setPreferredSize(new Dimension(700, 450));
        StoreDetails.setBorder(new LineBorder(Color.black, 1));
        StoreDetails.setLayout(null); // set jPanel layout to null
        this.add(StoreDetails, BorderLayout.NORTH); //set it to north in jFrame



        String[] ComboBoxCategories = {"All", "Electronics", "Clothing"}; // set combo box categories
        CategoryBox = new JComboBox<>(ComboBoxCategories);
        CategoryBox.setBounds(250, 50, 200, 40);
        CategoryBox.setVisible(true);
        StoreDetails.add(CategoryBox);

        CategoryBox.addActionListener(GetActions); // add action to category box


        DefaultTableModel model = new DefaultTableModel(TableData, ColumnNames);
        TableRowSorter SortTable = new TableRowSorter(model);  //Creat instance using table row sorter class

        ProductStore = new JTable(model);
        ProductStore.setRowSorter(SortTable); //Set row sorter for ProductStore Table


        ProductStore.setDefaultRenderer(Object.class, new CustomTableCellRenderer());


        JScrollPane ScrollPane = new JScrollPane(ProductStore);
        ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ScrollPane.setBounds(20, 100, 650, 300);
        StoreDetails.add(ScrollPane);

        ShoppingCartButton = new JButton("Shopping Cart");
        ShoppingCartButton.setBounds(570, 10, 120, 40);
        StoreDetails.add(ShoppingCartButton);



        ShoppingCartButton.addActionListener(GetActions); //add action to shopping cart button

        MyListener myListener = new MyListener();
        ProductStore.addMouseListener(myListener);

        //JPanel for Showing the selected item details
        JPanel SelectedItemDetails = new JPanel();
        SelectedItemDetails.setPreferredSize(new Dimension(700, 350));
        SelectedItemDetails.setBorder(new LineBorder(Color.black, 1));
        SelectedItemDetails.setLayout(null); // set jPanel layout to null
        this.add(SelectedItemDetails, BorderLayout.SOUTH); //set it to south in jFrame


        JLabel Topic = new JLabel("Selected Product - Details");
        Topic.setBounds(30, 50, 200, 20);
        Topic.setFont(new Font("SansSerif ", Font.BOLD, 13));
        SelectedItemDetails.add(Topic);

        JLabel ProductIDLabel = new JLabel("Product Id: ");
        ProductIDLabel.setBounds(30, 90, 100, 20);
        SelectedItemDetails.add(ProductIDLabel);

        JLabel CategoryLabel = new JLabel("Category: ");
        CategoryLabel.setBounds(30, 125, 100, 20);
        SelectedItemDetails.add(CategoryLabel);

        JLabel NameLabel = new JLabel("Name: ");
        NameLabel.setBounds(30, 160, 100, 20);
        SelectedItemDetails.add(NameLabel);

        ProductSpecificLabel1 = new JLabel("Brand: ");     // ProductSpecificLabel1 (Size/Brand)
        ProductSpecificLabel1.setBounds(30, 195, 100, 20);
        SelectedItemDetails.add(ProductSpecificLabel1);

        ProductSpecificLabel2 = new JLabel("Warranty Period: ");     // ProductSpecificLabel1 (Color/WarrantyPeriod)
        ProductSpecificLabel2.setBounds(30, 230, 150, 20);
        SelectedItemDetails.add(ProductSpecificLabel2);

        JLabel AvailableItemLabel = new JLabel("Items Available: ");
        AvailableItemLabel.setBounds(30, 265, 200, 20);
        SelectedItemDetails.add(AvailableItemLabel);


        ProductIDContainerLabel = new JLabel();
        ProductIDContainerLabel.setBounds(150, 90, 100, 20);
        SelectedItemDetails.add(ProductIDContainerLabel);

        CategoryContainerLabel = new JLabel();
        CategoryContainerLabel.setBounds(150, 125, 100, 20);
        SelectedItemDetails.add(CategoryContainerLabel);

        NameContainerLabel = new JLabel();
        NameContainerLabel.setBounds(150, 160, 100, 20);
        SelectedItemDetails.add(NameContainerLabel);

        ProductSpecificContainerLabel1 = new JLabel();     // ProductSpecificLabel1 (Size/Brand)
        ProductSpecificContainerLabel1.setBounds(150, 195, 100, 20);
        SelectedItemDetails.add(ProductSpecificContainerLabel1);

        ProductSpecificContainerLabel2 = new JLabel();     // ProductSpecificLabel1 (Color/WarrantyPeriod)
        ProductSpecificContainerLabel2.setBounds(150, 230, 100, 20);
        SelectedItemDetails.add(ProductSpecificContainerLabel2);

        AvailableItemContainerLabel = new JLabel();
        AvailableItemContainerLabel.setBounds(150, 265, 100, 20);
        SelectedItemDetails.add(AvailableItemContainerLabel);

        AddToCartButton = new JButton("Add to Shopping Cart");
        AddToCartButton.setBounds(230,290,150,40);
        SelectedItemDetails.add(AddToCartButton);



        SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, CartItemMaxValue, 1); // JSpinner to get the buy count
        ItemCount = new JSpinner(model1);


        ItemCount.setBounds(420,290,100,40);
        SelectedItemDetails.add(ItemCount);

        AddToCartButton.addActionListener(GetActions); // add action to add to cart button



        this.setTitle("Westminster Shopping Centre");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false); //Making the JFrame size not changeable
        this.setSize(700, 800);// set JFrame size
        this.setVisible(true);// Visible the frame


    }

    public class Actions implements ActionListener { //class for the actions
        @Override
        public void actionPerformed(ActionEvent evt) {
            String value = CategoryBox.getSelectedItem().toString();
            String ButtonPressed = evt.getActionCommand();

            if (ButtonPressed.equals("Shopping Cart")){

                PurchaseDetails.LordPurchaseUsers(); //lord Previously purchased users to the array

                ShoppingCartGui Run = new ShoppingCartGui();

                ShoppingCart AddPrice = new ShoppingCart();

                AddPrice.CalculateTotalCost();

            } else if (ButtonPressed.equals("Add to Shopping Cart")){
                try {

                    ShoppingCart AddItem = new ShoppingCart();

                    int itemCount = (int) ItemCount.getValue(); // Get the item count from the text field
                    AddItem.AddItems(ProductIDContainerLabel.getText(), itemCount, ProductSpecificContainerLabel1.getText(), ProductSpecificContainerLabel2.getText());

                    AddItem.CalculateTotalCost();

                    ShoppingCartGui.AddItems Add = new ShoppingCartGui.AddItems();

                    Add.AddCartItemToTable(); // Add Items in to cart Table

                }catch (NullPointerException e){}



            } else if (value.equals("Electronics")) {
                int productCount = 0;

                try {
                    File customerDetails = new File("OnlineShoppingSystemDetails.txt");
                    Scanner readDetails = new Scanner(customerDetails);

                    while (readDetails.hasNextLine()) {
                        String data = readDetails.nextLine();

                        if (data.contains("Electronics")) {
                            Pattern pattern = Pattern.compile("ProductId = ([a-zA-Z0-9_]+), ProductName = (\\w+), NumberOfAvailableItems = (\\d+), Price= ([\\d.]+), Brand= (\\w+), WarrantyPeriod= (\\d+ \\w+), Category = (\\w+)");
                            Matcher matcher = pattern.matcher(data);

                            if (matcher.find()) {
                                String productId = matcher.group(1);
                                String productName = matcher.group(2);
                                double price = Double.parseDouble(matcher.group(4));
                                String brand = matcher.group(5);
                                String warrantyPeriod = matcher.group(6);
                                String category = matcher.group(7);

                                TableData[productCount][0] = productId;
                                TableData[productCount][1] = productName;
                                TableData[productCount][2] = category;
                                TableData[productCount][3] = String.valueOf(price);
                                TableData[productCount][4] = brand + "," + warrantyPeriod;

                                productCount++;
                            }
                        }
                    }
                    readDetails.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                DefaultTableModel model = (DefaultTableModel) ProductStore.getModel();
                // Clear existing rows from the table
                model.setRowCount(0);

                // Add rows to the table
                for (int i = 0; i < productCount; i++) {
                    model.addRow(TableData[i]);
                }


            } else if (value.equals("Clothing")) {
                int productCount = 0;

                try {

                    File customerDetails = new File("OnlineShoppingSystemDetails.txt");
                    Scanner ReadDetails = new Scanner(customerDetails);

                    while (ReadDetails.hasNextLine()) {
                        String data = ReadDetails.nextLine();

                        if (data.contains("Clothing")) {

                            Pattern pattern = Pattern.compile("ProductId = ([a-zA-Z0-9_]+), ProductName = (\\w+), NumberOfAvailableItems = (\\d+), Price= ([\\d.]+), Size= (\\w+), Colour= (\\w+), Category = (\\w+)");
                            Matcher matcher = pattern.matcher(data);

                            if (matcher.find()) {
                                String productId = (matcher.group(1));
                                String productName = matcher.group(2);
                                double price = Double.parseDouble(matcher.group(4));
                                String size = (matcher.group(5));
                                String colour = matcher.group(6);
                                String category = matcher.group(7);


                                TableData[productCount][0] = productId;
                                TableData[productCount][1] = productName;
                                TableData[productCount][2] = category;
                                TableData[productCount][3] = String.valueOf(price);
                                TableData[productCount][4] = size + "," + colour;


                                productCount++;
                            }
                        }

                    }
                    ReadDetails.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }


                DefaultTableModel model = (DefaultTableModel) ProductStore.getModel();
                // Clear existing rows from the table
                model.setRowCount(0);

                // Add rows to the table
                for (int i = 0; i < productCount; i++) {
                    model.addRow(TableData[i]);
                }


            } else if (value.equals("All")) {
                int productCount = 0;

                try {
                    File customerDetails = new File("OnlineShoppingSystemDetails.txt");
                    Scanner readDetails = new Scanner(customerDetails);

                    while (readDetails.hasNextLine()) {
                        String data = readDetails.nextLine();

                        if (data.contains("Electronics")) {
                            Pattern pattern = Pattern.compile("ProductId = ([a-zA-Z0-9_]+), ProductName = (\\w+), NumberOfAvailableItems = (\\d+), Price= ([\\d.]+), Brand= (\\w+), WarrantyPeriod= (\\d+ \\w+), Category = (\\w+)");  //check the pattern
                            Matcher matcher = pattern.matcher(data);

                            if (matcher.find()) {
                                String productId = matcher.group(1);
                                String productName = matcher.group(2);
                                double price = Double.parseDouble(matcher.group(4));
                                String brand = matcher.group(5);
                                String warrantyPeriod = matcher.group(6);
                                String category = matcher.group(7);

                                TableData[productCount][0] = productId;
                                TableData[productCount][1] = productName;
                                TableData[productCount][2] = category;
                                TableData[productCount][3] = String.valueOf(price);
                                TableData[productCount][4] = brand + "," + warrantyPeriod;

                                productCount++;
                            }

                        } else if (data.contains("Clothing")) {

                            Pattern pattern = Pattern.compile("ProductId = ([a-zA-Z0-9_]+), ProductName = (\\w+), NumberOfAvailableItems = (\\d+), Price= ([\\d.]+), Size= (\\w+), Colour= (\\w+), Category = (\\w+)"); //check the pattern
                            Matcher matcher = pattern.matcher(data);

                            if (matcher.find()) {
                                String productId = (matcher.group(1));
                                String productName = matcher.group(2);
                                double price = Double.parseDouble(matcher.group(4));
                                String size = (matcher.group(5));
                                String colour = matcher.group(6);
                                String category = matcher.group(7);


                                TableData[productCount][0] = productId;
                                TableData[productCount][1] = productName;
                                TableData[productCount][2] = category;
                                TableData[productCount][3] = String.valueOf(price);
                                TableData[productCount][4] = size + "," + colour;


                                productCount++;
                            }
                        }

                    }
                    readDetails.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                DefaultTableModel model = (DefaultTableModel) ProductStore.getModel();
                // Clear existing rows from the table
                model.setRowCount(0);

                // Add rows to the table
                for (int i = 0; i < productCount; i++) {
                    model.addRow(TableData[i]);
                }


            }

            CheckQuantity();
        }
    }






    public String GetQuantity(String TableProductId, String Category){
        String AvailableItemCount = null;

        try {
            File customerDetails = new File("OnlineShoppingSystemDetails.txt");
            Scanner readDetails = new Scanner(customerDetails);

            while (readDetails.hasNextLine()) {
                String data = readDetails.nextLine();

                if (Category.equals("Electronics")) {

                    Pattern pattern = Pattern.compile("ProductId = ([a-zA-Z0-9_]+), ProductName = (\\w+), NumberOfAvailableItems = (\\d+), Price= ([\\d.]+), Brand= (\\w+), WarrantyPeriod= (\\d+ \\w+), Category = (\\w+)"); //check the pattern
                    Matcher matcher = pattern.matcher(data);

                    if (matcher.find()) {
                        String productId = matcher.group(1);
                        int numberOfAvailableItems = Integer.parseInt(matcher.group(3));

                        if (productId.equals(TableProductId)) {
                            AvailableItemCount = String.valueOf(numberOfAvailableItems);
                        }

                    }

                }else if (data.contains("Clothing")){


                    Pattern pattern = Pattern.compile("ProductId = ([a-zA-Z0-9_]+), ProductName = (\\w+), NumberOfAvailableItems = (\\d+), Price= ([\\d.]+), Size= (\\w+), Colour= (\\w+), Category = (\\w+)"); //check the pattern
                    Matcher matcher = pattern.matcher(data);

                    if (matcher.find()) {
                        String productId = matcher.group(1);
                        int numberOfAvailableItems = Integer.parseInt(matcher.group(3));

                        if (productId.equals(TableProductId)) {
                            AvailableItemCount = String.valueOf(numberOfAvailableItems);
                        }

                    }

                }



            }
            readDetails.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }catch (NullPointerException e){}

        return AvailableItemCount;
    }

    private class MyListener implements MouseListener {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {

            try {
                int Row = ProductStore.getSelectedRow(); //get the selected row
                DefaultTableModel model = (DefaultTableModel) ProductStore.getModel();


                ProductIDContainerLabel.setText(model.getValueAt(Row, 0).toString());
                CategoryContainerLabel.setText(model.getValueAt(Row, 2).toString());
                NameContainerLabel.setText(model.getValueAt(Row, 1).toString());


                String[] InfoArray = model.getValueAt(Row, 4).toString().split(",");

                ProductSpecificContainerLabel1.setText(InfoArray[0]);
                ProductSpecificContainerLabel2.setText(InfoArray[1]);

                if (model.getValueAt(Row, 2).toString().equals("Electronics")) {
                    ProductSpecificLabel1.setText("Brand: ");
                    ProductSpecificLabel2.setText("Warranty Period: ");

                    String ItemCount = GetQuantity(model.getValueAt(Row, 0).toString(), "Electronics"); //set the available item count method
                    AvailableItemContainerLabel.setText(ItemCount);

                } else if (model.getValueAt(Row, 2).toString().equals("Clothing")) {
                    ProductSpecificLabel1.setText("Size: ");
                    ProductSpecificLabel2.setText("Color: ");

                    String ItemCount = GetQuantity(model.getValueAt(Row, 0).toString(), "Clothing"); //set the available item count method
                    AvailableItemContainerLabel.setText(ItemCount);

                }

                UpdateMaxProductItemCount();

            }catch (NullPointerException exception){
                System.out.println("No data in the table");
            }
        }


        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {

        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {

        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {

        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {

        }
    }


    public class CustomTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (RedRowList.contains(row)) {
                cell.setBackground(Color.red);
            } else {
                cell.setBackground(Color.white);
                cell.setForeground(Color.black);
            }

            return cell;
        }
    }

    //method to check the quantity
    private void CheckQuantity(){
        try {
            RedRowList.clear();
            DefaultTableModel model = (DefaultTableModel) ProductStore.getModel();

            for (int i = 0; i < ProductStore.getRowCount(); i++) {
                String Quantity = GetQuantity((String) model.getValueAt(i, 0), (String) model.getValueAt(i, 2));


                if (Integer.parseInt(Quantity) < 3) {
                    RedRowList.add(i);
                }

            }
        }catch (NumberFormatException e){}


    }

    //method to change the maximum value of the JSpinner according to each item
    private void UpdateMaxProductItemCount() {
        int MaxValue = Integer.parseInt(AvailableItemContainerLabel.getText());
        SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, MaxValue, 1);
        ItemCount.setModel(model1); // Update the existing spinner with the new model
    }


}
