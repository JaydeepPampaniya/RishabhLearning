class C extends Object{ // avery super class extends object class by default 
    C(){
        super();
        System.out.println("C");
    }
    C(int i){
        super();
        System.out.println("C int");
    }
}
class B extends C{
    
    B(){
        super();
        System.out.println("B");
    }
    B(int i){
        this();
        System.out.println("B int");
    }

}


public class j_51_constructors {
    public static void main(String[] args) {
        B b = new B (5);
    }
}
