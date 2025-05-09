class Mobile1{
    String brand;
    int price;
    static String name;
    public Mobile1(){
        brand =" ";
        price = 200;
        System.out.println("in constructors");
    }
    static{
        String name ="phonr";
        System.out.println("in static block");
    }
}

public class j_38_StaticBlock {
    static{
        System.out.println("static block inside main class");
    }
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("Mobile"); // direct load a class
        Mobile1 iphone = new Mobile1();
        System.out.println( Mobile1.name);
    }
}
