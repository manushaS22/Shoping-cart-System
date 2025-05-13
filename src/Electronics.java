public class Electronics extends Product {

    private String Brand; //variable to contain brand
    private String WarrantyPeriod; //variable to contain warranty period

    //Default constructor
    public Electronics(){

    }

    //Parameterized constructor
    public Electronics(String productId, String productName, String category, int numberOfAvailableItems, double price, String brand, String warrantyPeriod) {
        super(productId,productName, category, numberOfAvailableItems,price);
        Brand = brand;
        WarrantyPeriod = warrantyPeriod;
    }



    @Override
    public String toString() {
        return "Product {" +
                "ProductId = " + ProductId +
                ", ProductName = " + ProductName +
                ", NumberOfAvailableItems = " + NumberOfAvailableItems +
                ", Price= " + Price +
                ", Brand= " + Brand +
                ", WarrantyPeriod= " + WarrantyPeriod +
                ", Category = " + Category +
                '}';
    }
}
