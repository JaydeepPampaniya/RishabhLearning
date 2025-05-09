interface i1{
    int a=45;
    String name= "jaydeep"; // by default variables are final and static so this reason immediatly assign the value of variable

    void show(); // so by default method is public and abstract
    void config();
}
class i2 implements i1{
    public void show(){
        
    }
    public void config(){

    }
}

public class j_65_Interface {
    
    
    public static void main(String[] args) {
        i1 s1 = new i2();
        s1.show();
        s1.config();
    }
}
