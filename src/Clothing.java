public class Clothing extends Product {

    private String Size; //variable to contain size
    private String Colour; //variable to contain colour

    //Default constructor
    public Clothing(){

    }

    //Parameterized constructor
    public Clothing(String productId, String productName, String category, int numberOfAvailableItems, double price,String size, String colour) {
        super(productId,productName, category, numberOfAvailableItems,price);
        Size = size;
        Colour = colour;
    }

    @Override
    public String toString() {
        return "Product {" +
                "ProductId = " + ProductId +
                ", ProductName = " + ProductName +
                ", NumberOfAvailableItems = " + NumberOfAvailableItems +
                ", Price= " + Price +
                ", Size= " + Size +
                ", Colour= " + Colour +
                ", Category = " + Category +
                '}';
    }

}
