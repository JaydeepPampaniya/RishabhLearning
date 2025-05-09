class calc{ // if i make a final class no one can extends this class

    //final variable,methods,class
    final public void show(){
        System.out.println("calc show");
    }
    public void add(int a,int b){
        System.out.println(a+b);
    }
}
class advCalc extends calc{
//    public void show(){
//        System.out.println("abdCalc show");
//    }
    public void add(int a,int b){
        System.out.println(a-b);
    }
}

public class j_57_FinalEX {
    public static void main(String[] args) {
        
    }
}
