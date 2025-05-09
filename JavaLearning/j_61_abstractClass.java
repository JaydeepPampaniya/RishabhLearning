abstract class car{
    public abstract void drive();
    public abstract void fly();
}
 abstract class WagonR extends car{   // must be override all the abstract methods of parent class
    // if you don't wont to override a abstract class methods, make it abstract(child)
    public void drive() {
        
    }

}
class updatedWagonR extends WagonR{
    public void fly() { //because i never ovverride the superclass
        System.out.println("car flying");
    }
    // public void drive(){

    // }
}

public class j_61_abstractClass {
    public static void main(String[] args) {
        car a = new updatedWagonR();
        a.fly();
        a.drive();
    }
}
