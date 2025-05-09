enum Laptops{
    Macbook(2000),XPS(2200),ThinkPad,Samsung(1900);
    
    private int price;
    private Laptops(){
        this.price=500; // this constructors calls once time
    }
    private Laptops(int price){
        this.price=price;
        System.out.println("in laptop "+this.name()); // this constructors called 3 times
    }

    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price=price;
    }
}

enum Samsung{
    S25_Ultra(149999),S25_Plus(99999),S25(79999),S24_Ultra(99999),S24_Plus(79999),S24;
    
    private int price;
    private Samsung(){
        price=55000;
    }
    private Samsung(int price){
        this.price=price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    

}

public class j_70_enumClass {
    public static void main(String[] args) {
        Laptops a1 = Laptops.Macbook;
        System.out.println(a1+" :  "+a1.getPrice());


        for(Laptops aa:Laptops.values()){
            System.out.println(aa+" :  "+aa.getPrice());
        }

        Samsung mobile = Samsung.S25_Ultra;
        System.out.println(mobile.getPrice());
    }
}
