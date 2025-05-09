class Mobile2{
    String brand;
    int price;
    static String name;
    public void show(){
        System.out.println(brand +" "+name+ " "+ price);
    }
    public static void show1(Mobile obj){
        System.out.println(obj.brand +" "+name+ " "+ obj.price);     //System.out.println(brand +" "+name+ " "+ price) we cannot use a npn static variable inside the static method
    }
    static class Samsung{
        public void show2(){
            System.out.println("inner static clas");
        }
    }
}

public class j_37_StaticMethod {
    public static void main(String[] args) {
        Mobile2 obj1 = new Mobile2();
        obj1.brand = "Apple";
        obj1.price = 1500;
        Mobile2.name = "SmartPhones";

        Mobile obj2 = new Mobile();
        obj2.brand = "Samsung";
        obj2.price = 1700;
        obj1.show();
        obj2.show();

        Mobile2.Samsung s2Samsung = new Mobile2.Samsung();
        s2Samsung.show2();
//        Mobile2.show1(obj1);

    }
}