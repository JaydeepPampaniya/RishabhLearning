class ABC{
    public void show(){
        System.out.println("ABC");
    }
}
class BCA extends ABC{
    public void show(){
        System.out.println("BCA");
    }
}
class ADC extends BCA{
    public void show(){
        System.out.println("ADC");
    }
}


public class j_56_dynamicMethodDispatch{
    public static void main(String[] args) {
        ABC a3 = new ABC();
        ABC a1 = new BCA();
        a1.show();

        BCA a2 = new ADC();
        a2.show();

        a3 =new BCA();
        a3.show();

        a3 = new BCA();
        a3.show();

        a3 = new ADC();
        a3.show();
    }
}