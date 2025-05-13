import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ShoppingCartGui extends JFrame {

    String[] ColumnNames = {"Product", "Quantity", "Price"}; //table column array
    static String[][] CartTableData = new String[50][3]; //table data array

    //Shopping cart components
    static JTable CartTable;
    JLabel User;
    JLabel TotalLabel;
    JLabel FirstPurchaseDiscountLabel;
    JLabel ThreeItemSameCategoryDiscountLabel;
    JLabel FinalTotalLabel;
    static JLabel TotalContainerLabel;
    static JLabel FirstPurchaseDiscountContainerLabel;
    static JLabel ThreeItemSameCategoryDiscountContainerLabel;
    static JLabel FinalTotalContainerLabel;
    JButton PurchasesButton;
    JButton DeleteButton;
    static JLabel Message;
    static String Username;


    ShoppingCartGui() { //constructor
        this.setLayout(null);

        //String Username = Login.UserName; //get the login username

        User = new JLabel(Username);
        User.setBounds(20,50,100,30);
        this.add(User);

        Message = new JLabel();
        Message.setBounds(300,50,100,30);
        this.add(Message);

        DefaultTableModel model = new DefaultTableModel(CartTableData, ColumnNames);
        CartTable = new JTable(model);
        CartTable.setRowHeight(60);

        JScrollPane ScrollPane = new JScrollPane(CartTable);
        ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ScrollPane.setBounds(20, 100, 650, 300);
        this.add(ScrollPane);

        AddItems Add = new AddItems();
        Add.AddCartItemToTable();

        TotalLabel = new JLabel("Total");
        TotalLabel.setBounds(400,450,100,30);
        this.add(TotalLabel);

        FirstPurchaseDiscountLabel = new JLabel("First Purchase Discount (10%)");
        FirstPurchaseDiscountLabel.setBounds(250,500,200,30);
        this.add(FirstPurchaseDiscountLabel);

        ThreeItemSameCategoryDiscountLabel = new JLabel("Three items in same Category Discount (20%)");
        ThreeItemSameCategoryDiscountLabel.setBounds(150,550,300,30);
        this.add(ThreeItemSameCategoryDiscountLabel);

        FinalTotalLabel = new JLabel("Final Total");
        FinalTotalLabel.setBounds(370,600,100,30);
        this.add(FinalTotalLabel);

        TotalContainerLabel = new JLabel();
        TotalContainerLabel.setBounds(500,450,200,30);
        this.add(TotalContainerLabel);

        FirstPurchaseDiscountContainerLabel = new JLabel();
        FirstPurchaseDiscountContainerLabel.setBounds(500,500,200,30);
        this.add(FirstPurchaseDiscountContainerLabel);

        ThreeItemSameCategoryDiscountContainerLabel = new JLabel();
        ThreeItemSameCategoryDiscountContainerLabel.setBounds(500,550,200,30);
        this.add(ThreeItemSameCategoryDiscountContainerLabel);

        FinalTotalContainerLabel = new JLabel();
        FinalTotalContainerLabel.setBounds(500,600,200,30);
        this.add(FinalTotalContainerLabel);

        PurchasesButton = new JButton("Purchases");
        PurchasesButton.setBounds(250,700,200,30);
        this.add(PurchasesButton);

        ButtonAction AddAction = new ButtonAction();

        PurchasesButton.addActionListener(AddAction);

        DeleteButton = new JButton("Remove");
        DeleteButton.setBounds(580,30,100,30);
        this.add(DeleteButton);

        DeleteButton.addActionListener(AddAction);

        this.setTitle("Shopping Cart"); //set JFrame Title
        this.setResizable(false); //Making the JFrame size not changeable
        this.setSize(700, 800); // set JFrame size
        this.setVisible(true); // Visible the frame
    }

    public class ButtonAction implements ActionListener { // class for the action listener

        @Override
        public void actionPerformed(ActionEvent e) {
            String ButtonPressed = e.getActionCommand();

            if (ButtonPressed.equals("Purchases")) {
                PurchaseDetails AddCustomers = new PurchaseDetails(); //creating instance using PurchaseDetails


                if (!PurchaseDetails.PurchaseCustomers.contains(User.getText())) { // check the user is previously purchased one
                    PurchaseDetails.PurchaseCustomers.add(User.getText());
                }
                AddCustomers.SavePurchaseUsers(); // save the purchase users


                ReduceProductCount(); //Reduce the product count in the ListOfAllProducts



            }else if (ButtonPressed.equals("Remove")) {

                ShoppingCart Update = new ShoppingCart();
                Update.RemoveItems(); //Remove items from cart

                ShoppingCartGui.AddItems Add = new ShoppingCartGui.AddItems();
                Add.AddCartItemToTable(); //Refresh the cart

                Update.CalculateTotalCost(); //refresh the total and discounts

            }
        }
    }

    public static class AddItems { // class for add items to Cart table
        public void AddCartItemToTable() { //method for add items to Cart table

            // Create a DecimalFormat object with the desired format
            DecimalFormat DecimalFormat = new DecimalFormat("#.##");

            try {
                DefaultTableModel model = (DefaultTableModel) CartTable.getModel(); //retrieve DefaultTableModel

                //add data to the table
                for (int i = 0; i < ShoppingCart.getListOfProducts().size(); i++) {

                    CartTableData[i][0] = "<html>" +
                            ShoppingCart.getListOfProducts().get(i).getProduct().getProductId() +
                            "<br>" +
                            ShoppingCart.getListOfProducts().get(i).getProduct().getProductName() +
                            "<br>" +
                            ShoppingCart.getListOfProducts().get(i).getProductSpecification1() +
                            "," +
                            ShoppingCart.getListOfProducts().get(i).getProductSpecification2() +
                            "</html>";

                    CartTableData[i][1] = String.valueOf(ShoppingCart.getListOfProducts().get(i).getItemCount());

                    double CartPrice = ShoppingCart.getListOfProducts().get(i).getProduct().getPrice() * ShoppingCart.getListOfProducts().get(i).getItemCount();

                    CartTableData[i][2] = DecimalFormat.format(CartPrice) + "£";

                }

                model.setRowCount(0); //set table row count to table

                for (int j = 0; j < CartTableData.length; j++) {
                    model.addRow(CartTableData[j]); // add the rows
                }

            }catch (NullPointerException e){}


        }
    }

    // method to reduce the product count in ListOfAllProducts
    public void ReduceProductCount() {
        DefaultTableModel model = (DefaultTableModel) CartTable.getModel(); //retrieve DefaultTableModel
        WestminsterShoppingManager GetProduct = new WestminsterShoppingManager(); // create an instance from WestminsterShoppingManager

        GetProduct.LordData(); // load the product data

        for (int i = 0; i < ShoppingCart.getListOfProducts().size(); i++) {
            String CheckId = ShoppingCart.getListOfProducts().get(i).getProduct().getProductId(); // get the product id to check
            int ReduceValue = Integer.parseInt(model.getValueAt(0, 1).toString()); //get the reduce value from cart table
            System.out.println(ReduceValue);

            for (int j = 0; j < GetProduct.ListOfAllProducts.size(); j++)
                if (GetProduct.ListOfAllProducts.get(j).getProductId().equals(CheckId)){
                    GetProduct.ListOfAllProducts.get(j).setNumberOfAvailableItems(GetProduct.ListOfAllProducts.get(j).getNumberOfAvailableItems() - ReduceValue); //reduce the count
                }
        }



        GetProduct.SaveToFile(); //save data
    }

}
