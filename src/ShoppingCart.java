import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
public class ShoppingCart {

    Product Product; // variable to store product
    int ItemCount; // variable to store quantity of user want
    String ProductSpecification1; // store the brand or size
    String ProductSpecification2; // store warranty period or
    private static ArrayList<ShoppingCart> ListOfProducts = new ArrayList<>(); // Array List to store cart items
    int Stock;

    // default constructor
    public ShoppingCart() {
    }

    //parameterized constructor
    public ShoppingCart(Product product, int itemCount, String productSpecification1, String productSpecification2) {
        Product = product;
        ItemCount = itemCount;
        ProductSpecification1 = productSpecification1;
        ProductSpecification2 = productSpecification2;
    }

    //method to add the items to cart
    public void AddItems(String ProductId, int BuyCount, String ProductSpecification1, String ProductSpecification2){
        WestminsterShoppingManager GetProducts = new WestminsterShoppingManager(); // create instance using WestminsterShoppingManager to get the details

        GetProducts.LordData(); //Lording all the products to the ListOfAllProducts

        // get the available stock according to product
        for (int k = 0; k < GetProducts.ListOfAllProducts.size(); k++) {
            if (ProductId.equals(GetProducts.ListOfAllProducts.get(k).getProductId())){
                Stock = GetProducts.ListOfAllProducts.get(k).getNumberOfAvailableItems();
            }
        }


        boolean ItemExist = true; // variable to check the Item is already exists one

        for (int j = 0; j < ListOfProducts.size(); j++){
            if (ProductId.equals(ListOfProducts.get(j).getProduct().getProductId())){ //check the product ID
                if (ListOfProducts.get(j).ItemCount < Stock) { // Check the User selected Item count less or more than the available stock
                    if (BuyCount <= Stock - ListOfProducts.get(j).ItemCount) { // add the remaining item count to the cart if the user selected count is not in the stock
                        ListOfProducts.get(j).ItemCount += BuyCount;
                    }else {
                        ListOfProducts.get(j).ItemCount += (Stock - ListOfProducts.get(j).ItemCount);
                        ShoppingCartGui.Message.setText("Cannot add " + BuyCount); //Print a message to user cannot add that count
                    }
                }
                ItemExist = false;
                break;
            }
        }


        if (ItemExist) { // check the item existence
            for (int i = 0; i < GetProducts.ListOfAllProducts.size(); i++) {

                if (ProductId.equals(GetProducts.ListOfAllProducts.get(i).getProductId())) {
                    ShoppingCart CartItem = new ShoppingCart(GetProducts.ListOfAllProducts.get(i), BuyCount, ProductSpecification1, ProductSpecification2);
                    ListOfProducts.add(CartItem); //add new item
                }
            }
        }

    }

    //method to remove item
    public void RemoveItems(){
        int Row = ShoppingCartGui.CartTable.getSelectedRow(); //get the selected row
        DefaultTableModel model = (DefaultTableModel) ShoppingCartGui.CartTable.getModel();

        String RemoveItem = model.getValueAt(Row, 0).toString(); // get the selected item from cart table

        // Split the string based on the <br> tag
        String[] parts = RemoveItem.split("<br>");

        // Extract the first part (index 0) and remove leading and trailing whitespace
        String ExtractedValue = parts[0].replaceAll("<html>", "").trim();


        for (int i = 0; i < ListOfProducts.size(); i++){
            if (ListOfProducts.get(i).getProduct().getProductId().equals(ExtractedValue)){
                ListOfProducts.remove(i);
                ShoppingCartGui.CartTableData = new String[50][3]; //Remove all the items in cart table
                break;
            }
        }

    }

    // method to Calculate the total cost and discounts
    public void CalculateTotalCost(){
        ShoppingCartGui.TotalContainerLabel.setText("0£");
        double Total = 0;

        // Create a DecimalFormat object with the desired format
        DecimalFormat DecimalFormat = new DecimalFormat("#.##");

        try {
            //Calculate total
            for (int i = 0; i < ListOfProducts.size(); i++) {
                double Price = ListOfProducts.get(i).getProduct().getPrice() * ListOfProducts.get(i).ItemCount;
                Total = Total + Price;
                ShoppingCartGui.TotalContainerLabel.setText(DecimalFormat.format(Total) + "£");
            }
            //calculate first purchase discount
            double FirstPurchaseDiscount = 0;

            if (!PurchaseDetails.PurchaseCustomers.contains(Login.UserName)) {
                FirstPurchaseDiscount = Total * 0.1;
                ShoppingCartGui.FirstPurchaseDiscountContainerLabel.setText(DecimalFormat.format(FirstPurchaseDiscount) + "£");
            }else {
                ShoppingCartGui.FirstPurchaseDiscountContainerLabel.setText(DecimalFormat.format(FirstPurchaseDiscount) + "£");
            }
            //Calculate three item same category discount
            double ThreeItemSameCategoryDiscount = 0;
            boolean BuyThreeItem = false; //variable to check is there ThreeItem product in the cart

            for (int j = 0; j < ListOfProducts.size(); j++) {
                if (ListOfProducts.get(j).ItemCount >= 3) {
                    BuyThreeItem = true;
                    break;
                }
            }


            if (BuyThreeItem) {
                ThreeItemSameCategoryDiscount = Total * 0.2;
                ShoppingCartGui.ThreeItemSameCategoryDiscountContainerLabel.setText(DecimalFormat.format(ThreeItemSameCategoryDiscount) + "£");
            } else {
                ShoppingCartGui.ThreeItemSameCategoryDiscountContainerLabel.setText(DecimalFormat.format(ThreeItemSameCategoryDiscount) + "£");
            }


            double FinalTotal = Total - FirstPurchaseDiscount - ThreeItemSameCategoryDiscount;
            ShoppingCartGui.FinalTotalContainerLabel.setText(DecimalFormat.format(FinalTotal) + "£");

        }catch (NullPointerException e){
        }
    }

    //getters
    public static ArrayList<ShoppingCart> getListOfProducts() {
        return ListOfProducts;
    }

    public Product getProduct() {
        return Product;
    }

    public int getItemCount() {
        return ItemCount;
    }

    public String getProductSpecification1() {
        return ProductSpecification1;
    }

    public String getProductSpecification2() {
        return ProductSpecification2;
    }
}
