// Interface representing a printable behavior
interface Printable {
    void print();
}

// Interface representing a drawable behavior
interface Drawable {
    void draw();
}

// Class implementing both Printable and Drawable interfaces
class MyClass implements Printable, Drawable {
    @Override
    public void print() {
        System.out.println("Printing...");
    }

    @Override
    public void draw() {
        System.out.println("Drawing...");
    }
}

public class Test {
    public static void main(String[] args) {
        MyClass myObject = new MyClass();
        myObject.print();  // Invokes the print method from the Printable interface
        myObject.draw();   // Invokes the draw method from the Drawable interface
    }
}
