interface Parent{
    int age = 44;
    String name="jaydeep";
    void show();
    void config();
}

interface parent2{
    void run();
}
interface child extends parent2{
    
}
class jpd implements Parent,child{
    public void run(){
        System.out.println("run");
    }
    public void show(){
        System.out.println("Show");
    }
    public void config(){
        System.out.println("config");
    }
}


public class j_67_multipleInheritance {
    public static void main(String[] args) {
        Parent s1 = new jpd();
        s1.show();
        s1.config();
        
        parent2 s2 = new jpd();
        s2.run();
    }
}
