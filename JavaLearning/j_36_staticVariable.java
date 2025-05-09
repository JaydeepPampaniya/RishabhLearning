class Mobile{
    String brand;
    int price;
    static String name;
    public void show(){
        System.out.println(brand +" "+name+ " "+ price);
    }
}

public class j_36_staticVariable {
    public static void main(String[] args) {
        Mobile obj1 = new Mobile();
        obj1.brand = "Apple";
        obj1.price = 1500;
        Mobile.name = "SmartPhones";
        
        Mobile obj2 = new Mobile();
        obj2.brand = "Samsung";
        obj2.price = 1700;
        obj1.show();
        obj2.show();
    }
}
