public abstract class Product {


    protected String ProductId; //variable to contain product ID
    protected String ProductName; //variable to contain Product Name
    protected int NumberOfAvailableItems; //variable to contain number of available items
    protected double Price; //variable to contain price
    protected String Category; //variable to contain category

    //Parameterized constructor
    public Product(String productId, String productName, String category, int numberOfAvailableItems, double price) {
        ProductId = productId;
        ProductName = productName;
        NumberOfAvailableItems = numberOfAvailableItems;
        Price = price;
        Category = category;
    }

    // Default constructor
    public Product() {

    }

    //getters
    public String getProductId() {
        return ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getNumberOfAvailableItems() {
        return NumberOfAvailableItems;
    }

    public double getPrice() {
        return Price;
    }

    //setters
    public void setNumberOfAvailableItems(int numberOfAvailableItems) {
        NumberOfAvailableItems = numberOfAvailableItems;
    }




}
